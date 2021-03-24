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
import com.my.lit.responses.UserLoginResponse;
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
                break;
            case R.id.LogInBtn:
                login();
        }
    }

    private void login() {
        mProgress.setTitle("Logging in");
        mProgress.setMessage("Please wait while we fetching your login account");
        mProgress.setCanceledOnTouchOutside(false);
        mProgress.show();
        String email = activityLoginBinding.signInEmail.getText().toString().trim();
        String password = activityLoginBinding.signInPassword.getText().toString().trim();
        Call<UserLoginResponse> userLoginResponseCall = RetrofitClient.getInstance().getUserServices().userLogin("rahuldev1531@gmail.com", "123456");
        userLoginResponseCall.enqueue(new Callback<UserLoginResponse>() {
            @Override
            public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
                mProgress.dismiss();
                if (response.isSuccessful()) {
                    UserLoginResponse userLoginResponse = response.body();
                    SharedPreferenceManager.getInstance(GuestLoginActivity.this).saveToken(userLoginResponse.getToken());
                    startActivity(new Intent(GuestLoginActivity.this, GuestDashBoardActivity.class));
                    Toast.makeText(GuestLoginActivity.this, "Logged In Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    try {
                        AuthErrorResponse authErrorResponse = new Gson().fromJson(response.errorBody().string(), AuthErrorResponse.class);
                        Toast.makeText(GuestLoginActivity.this, "" + authErrorResponse.getError(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserLoginResponse> call, Throwable t) {
                mProgress.dismiss();
                Toast.makeText(GuestLoginActivity.this, "Something went wrong\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}