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

public class ViewLightsAdapter extends RecyclerView.Adapter<ViewLightsAdapter.ViewLightsViewHolder> {

    ArrayList<Room> list;

    public ViewLightsAdapter(ArrayList<Room> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewLightsAdapter.ViewLightsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.room_item,parent,false);
        return new ViewLightsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewLightsAdapter.ViewLightsViewHolder holder, int position) {
        holder.BuildingName.setText((CharSequence) list.get(position).toString());
        holder.Floor.setText(list.get(position).toString());
        holder.RoomNo.setText(list.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return 0;
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
    }
}
