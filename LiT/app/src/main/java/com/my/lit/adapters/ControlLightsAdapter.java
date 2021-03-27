package com.my.lit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.my.lit.R;

import java.util.ArrayList;

public class ControlLightsAdapter extends RecyclerView.Adapter<ControlLightsAdapter.ViewHolder>{
    ArrayList<String> lightNames ;
    ArrayList<Boolean> lightStatus ;
    Context context;
    int switchBtnValue =2;



    public ControlLightsAdapter(Context context, ArrayList<String> lightnames, ArrayList<Boolean> lightstatus) {
        this.lightNames = lightnames;
        this.lightStatus = lightstatus;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.control_lights_item,parent,false);

        return new ControlLightsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
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
            holder.Bulb.setImageResource(R.drawable.off_bulb);
        }
        if(holder.LightStatus.equals("ON")){
            switchBtnValue=1;
        }else{
            switchBtnValue=0;
        }
        holder.switchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //switch (holder.LightStatus)
                if(switchBtnValue==1){
                    holder.LightStatus.setBackgroundColor(context.getResources().getColor(R.color.white));
                    holder.LightStatus.setText("OFF");
                    holder.LightStatus.setTextColor(context.getResources().getColor(R.color.black));
                    holder.Bulb.setImageDrawable(context.getResources().getDrawable(R.drawable.off_bulb));
                    switchBtnValue=0;
                }else {
                    holder.LightStatus.setBackgroundColor(context.getResources().getColor(R.color.yellow_light));
                    holder.LightStatus.setText("ON");
                    holder.LightStatus.setTextColor(context.getResources().getColor(R.color.black));
                    holder.Bulb.setImageDrawable(context.getResources().getDrawable(R.drawable.bulb));
                    switchBtnValue=1;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return lightNames.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView LightName;
        TextView LightStatus;
        ImageView Bulb;
        MaterialButton switchBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            LightName = itemView.findViewById(R.id.light_name);
            LightStatus = itemView.findViewById(R.id.light_status);
            Bulb = itemView.findViewById(R.id.bulb);
            switchBtn = itemView.findViewById(R.id.switch_btn);
        }
    }
}
