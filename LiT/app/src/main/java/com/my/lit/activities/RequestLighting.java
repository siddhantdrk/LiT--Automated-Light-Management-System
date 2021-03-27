package com.my.lit.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.my.lit.R;
import com.my.lit.databinding.ActivityRequestLightingBinding;

public class RequestLighting extends AppCompatActivity {

    ActivityRequestLightingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_lighting);
    }
}