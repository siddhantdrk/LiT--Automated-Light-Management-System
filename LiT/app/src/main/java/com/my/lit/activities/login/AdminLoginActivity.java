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
import com.my.lit.activities.dashboard.AdminDashBoardActivity;
import com.my.lit.activities.register.GuestRegisterActivity;
import com.my.lit.api.RetrofitClient;
import com.my.lit.databinding.ActivityLoginBinding;
import com.my.lit.responses.AdminLoginResponse;
import com.my.lit.responses.AuthErrorResponse;
import com.my.lit.storage.SharedPreferenceManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminLoginActivity extends AppCompatActivity {
    private ActivityLoginBinding activityLoginBinding;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login);
        activityLoginBinding = ActivityLoginBinding.inflate(LayoutInflater.from(this));
        setContentView(activityLoginBinding.getRoot());
        mProgress = new ProgressDialog(this);
        activityLoginBinding.SignUp.setOnClickListener(this::onClick);
        activityLoginBinding.LogInBtn.setOnClickListener(this::onClick);

    }

    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.SignUp:
                Intent intent = new Intent(AdminLoginActivity.this, GuestRegisterActivity.class);
                view.getContext().startActivity(intent);
            case R.id.LogInBtn:
                login();
        }
    }

    private void login() {

        mProgress.setTitle("Logging in");
        mProgress.setMessage("Please wait while we are fetching your account");
        mProgress.setCanceledOnTouchOutside(false);
        mProgress.show();

        String email = activityLoginBinding.signInEmail.getText().toString().trim();
        String password = activityLoginBinding.signInPassword.getText().toString().trim();

        Call<AdminLoginResponse> adminLoginResponseCall = RetrofitClient.getInstance().getUserServices().adminLogin(email, password);

        adminLoginResponseCall.enqueue(new Callback<AdminLoginResponse>() {
            @Override
            public void onResponse(Call<AdminLoginResponse> call, Response<AdminLoginResponse> response) {
                mProgress.dismiss();
                if (response.isSuccessful()) {
                    AdminLoginResponse adminLoginResponse = response.body();
                    SharedPreferenceManager.getInstance(AdminLoginActivity.this).saveToken(adminLoginResponse.getToken());
                    startActivity(new Intent(AdminLoginActivity.this, AdminDashBoardActivity.class));
                    Toast.makeText(AdminLoginActivity.this, "Logged In Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    try {
                        AuthErrorResponse authErrorResponse = new Gson().fromJson(response.errorBody().string(), AuthErrorResponse.class);
                        Toast.makeText(AdminLoginActivity.this, "" + authErrorResponse.getError(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<AdminLoginResponse> call, Throwable t) {
                mProgress.dismiss();
                Toast.makeText(AdminLoginActivity.this, "Something went wrong\n" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}