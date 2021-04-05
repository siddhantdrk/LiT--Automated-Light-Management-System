package com.my.lit.activities.login;

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
import com.my.lit.activities.register.GuestRegisterActivity;
import com.my.lit.api.RetrofitClient;
import com.my.lit.databinding.ActivityLoginBinding;
import com.my.lit.responses.AuthErrorResponse;
import com.my.lit.responses.GuestAuthResponse;
import com.my.lit.storage.SharedPreferenceManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GuestLoginActivity extends AppCompatActivity {

    ActivityLoginBinding activityLoginBinding;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityLoginBinding = ActivityLoginBinding.inflate(LayoutInflater.from(this));
        setContentView(activityLoginBinding.getRoot());

        activityLoginBinding.SignUp.setOnClickListener(this::onClick);
        activityLoginBinding.LogInBtn.setOnClickListener(this::onClick);
        mProgress = new ProgressDialog(this);
    }

    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.SignUp:
                Intent intent = new Intent(GuestLoginActivity.this, GuestRegisterActivity.class);
                view.getContext().startActivity(intent);
                finish();
                break;
            case R.id.LogInBtn:
                login();
        }
    }

    private void login() {

        String email = activityLoginBinding.signInEmail.getText().toString().trim();
        String password = activityLoginBinding.signInPassword.getText().toString().trim();

        if(!email.isEmpty()) {
            if (!password.isEmpty()) {
                mProgress.setTitle("Logging in");
                mProgress.setMessage("Please wait while we fetching your login account");
                mProgress.setCanceledOnTouchOutside(false);
                mProgress.show();
                Call<GuestAuthResponse> userLoginResponseCall = RetrofitClient.getInstance().getUserServices().userLogin(email, password);
                userLoginResponseCall.enqueue(new Callback<GuestAuthResponse>() {
                    @Override
                    public void onResponse(Call<GuestAuthResponse> call, Response<GuestAuthResponse> response) {
                        mProgress.dismiss();
                        if (response.isSuccessful()) {
                            GuestAuthResponse guestAuthResponse = response.body();
                            SharedPreferenceManager.getInstance(GuestLoginActivity.this).saveToken(guestAuthResponse.getGuestData().getToken());
                            startActivity(new Intent(GuestLoginActivity.this, GuestDashBoardActivity.class));
                            Toast.makeText(GuestLoginActivity.this, "Logged In Successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            try {
                                AuthErrorResponse authErrorResponse = new Gson().fromJson(response.errorBody().string(), AuthErrorResponse.class);
                                Toast.makeText(GuestLoginActivity.this, "" + authErrorResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<GuestAuthResponse> call, Throwable t) {
                        mProgress.dismiss();
                        Toast.makeText(GuestLoginActivity.this, "Something went wrong\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                activityLoginBinding.signInPassword.setError("Password field must be filled");
            }
        }else{
            activityLoginBinding.signInEmail.setError("Email field is empty");
        }

    }
}