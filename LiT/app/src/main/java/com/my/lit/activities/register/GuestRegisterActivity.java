package com.my.lit.activities.register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.my.lit.R;
import com.my.lit.activities.dashboard.GuestDashBoardActivity;
import com.my.lit.activities.login.GuestLoginActivity;
import com.my.lit.api.RetrofitClient;
import com.my.lit.databinding.ActivityRegisterBinding;
import com.my.lit.responses.UserRegisterResponse;

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
        Call<UserRegisterResponse> registerResponseCall = RetrofitClient.getInstance().getUserServices().userRegister("siddhantdrk@gmail.com", "hardik0405", "Hardik", "Bajaj");
        registerResponseCall.enqueue(new Callback<UserRegisterResponse>() {
            @Override
            public void onResponse(Call<UserRegisterResponse> call, Response<UserRegisterResponse> response) {
                mProgress.dismiss();
                UserRegisterResponse userRegisterResponse = response.body();
                if (response.isSuccessful()) {
                    if (response.code() == 400) {
                        Toast.makeText(GuestRegisterActivity.this, userRegisterResponse.getError(), Toast.LENGTH_SHORT).show();
                    } else if (response.code() == 200) {
                        startActivity(new Intent(GuestRegisterActivity.this, GuestDashBoardActivity.class));
                        Toast.makeText(GuestRegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } else {
                    Toast.makeText(GuestRegisterActivity.this, "Unsuccessful " + response.code() + response.raw(), Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<UserRegisterResponse> call, Throwable t) {
                mProgress.dismiss();
                Toast.makeText(GuestRegisterActivity.this, "Something went wrong\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}