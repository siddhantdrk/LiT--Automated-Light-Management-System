package com.my.lit.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.my.lit.R;
import com.my.lit.activities.login.AdminLoginActivity;
import com.my.lit.activities.login.GuestLoginActivity;
import com.my.lit.databinding.ActivityWelcomeBinding;

public class WelcomeActivity extends AppCompatActivity {
    ActivityWelcomeBinding welcomeBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        welcomeBinding=ActivityWelcomeBinding.inflate(LayoutInflater.from(this));
        setContentView(welcomeBinding.getRoot());
        welcomeBinding.proceedAdminBtn.setOnClickListener(this::onClick);
        welcomeBinding.proceedGuestBtn.setOnClickListener(this::onClick);
    }

    private void onClick(View view) {
        switch (view.getId()){
            case R.id.proceed_admin_btn:
                startActivity(new Intent(WelcomeActivity.this, AdminLoginActivity.class));
            break;
            case R.id.proceed_guest_btn:
                startActivity(new Intent(WelcomeActivity.this, GuestLoginActivity.class));
            break;
        }
    }

}