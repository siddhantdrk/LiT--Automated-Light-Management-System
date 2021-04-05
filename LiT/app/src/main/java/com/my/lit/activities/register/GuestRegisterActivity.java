package com.my.lit.activities.register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.my.lit.R;
import com.my.lit.activities.dashboard.GuestDashBoardActivity;
import com.my.lit.activities.login.GuestLoginActivity;
import com.my.lit.api.RetrofitClient;
import com.my.lit.databinding.ActivityRegisterBinding;
import com.my.lit.responses.AuthErrorResponse;
import com.my.lit.responses.GuestAuthResponse;
import com.my.lit.storage.SharedPreferenceManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GuestRegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding activityRegisterBinding;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRegisterBinding = ActivityRegisterBinding.inflate(LayoutInflater.from(this));
        setContentView(activityRegisterBinding.getRoot());
        mProgress = new ProgressDialog(this);
        activityRegisterBinding.registerBtn.setOnClickListener(this::onClick);
        activityRegisterBinding.signIn.setOnClickListener(this::onClick);
    }

    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.registerBtn:
                register();
                break;
            case R.id.signIn:
                Intent intent1 = new Intent(GuestRegisterActivity.this, GuestLoginActivity.class);
                view.getContext().startActivity(intent1);
                finish();
                break;
        }
    }

    private void register() {
        mProgress.setTitle("Registering user");
        mProgress.setMessage("Please wait while we Register you");
        mProgress.setCanceledOnTouchOutside(false);
        mProgress.show();
        String email = activityRegisterBinding.signUpEmail.getText().toString().trim();
        String password = activityRegisterBinding.signUpPassword.getText().toString().trim();
        String confirmPassword = activityRegisterBinding.signUpConfirmPassword.getText().toString().trim();
        String firstName = activityRegisterBinding.signUpUserFirstName.getText().toString().trim();
        String lastName = activityRegisterBinding.signUpUserLastName.getText().toString().trim();
        if (!password.equals(confirmPassword)) {
            Toast.makeText(GuestRegisterActivity.this, "password does not match", Toast.LENGTH_LONG).show();
            mProgress.dismiss();
        } else {
            Call<GuestAuthResponse> registerResponseCall = RetrofitClient.getInstance().getUserServices().userRegister(email, confirmPassword, firstName, lastName);
            registerResponseCall.enqueue(new Callback<GuestAuthResponse>() {
                @Override
                public void onResponse(Call<GuestAuthResponse> call, Response<GuestAuthResponse> response) {
                    mProgress.dismiss();
                    if (response.isSuccessful()) {
                        GuestAuthResponse guestAuthResponse = response.body();
                        SharedPreferenceManager.getInstance(GuestRegisterActivity.this).saveToken(guestAuthResponse.getGuestData().getToken());
                        startActivity(new Intent(GuestRegisterActivity.this, GuestDashBoardActivity.class));
                        Toast.makeText(GuestRegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        try {
                            AuthErrorResponse authErrorResponse = new Gson().fromJson(response.errorBody().string(), AuthErrorResponse.class);
                            Toast.makeText(GuestRegisterActivity.this, "" + authErrorResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<GuestAuthResponse> call, Throwable t) {
                    mProgress.dismiss();
                    Toast.makeText(GuestRegisterActivity.this, "Something went wrong\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}