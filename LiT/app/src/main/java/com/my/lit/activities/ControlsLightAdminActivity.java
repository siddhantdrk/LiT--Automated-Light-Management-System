package com.my.lit.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.my.lit.R;
import com.my.lit.adapters.ControlLightsAdapter;
import com.my.lit.adapters.RequestLightingAdapter;
import com.my.lit.adapters.ViewLightsAdapter;
import com.my.lit.databinding.ActivityControlsLightAdminBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ControlsLightAdminActivity extends AppCompatActivity {

    ActivityControlsLightAdminBinding binding;
    private String toCheck;

    ArrayList<String> lightnames = new ArrayList<>();
    ArrayList<Boolean> lightstatus = new ArrayList<>();
    HashMap<String,Boolean> requestedLights = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controls_light_admin);
        binding = ActivityControlsLightAdminBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        HashMap<String,Boolean> lights = (HashMap<String, Boolean>) getIntent(). getSerializableExtra("Lights");
//        toCheck = getIntent().getStringExtra("From_ViewRooms");

        for(Map.Entry<String, Boolean> entry : lights.entrySet()) {
            lightnames.add(entry.getKey());
            lightstatus.add(entry.getValue());
        }
        setUpRecyclerView(lightnames,lightstatus);

    }

    private void setUpRecyclerView(ArrayList<String> lightnames,ArrayList<Boolean> lightstatus) {

        ControlLightsAdapter adapter = new ControlLightsAdapter(this,lightnames,lightstatus);
        binding.lightDetailsRv.setAdapter(adapter);
        binding.lightDetailsRv.setLayoutManager(new LinearLayoutManager(this));
    }

}