package com.my.lit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.my.lit.R;
import com.my.lit.models.LightDataItem;

import java.util.List;

public class ViewLightsAdapter extends RecyclerView.Adapter<ViewLightsAdapter.ViewHolder> {
    private final List<LightDataItem> lightDataItemList;
    Context context;

    public ViewLightsAdapter(List<LightDataItem> lightDataItemList, Context context) {
        this.lightDataItemList = lightDataItemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.light_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LightDataItem lightDataItem = lightDataItemList.get(position);
        holder.LightName.setText(lightDataItem.getName());
        if (lightDataItem.isStatus()) {
            holder.LightStatus.setBackgroundColor(context.getResources().getColor(R.color.yellow_light));
            holder.LightStatus.setText("ON");
            holder.LightStatus.setTextColor(context.getResources().getColor(R.color.green));
        } else {
            holder.LightStatus.setBackgroundColor(context.getResources().getColor(R.color.white));
            holder.LightStatus.setText("OFF");
            holder.LightStatus.setTextColor(context.getResources().getColor(R.color.red));
            holder.Bulb.setImageResource(R.drawable.off_bulb);
        }

    }

    @Override
    public int getItemCount() {
        return lightDataItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView LightName;
        private final TextView LightStatus;
        private final ImageView Bulb;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            LightName = itemView.findViewById(R.id.light_name);
            LightStatus = itemView.findViewById(R.id.light_status);
            Bulb = itemView.findViewById(R.id.bulb);
        }
    }
}
