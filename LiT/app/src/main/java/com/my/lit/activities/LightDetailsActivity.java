package com.my.lit.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.my.lit.R;
import com.my.lit.adapters.ControlLightsAdapter;
import com.my.lit.adapters.RequestLightingAdapter;
import com.my.lit.adapters.ViewLightsAdapter;
import com.my.lit.databinding.ActivityLightDetailsBinding;
import com.my.lit.models.Room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LightDetailsActivity extends AppCompatActivity implements RequestLightingAdapter.OnItemClickListener {

    ActivityLightDetailsBinding binding;
    private String toCheck;

    ArrayList<String> lightnames = new ArrayList<>();
    ArrayList<Boolean> lightstatus = new ArrayList<>();
    HashMap<String,Boolean> requestedLights = new HashMap<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLightDetailsBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        HashMap<String,Boolean> lights = (HashMap<String, Boolean>) getIntent(). getSerializableExtra("Lights");
        toCheck = getIntent().getStringExtra("From_ViewRooms");

        for(Map.Entry<String, Boolean> entry : lights.entrySet()) {
            lightnames.add(entry.getKey());
            lightstatus.add(entry.getValue());
        }

        switch (toCheck){
            case "ViewLights":

                setupRecyclerViewforViewLights(lightnames,lightstatus);
                binding.sendRequestBtn.setVisibility(View.GONE);


            case "SendRequest":
                setupRecyclerViewforSendReq(lightnames,lightstatus);
                binding.sendRequestBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       // Toast.makeText(this,String.valueOf(requestedLights.size()),Toast.LENGTH_SHORT).show();
                    }
                });

        }



    }

    private void setupRecyclerViewforViewLights(ArrayList<String> lightnames,ArrayList<Boolean> lightstatus) {

        ViewLightsAdapter adapter = new ViewLightsAdapter(this,lightnames,lightstatus);
        binding.lightDetailsRv.setAdapter(adapter);
        binding.lightDetailsRv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void  setupRecyclerViewforSendReq(ArrayList<String> lightnames,ArrayList<Boolean> lightstatus){

        RequestLightingAdapter adapter = new RequestLightingAdapter(lightnames,lightstatus,getApplicationContext(),this);
        binding.lightDetailsRv.setAdapter(adapter);
        binding.lightDetailsRv.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public void onItemCheck(String name, Boolean state) {
        requestedLights.put(name,state);

    }

    @Override
    public void onItemUnchecked(String name, Boolean state) {
        requestedLights.remove(name);

    }
}