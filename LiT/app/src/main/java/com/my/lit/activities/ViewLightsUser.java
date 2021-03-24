package com.my.lit.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.my.lit.R;
import com.my.lit.Room;
import com.my.lit.adapters.ViewLightsAdapter;
import com.my.lit.databinding.ActivityViewLightsUserBinding;

import java.util.ArrayList;

public class ViewLightsUser extends AppCompatActivity {

    ActivityViewLightsUserBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewLightsUserBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        //Dummy list items
        ArrayList<Room> list = new ArrayList<>();
        Room room1 = new Room(5223,"CCC3" , 2);
        Room room2 = new Room(5323,"CCC3" , 3);
        Room room3 = new Room(5423,"CCC3" ,4);
        list.add(room1);
        list.add(room2);
        list.add(room3);

        setUpRecyclerView(list);
    }

    private void setUpRecyclerView(ArrayList<Room> list) {
        ViewLightsAdapter adapter = new ViewLightsAdapter(list);
        binding.viewLightsUserRv.setLayoutManager(new LinearLayoutManager(this));
        binding.viewLightsUserRv.setAdapter(adapter);
    }
}