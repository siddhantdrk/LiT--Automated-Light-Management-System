package com.my.lit.activities.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.my.lit.R;
import com.my.lit.activities.DashBoard;
import com.my.lit.activities.RegisterActivity;
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
        activityLoginBinding.signInButton.setOnClickListener(this::onClick);
        mProgress = new ProgressDialog(this);
    }

    private void onClick(View view) {
        switch (view.getId()){
            case R.id.SignUp:
                Intent intent = new Intent(GuestLoginActivity.this , RegisterActivity.class);
                view.getContext().startActivity(intent);
            case R.id.signIn:
                mProgress.setTitle("Logging in");
                mProgress.setMessage("Please wait while we fetching your login account");
                mProgress.setCanceledOnTouchOutside(false);
                mProgress.show();
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        mProgress.dismiss();
                        Intent intent1 = new Intent(GuestLoginActivity.this , DashBoard.class);
                        view.getContext().startActivity(intent1);
                    }
                }, 3000);
        }
    }
}