package com.my.lit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.my.lit.R;
import com.my.lit.activities.dashboard.AdminDashBoardActivity;
import com.my.lit.activities.dashboard.GuestDashBoardActivity;
import com.my.lit.activities.login.AdminLoginActivity;
import com.my.lit.activities.login.GuestLoginActivity;
import com.my.lit.databinding.ActivityWelcomeBinding;
import com.my.lit.storage.SharedPreferenceManager;

public class WelcomeActivity extends AppCompatActivity {
    private ActivityWelcomeBinding welcomeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        welcomeBinding = ActivityWelcomeBinding.inflate(LayoutInflater.from(this));
        checkIfUserExists();
        welcomeBinding.proceedAdminBtn.setOnClickListener(this::onClick);
        welcomeBinding.proceedGuestBtn.setOnClickListener(this::onClick);
    }

    private void onClick(View view) {
        if (view.getId() == R.id.proceed_admin_btn) {
            startActivity(new Intent(WelcomeActivity.this, AdminLoginActivity.class));
            SharedPreferenceManager.getInstance(this).setAdmin(true);
        } else if (view.getId() == R.id.proceed_guest_btn) {
            startActivity(new Intent(WelcomeActivity.this, GuestLoginActivity.class));
            SharedPreferenceManager.getInstance(this).setAdmin(false);
        }
    }

    private void checkIfUserExists() {
        if (SharedPreferenceManager.getInstance(this).getToken() != null) {
            if (SharedPreferenceManager.getInstance(this).isAdmin())
                startActivity(new Intent(WelcomeActivity.this, AdminDashBoardActivity.class));
            else
                startActivity(new Intent(WelcomeActivity.this, GuestDashBoardActivity.class));
            finish();
        } else {
            setContentView(welcomeBinding.getRoot());
        }
    }
}