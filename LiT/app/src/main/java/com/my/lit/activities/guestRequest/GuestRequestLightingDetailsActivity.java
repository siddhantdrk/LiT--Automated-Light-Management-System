package com.my.lit.activities.guestRequest;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import com.my.lit.databinding.ActivityGuestRequestLightingBinding;

public class GuestRequestLightingDetailsActivity extends AppCompatActivity {
    ActivityGuestRequestLightingBinding guestRequestLightingBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        guestRequestLightingBinding = com.my.lit.databinding.ActivityGuestRequestLightingBinding.inflate(LayoutInflater.from(this));
        setContentView(guestRequestLightingBinding.getRoot());
    }
}