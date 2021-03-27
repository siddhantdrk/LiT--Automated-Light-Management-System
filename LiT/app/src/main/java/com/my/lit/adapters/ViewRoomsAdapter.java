package com.my.lit.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.my.lit.R;
import com.my.lit.models.Room;

import java.util.ArrayList;

public class ViewRoomsAdapter extends RecyclerView.Adapter<ViewRoomsAdapter.ViewLightsViewHolder> {

    ArrayList<Room> list;
     OnItemClickListener listener;

    public ViewRoomsAdapter(ArrayList<Room> list, OnItemClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewRoomsAdapter.ViewLightsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.room_item,parent,false);


        return new ViewLightsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewLightsViewHolder holder, int position) {
        holder.BuildingName.setText( list.get(position).getBuilding());
        holder.Floor.setText(String.valueOf(list.get(position).getFloor()) );
        holder.RoomNo.setText( String.valueOf(list.get(position).getRoom_no()));
        holder.bind(list.get(position),listener);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewLightsViewHolder extends RecyclerView.ViewHolder {
        public TextView BuildingName;
        public TextView RoomNo;
        public TextView Floor;

        public ViewLightsViewHolder(View itemView) {
            super(itemView);
            BuildingName = itemView.findViewById(R.id.building_name);
            RoomNo = itemView.findViewById(R.id.RoomNo);
            Floor = itemView.findViewById(R.id.floor_no);


        }

        public void bind(Room room, OnItemClickListener listener) {
//                BuildingName.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(room);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Room item);
    }


}






