package com.my.lit.activities.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.my.lit.R;
import com.my.lit.activities.ViewRoomsUser;
import com.my.lit.databinding.ActivityGuestDashBoardBinding;
import com.my.lit.storage.SharedPreferenceManager;

public class GuestDashBoardActivity extends AppCompatActivity {

    private ActivityGuestDashBoardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGuestDashBoardBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());


        binding.logoutBtn.setOnClickListener(this::onClick);
        binding.viewCurrentLightingBtn.setOnClickListener(this::onClick);
    }

    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.logout_btn:
                SharedPreferenceManager.getInstance(this).clear();
                finish();
                break;

            case R.id.view_current_lighting_btn:
                Intent intent = new Intent(getApplicationContext(), ViewRoomsUser.class);
                startActivity(intent);
                break;
        }
    }
}