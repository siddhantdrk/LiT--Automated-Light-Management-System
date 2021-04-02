package com.my.lit.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.my.lit.R;
import com.my.lit.adapters.ViewRoomsAdapter;
import com.my.lit.databinding.ActivityViewLightsUserBinding;
import com.my.lit.models.Room;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewRoomsAdminActivity extends AppCompatActivity {

    ActivityViewLightsUserBinding binding;

    private String toCheckFromAdminDashBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewLightsUserBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        //Dummy list items
        ArrayList<Room> list = new ArrayList<>();
        Room room1 = new Room(5223,"CCC3" , 2);
        HashMap<String,Boolean> room1lights = new HashMap<>();
        room1lights.put("R1L1", true);
        room1lights.put("R1L2", true);
        room1lights.put("R1L3", false);
        room1lights.put("R1L4", true);
        room1lights.put("R1L5", false);
        room1.setLightstate(room1lights);
        Room room2 = new Room(5323,"CCC3" , 3);
        HashMap<String,Boolean> room2lights = new HashMap<>();
        room2lights.put("R2L1", true);
        room2lights.put("R2L2", false);
        room2lights.put("R2L3", false);
        room2lights.put("R2L4", true);
        room2lights.put("R2L5", false);
        room2.setLightstate(room2lights);
        Room room3 = new Room(5423,"CCC3" ,4);
        HashMap<String,Boolean> room3lights = new HashMap<>();
        room3lights.put("R3L1", true);
        room3lights.put("R3L2", false);
        room3lights.put("R3L3", false);
        room3lights.put("R3L4", true);
        room3lights.put("R3L5", false);
        room3.setLightstate(room3lights);

        Room room4 = new Room(5223,"CCC3" , 2);
        HashMap<String,Boolean> room4lights = new HashMap<>();
        room4lights.put("R1L1", true);
        room4lights.put("R1L2", true);
        room4lights.put("R1L3", false);
        room4lights.put("R1L4", true);
        room4lights.put("R1L5", false);
        room4.setLightstate(room4lights);

        Room room5 = new Room(5223,"CCC3" , 2);
        HashMap<String,Boolean> room5lights = new HashMap<>();
        room5lights.put("R1L1", true);
        room5lights.put("R1L2", true);
        room5lights.put("R1L3", false);
        room5lights.put("R1L4", true);
        room5lights.put("R1L5", false);
        room5.setLightstate(room5lights);

        Room room6 = new Room(5223,"CCC3" , 2);
        HashMap<String,Boolean> room6lights = new HashMap<>();
        room6lights.put("R1L1", true);
        room6lights.put("R1L2", true);
        room6lights.put("R1L3", false);
        room6lights.put("R1L4", true);
        room6lights.put("R1L5", false);
        room6.setLightstate(room6lights);


        list.add(room1);
        list.add(room2);
        list.add(room3);
        list.add(room4);
        list.add(room5);
        list.add(room6);


        toCheckFromAdminDashBoard = getIntent().getStringExtra("From_AdminDashboard");

        switch (toCheckFromAdminDashBoard){
            case "ControlLights":
                setUpControlLightsRecyclerView(list);

            case "SendRequest":
                setUpRecyclerView(list);
        }


    }

    private void setUpRecyclerView(ArrayList<Room> list) {
    }

    private void setUpControlLightsRecyclerView(ArrayList<Room> list) {
        ViewRoomsAdapter adapter = new ViewRoomsAdapter(list,this::onItemControlClick);
        binding.viewLightsUserRv.setLayoutManager(new LinearLayoutManager(this));
        binding.viewLightsUserRv.setAdapter(adapter);
    }

    public void onItemControlClick(Room item) {
        Intent intent = new Intent(this, ControlsLightAdminActivity.class);
        intent.putExtra("Lights", item.getLightstate());
        startActivity(intent);
    }
}