package com.my.lit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.my.lit.R;
import com.my.lit.api.RetrofitClient;
import com.my.lit.models.LightDataItem;
import com.my.lit.models.UpdateLightStatus;
import com.my.lit.responses.TokenErrorResponse;
import com.my.lit.responses.UpdateLightStatusResponse;
import com.my.lit.storage.SharedPreferenceManager;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControlLightsAdapter extends RecyclerView.Adapter<ControlLightsAdapter.ViewHolder> {
    private final List<LightDataItem> lightDataItemList;
    private final Context context;
    private final int switchBtnValue = 2;

    public ControlLightsAdapter(List<LightDataItem> lightDataItemList, Context context) {
        this.lightDataItemList = lightDataItemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.control_lights_item, parent, false);

        return new ControlLightsAdapter.ViewHolder(view);
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

        holder.switchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //switch (holder.LightStatus)
                if (lightDataItem.isStatus()) {
                    updateLightStatus(new UpdateLightStatus(lightDataItem.getId(), false), holder, position);
                } else {
                    updateLightStatus(new UpdateLightStatus(lightDataItem.getId(), true), holder, position);
                }
            }
        });
    }

    private void updateLightStatus(UpdateLightStatus lightStatus, ViewHolder holder, int position) {
        String token = "token " + SharedPreferenceManager.getInstance(context).getToken();
        Call<UpdateLightStatusResponse> updateLightStatusResponseCall = RetrofitClient.getInstance().getUserServices().updateLightStatus(lightStatus, token);
        updateLightStatusResponseCall.enqueue(new Callback<UpdateLightStatusResponse>() {
            @Override
            public void onResponse(Call<UpdateLightStatusResponse> call, Response<UpdateLightStatusResponse> response) {
                if (response.isSuccessful()) {
                    UpdateLightStatusResponse statusResponse = response.body();
                    if (statusResponse.getUpdateLightStatusData().getNewLightStatus().isStatus()) {
                        lightDataItemList.get(position).setStatus(true);
                        holder.LightStatus.setBackgroundColor(context.getResources().getColor(R.color.yellow_light));
                        holder.LightStatus.setText("ON");
                        holder.LightStatus.setTextColor(context.getResources().getColor(R.color.green));
                        holder.Bulb.setImageDrawable(context.getResources().getDrawable(R.drawable.bulb));
                    } else {
                        lightDataItemList.get(position).setStatus(false);
                        holder.LightStatus.setBackgroundColor(context.getResources().getColor(R.color.white));
                        holder.LightStatus.setText("OFF");
                        holder.LightStatus.setTextColor(context.getResources().getColor(R.color.red));
                        holder.Bulb.setImageDrawable(context.getResources().getDrawable(R.drawable.off_bulb));
                    }
                    Toast.makeText(context, "Light status updated Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        TokenErrorResponse tokenErrorResponse = new Gson().fromJson(response.errorBody().string(), TokenErrorResponse.class);
                        Toast.makeText(context, "" + tokenErrorResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<UpdateLightStatusResponse> call, Throwable t) {
                Toast.makeText(context, "Something went wrong\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return lightDataItemList.size();
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
