package com.my.lit.activities.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.my.lit.R;
import com.my.lit.activities.dashboard.GuestDashBoardActivity;
import com.my.lit.activities.register.GuestRegisterActivity;
import com.my.lit.api.RetrofitClient;
import com.my.lit.databinding.ActivityLoginBinding;
import com.my.lit.responses.AdminLoginResponse;
import com.my.lit.responses.UserLoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminLoginActivity extends AppCompatActivity {
    ActivityLoginBinding activityLoginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login);
        activityLoginBinding = ActivityLoginBinding.inflate(LayoutInflater.from(this));
        setContentView(activityLoginBinding.getRoot());

        activityLoginBinding.SignUp.setOnClickListener(this::onClick);
        activityLoginBinding.LogInBtn.setOnClickListener(this::onClick);

    }

    private void onClick(View view) {
        switch (view.getId()){
            case R.id.SignUp:
                Intent intent = new Intent(AdminLoginActivity.this, GuestRegisterActivity.class);
                view.getContext().startActivity(intent);
            case R.id.signIn:
                Intent intent1 = new Intent(AdminLoginActivity.this, GuestDashBoardActivity.class);
                view.getContext().startActivity(intent1);
                login();

        }
    }

    private void login() {

        String Adminemail = activityLoginBinding.signInEmail.getText().toString().trim();
        String Adminpassword = activityLoginBinding.signInPassword.getText().toString().trim();

        Call<AdminLoginResponse> adminLoginResponseCall = RetrofitClient.getInstance().getUserServices().adminLogin(Adminemail,Adminpassword);

        adminLoginResponseCall.enqueue(new Callback<AdminLoginResponse>() {
            @Override
            public void onResponse(Call<AdminLoginResponse> call, Response<AdminLoginResponse> response) {
                AdminLoginResponse adminLoginResponse = response.body();
                if(response.isSuccessful()){
                    if(response.code() == 400){
                        Toast.makeText(AdminLoginActivity.this, adminLoginResponse.getError(), Toast.LENGTH_SHORT).show();
                    }else if(response.code() == 200){
                        startActivity(new Intent(AdminLoginActivity.this, GuestDashBoardActivity.class));
                        Toast.makeText(AdminLoginActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else{
                        Toast.makeText(AdminLoginActivity.this, "Unsuccessful " + response.code() + response.raw(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<AdminLoginResponse> call, Throwable t) {

                Toast.makeText(AdminLoginActivity.this, "Something went wrong\n" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}