package com.my.lit.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.my.lit.R;
import com.my.lit.activities.login.GuestLoginActivity;
import com.my.lit.databinding.ActivityLoginBinding;
import com.my.lit.databinding.ActivityWelcomeBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}