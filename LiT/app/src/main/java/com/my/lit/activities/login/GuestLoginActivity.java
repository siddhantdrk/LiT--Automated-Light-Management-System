package com.my.lit.activities.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.my.lit.R;
import com.my.lit.activities.dashboard.GuestDashBoardActivity;
import com.my.lit.activities.register.GuestRegisterActivity;
import com.my.lit.api.RetrofitClient;
import com.my.lit.databinding.ActivityLoginBinding;
import com.my.lit.responses.UserLoginResponse;
import com.my.lit.responses.UserRegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GuestLoginActivity extends AppCompatActivity {

    ActivityLoginBinding activityLoginBinding;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        activityLoginBinding = ActivityLoginBinding.inflate(LayoutInflater.from(this));
        setContentView(activityLoginBinding.getRoot());

        activityLoginBinding.SignUp.setOnClickListener(this::onClick);
        activityLoginBinding.LogInBtn.setOnClickListener(this::onClick);
        mProgress = new ProgressDialog(this);
    }

    private void onClick(View view) {
        switch (view.getId()){
            case R.id.SignUp:
                Intent intent = new Intent(GuestLoginActivity.this, GuestRegisterActivity.class);
                view.getContext().startActivity(intent);
                break;
            case R.id.LogInBtn:
                mProgress.setTitle("Logging in");
                mProgress.setMessage("Please wait while we fetching your login account");
                mProgress.setCanceledOnTouchOutside(false);
                mProgress.show();
                login();
        }
    }

    private void login() {
        String email = activityLoginBinding.signInEmail.getText().toString().trim();
        String password = activityLoginBinding.signInPassword.getText().toString().trim();
        Call<UserLoginResponse> userLoginResponseCall = RetrofitClient.getInstance().getUserServices().userLogin(email,password);
        userLoginResponseCall.enqueue(new Callback<UserLoginResponse>() {
            @Override
            public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
                mProgress.dismiss();
                UserLoginResponse loginResponseCall = response.body();
                if (response.isSuccessful()) {
                    if (response.code() == 400) {
                        Toast.makeText(GuestLoginActivity.this, loginResponseCall.getError(), Toast.LENGTH_SHORT).show();
                    } else if (response.code() == 200) {
                        startActivity(new Intent(GuestLoginActivity.this, GuestDashBoardActivity.class));
                        Toast.makeText(GuestLoginActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } else {
                    Toast.makeText(GuestLoginActivity.this, "Unsuccessful " + response.code() + response.raw(), Toast.LENGTH_SHORT).show();
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