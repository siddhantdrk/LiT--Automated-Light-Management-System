package com.my.lit.activities.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.my.lit.R;
import com.my.lit.activities.dashboard.GuestDashBoardActivity;
import com.my.lit.activities.register.GuestRegisterActivity;
import com.my.lit.databinding.ActivityLoginBinding;

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
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        mProgress.dismiss();
                        Intent intent1 = new Intent(GuestLoginActivity.this, GuestDashBoardActivity.class);
                        view.getContext().startActivity(intent1);
                    }
                }, 3000);
                Intent intent1 = new Intent(GuestLoginActivity.this, GuestDashBoardActivity.class);
                view.getContext().startActivity(intent1);
                break;
        }
    }

    private void login() {

    }
}