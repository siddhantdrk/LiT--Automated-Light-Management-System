package com.my.lit.activities;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import com.my.lit.databinding.ActivityGuestRequestLightingBinding;

public class GuestRequestLightingActivity extends AppCompatActivity {

    private ActivityGuestRequestLightingBinding requestLightingBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestLightingBinding = ActivityGuestRequestLightingBinding.inflate(LayoutInflater.from(this));
        setContentView(requestLightingBinding.getRoot());
    }
}