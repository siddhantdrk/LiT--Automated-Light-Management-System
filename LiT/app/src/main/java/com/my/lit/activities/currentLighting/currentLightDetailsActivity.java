package com.my.lit.activities.currentLighting;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import com.my.lit.databinding.ActivityCurrentLightDetailsBinding;

public class currentLightDetailsActivity extends AppCompatActivity {

    private ActivityCurrentLightDetailsBinding currentLightDetailsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentLightDetailsBinding = ActivityCurrentLightDetailsBinding.inflate(LayoutInflater.from(this));
        setContentView(currentLightDetailsBinding.getRoot());
    }
}