package com.my.lit.activities.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.my.lit.R;
import com.my.lit.databinding.ActivityAdminDashBoardBinding;
import com.my.lit.storage.SharedPreferenceManager;

public class AdminDashBoardActivity extends AppCompatActivity {
    ActivityAdminDashBoardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminDashBoardBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        binding.logoutBtn.setOnClickListener(this::onClick);
    }

    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.logout_btn:
                SharedPreferenceManager.getInstance(this).clear();
                finish();
        }
    }
}