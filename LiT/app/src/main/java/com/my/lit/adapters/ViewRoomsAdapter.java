package com.my.lit.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.my.lit.R;
import com.my.lit.models.AreaDataItem;

import java.util.List;

public class ViewRoomsAdapter extends RecyclerView.Adapter<ViewRoomsAdapter.ViewLightsViewHolder> {

    List<AreaDataItem> areaDataItemList;
     OnItemClickListener listener;

    public ViewRoomsAdapter(List<AreaDataItem> areaDataItemList, OnItemClickListener listener) {
        this.areaDataItemList = areaDataItemList;
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
        holder.BuildingName.setText( areaDataItemList.get(position).getBuilding());
        holder.Floor.setText(String.valueOf(areaDataItemList.get(position).getFloor()) );
        holder.Name.setText( String.valueOf(areaDataItemList.get(position).getName()));
        holder.bind(areaDataItemList.get(position),listener);
    }


    @Override
    public int getItemCount() {
        return areaDataItemList.size();
    }

    public static class ViewLightsViewHolder extends RecyclerView.ViewHolder {
        public TextView BuildingName;
        public TextView Name;
        public TextView Floor;

        public ViewLightsViewHolder(View itemView) {
            super(itemView);
            BuildingName = itemView.findViewById(R.id.building_name);
            Name = itemView.findViewById(R.id.RoomNo);
            Floor = itemView.findViewById(R.id.floor_no);


        }

        public void bind(AreaDataItem room, OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(room);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(AreaDataItem item);
    }


}






