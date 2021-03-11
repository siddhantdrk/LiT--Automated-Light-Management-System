package com.my.lit.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.my.lit.R;
import com.my.lit.activities.login.GuestLoginActivity;
import com.my.lit.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding activityRegisterBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        activityRegisterBinding = ActivityRegisterBinding.inflate(LayoutInflater.from(this));
        setContentView(activityRegisterBinding.getRoot());

        activityRegisterBinding.signUpRegister.setOnClickListener(this::onClick);
        activityRegisterBinding.signUpRegister.setOnClickListener(this::onClick);
    }

    private void onClick(View view) {
        switch (view.getId()){
            case R.id.signUp_register:
                Intent intent = new Intent(RegisterActivity.this , DashBoard.class);
                view.getContext().startActivity(intent);
            case R.id.signIn:
                Intent intent1 = new Intent(RegisterActivity.this , GuestLoginActivity.class);
                view.getContext().startActivity(intent1);
        }
    }
}