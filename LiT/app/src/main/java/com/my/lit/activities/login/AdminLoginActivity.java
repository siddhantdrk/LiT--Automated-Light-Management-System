package com.my.lit.activities.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.my.lit.R;
import com.my.lit.activities.DashBoard;
import com.my.lit.activities.RegisterActivity;
import com.my.lit.databinding.ActivityLoginBinding;

public class AdminLoginActivity extends AppCompatActivity {
    ActivityLoginBinding activityLoginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        activityLoginBinding = ActivityLoginBinding.inflate(LayoutInflater.from(this));
        setContentView(activityLoginBinding.getRoot());

        activityLoginBinding.SignUp.setOnClickListener(this::onClick);
        activityLoginBinding.LogInBtn.setOnClickListener(this::onClick);

    }

    private void onClick(View view) {
        switch (view.getId()){
            case R.id.SignUp:
                Intent intent = new Intent(AdminLoginActivity.this , RegisterActivity.class);
                view.getContext().startActivity(intent);
            case R.id.signIn:
                Intent intent1 = new Intent(AdminLoginActivity.this , DashBoard.class);
                view.getContext().startActivity(intent1);

        }
    }
}