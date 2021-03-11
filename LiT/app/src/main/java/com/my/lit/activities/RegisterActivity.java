package com.my.lit.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.my.lit.R;
import com.my.lit.activities.login.GuestLoginActivity;
import com.my.lit.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

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
        switch (view.getId()){
            case R.id.registerBtn:
                Intent intent = new Intent(RegisterActivity.this , DashBoard.class);
                view.getContext().startActivity(intent);
                mProgress.setTitle("Registering user");
                mProgress.setMessage("Please wait while we Register you");
                mProgress.setCanceledOnTouchOutside(false);
                mProgress.show();
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        mProgress.dismiss();
                        Intent intent = new Intent(RegisterActivity.this , DashBoard.class);
                        view.getContext().startActivity(intent);
                    }
                }, 3000);
                break;
            case R.id.signIn:
                Intent intent1 = new Intent(RegisterActivity.this , GuestLoginActivity.class);
                view.getContext().startActivity(intent1);
                break;
        }
    }
}