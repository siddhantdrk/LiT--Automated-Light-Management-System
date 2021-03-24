package com.my.lit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.my.lit.adapters.ViewRoomsAdapter;
import com.my.lit.databinding.ActivityViewLightsUserBinding;
import com.my.lit.models.Room;

import java.util.ArrayList;

public class ViewRoomsUser extends AppCompatActivity implements ViewRoomsAdapter.OnItemClickListener {

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
        ViewRoomsAdapter adapter = new ViewRoomsAdapter(list, (ViewRoomsAdapter.OnItemClickListener) this);
        binding.viewLightsUserRv.setLayoutManager(new LinearLayoutManager(this));
        binding.viewLightsUserRv.setAdapter(adapter);
    }

    @Override
    public void onItemClick(Room item) {
        Intent intent = new Intent(this,LightDetails.class);
        intent.putExtra("Lights", item.getLightstate());

    }
}