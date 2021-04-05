package com.my.lit.activities.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.my.lit.R;
import com.my.lit.activities.ViewRoomsAdminActivity;
import com.my.lit.activities.WelcomeActivity;
import com.my.lit.activities.currentLighting.currentLightingGuestActivity;
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
        binding.viewCurrentLightingBtn.setOnClickListener(this::onClick);
        binding.controlLightingBtn.setOnClickListener(this::onClick);
        binding.seeUserRequestBtn.setOnClickListener(this::onClick);
    }

    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.logout_btn:
                SharedPreferenceManager.getInstance(this).clear();
                startActivity(new Intent(this, WelcomeActivity.class));
                finish();
                break;
            case R.id.view_current_lighting_btn:
                Intent intent = new Intent(AdminDashBoardActivity.this, currentLightingGuestActivity.class);
                intent.putExtra("From_GuestDashboard","ViewLightsAdmin");

                startActivity(intent);
                break;
            case R.id.control_lighting_btn:
                Intent intent2 = new Intent(AdminDashBoardActivity.this, ViewRoomsAdminActivity.class);
                intent2.putExtra("From_AdminDashboard","ControlLights");
                startActivity(intent2);
                break;
//            case R.id.see_user_request_btn:
//                Intent intent3 = new Intent(AdminDashBoardActivity.this, SeeUserRequestActivity.class);
//                intent3.putExtra("From_AdminDashboard","SendRequest");
//                startActivity(intent3);
//                break;
        }
    }
}