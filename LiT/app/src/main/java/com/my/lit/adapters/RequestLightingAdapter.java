package com.my.lit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.my.lit.R;
import com.my.lit.api.RetrofitClient;
import com.my.lit.models.LightDataItem;
import com.my.lit.models.UpdateLightStatus;
import com.my.lit.responses.RequestLightResponse;
import com.my.lit.storage.SharedPreferenceManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        holder.onOffRdGrp.setOnCheckedChangeListener((radioGroup, i) -> {
            UpdateLightStatus status = null;
            switch (radioGroup.getCheckedRadioButtonId()) {
                case R.id.off_rd_btn:
                    status = new UpdateLightStatus(lightDataItemList.get(position).getId(), false);
                    break;

                case R.id.on_rd_btn:
                    status = new UpdateLightStatus(lightDataItemList.get(position).getId(), true);
                    break;
            }
            if (status != null)
                requestLight(status);
        });
    }

    private void requestLight(UpdateLightStatus status) {
        String token = "token " + SharedPreferenceManager.getInstance(context).getToken();
        Call<RequestLightResponse> requestLightResponseCall = RetrofitClient.getInstance().getUserServices().requestLight(status, token);
        requestLightResponseCall.enqueue(new Callback<RequestLightResponse>() {
            @Override
            public void onResponse(Call<RequestLightResponse> call, Response<RequestLightResponse> response) {
                if (response.isSuccessful()) {
                    RequestLightResponse requestLightResponse = response.body();
                    Toast.makeText(context, "Your request have been sent Successfully !", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RequestLightResponse> call, Throwable t) {
                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static class RequestLightingAdapterViewHolder extends RecyclerView.ViewHolder {
        private final TextView LightName;
        private final TextView LightStatus;
        private final ImageView Bulb;
        private final RadioGroup onOffRdGrp;

        public RequestLightingAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            LightName = itemView.findViewById(R.id.light_name);
            LightStatus = itemView.findViewById(R.id.light_status);
            Bulb = itemView.findViewById(R.id.bulb);
            onOffRdGrp = itemView.findViewById(R.id.on_off_rd_grp);
        }
    }
}





