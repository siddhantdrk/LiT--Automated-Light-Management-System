package com.my.lit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.my.lit.R;
import com.my.lit.models.LightDataItem;

import java.util.List;

public class RequestLightingAdapter  extends RecyclerView.Adapter<RequestLightingAdapter.RequestLightingAdapterViewHolder>{
    List<LightDataItem> lightDataItemList ;
    Context context;

    public RequestLightingAdapter(List<LightDataItem> lightDataItemList, Context context) {
        this.lightDataItemList = lightDataItemList;
        this.context = context;
    }

    @NonNull
    @Override
    public RequestLightingAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.request_light_item,parent,false);
        return new RequestLightingAdapterViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return lightDataItemList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull RequestLightingAdapterViewHolder holder, int position) {

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

    public static class RequestLightingAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView LightName;
        TextView LightStatus;
        ImageView Bulb;
        CheckBox checkBox;

        public RequestLightingAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            LightName = itemView.findViewById(R.id.light_name);
            LightStatus = itemView.findViewById(R.id.light_status);
            Bulb = itemView.findViewById(R.id.bulb);
            checkBox = itemView.findViewById(R.id.request_checkbox);
        }
    }
}





