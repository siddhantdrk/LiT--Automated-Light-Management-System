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

import com.google.android.material.button.MaterialButton;
import com.my.lit.R;
import com.my.lit.models.Room;

import java.util.ArrayList;

public class RequestLightingAdapter  extends RecyclerView.Adapter<RequestLightingAdapter.RequestLightingAdapterViewHolder>{
    ArrayList<String> lightNames ;
    ArrayList<Boolean> lightStatus ;
    Context context;
    OnItemClickListener listener;

    public RequestLightingAdapter(ArrayList<String> lightNames, ArrayList<Boolean> lightStatus, Context context,OnItemClickListener listener) {
        this.lightNames = lightNames;
        this.lightStatus = lightStatus;
        this.context = context;
        this.listener = listener;
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
        return lightNames.size();
    }

    @Override
    public void onBindViewHolder(@NonNull RequestLightingAdapterViewHolder holder, int position) {

        holder.LightName.setText(lightNames.get(position));
        if(lightStatus.get(position)){
            holder.LightStatus.setBackgroundColor(context.getResources().getColor(R.color.yellow_light));
            holder.LightStatus.setText("ON");
            holder.LightStatus.setTextColor(context.getResources().getColor(R.color.black));
        }
        else{
            holder.LightStatus.setBackgroundColor(context.getResources().getColor(R.color.white));
            holder.LightStatus.setText("OFF");
            holder.LightStatus.setTextColor(context.getResources().getColor(R.color.black));
            //holder.Bulb.setImageDrawable(context.getResources().getDrawable(R.drawable.off_bulb));
            holder.Bulb.setImageResource(R.drawable.off_bulb);
        }
        if(holder.checkBox.isChecked()){
            holder.checkBoxCheck(lightNames.get(position),lightStatus.get(position),  listener);
        }
        if(!holder.checkBox.isChecked()){
            holder.checkBoxUnCheck(lightNames.get(position),lightStatus.get(position),  listener);
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

        public void checkBoxCheck(String s, Boolean aBoolean, OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemCheck(s,aBoolean);
                }
            });
        }

        public void checkBoxUnCheck(String s, Boolean aBoolean, OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemUnchecked(s,aBoolean);
                }
            });
        }

        }
    public interface OnItemClickListener {
        void onItemCheck(String name , Boolean state);
        void onItemUnchecked(String name, Boolean state);
    }
}





