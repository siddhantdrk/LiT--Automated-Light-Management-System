package com.my.lit.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.my.lit.R;
import com.my.lit.adapters.ViewLightsAdapter;
import com.my.lit.databinding.ActivityLightDetailsBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LightDetails extends AppCompatActivity {

    ActivityLightDetailsBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLightDetailsBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        HashMap<String,Boolean> lights = (HashMap<String, Boolean>) getIntent(). getSerializableExtra("Lights");

        ArrayList<String> lightnames = new ArrayList<>();
        ArrayList<Boolean> lightstatus = new ArrayList<>();

        for(Map.Entry<String, Boolean> entry : lights.entrySet()) {
            lightnames.add(entry.getKey());
            lightstatus.add(entry.getValue());

        }
        
        setupRecyclerView(lightnames,lightstatus);




    }

    private void setupRecyclerView(ArrayList<String> lightnames,ArrayList<Boolean> lightstatus) {

        ViewLightsAdapter adapter = new ViewLightsAdapter(this,lightnames,lightstatus);
        binding.lightDetailsRv.setAdapter(adapter);
        binding.lightDetailsRv.setLayoutManager(new LinearLayoutManager(this));
    }
}