package com.my.lit.activities.guestRequest;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.my.lit.adapters.RequestLightingAdapter;
import com.my.lit.api.RetrofitClient;
import com.my.lit.databinding.ActivityGuestRequestLightingBinding;
import com.my.lit.models.LightDataItem;
import com.my.lit.responses.GetLightsByAreaIdResponse;
import com.my.lit.responses.TokenErrorResponse;
import com.my.lit.storage.SharedPreferenceManager;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GuestRequestLightingDetailsActivity extends AppCompatActivity {
    ActivityGuestRequestLightingBinding guestRequestLightingBinding;
    private List<LightDataItem> lightDataItemList;
    private ProgressDialog mProgress;
    private String areaId;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        guestRequestLightingBinding = com.my.lit.databinding.ActivityGuestRequestLightingBinding.inflate(LayoutInflater.from(this));
        setContentView(guestRequestLightingBinding.getRoot());
        mProgress = new ProgressDialog(this);
        areaId = getIntent().getStringExtra("AreaId");
        getLightDetails();
    }

    private void getLightDetails() {
        mProgress.setTitle("Fetching Data...");
        mProgress.setMessage("Please wait while we fetch your data");
        mProgress.setCanceledOnTouchOutside(false);
        mProgress.show();
        token = "token " + SharedPreferenceManager.getInstance(this).getToken();
        Call<GetLightsByAreaIdResponse> getLightsByAreaIdResponseCall = RetrofitClient.getInstance().getUserServices().getLightsByAreaIdAdmin(areaId, token);
        getLightsByAreaIdResponseCall.enqueue(new Callback<GetLightsByAreaIdResponse>() {
            @Override
            public void onResponse(Call<GetLightsByAreaIdResponse> call, Response<GetLightsByAreaIdResponse> response) {
                mProgress.dismiss();
                if (response.isSuccessful()) {
                    GetLightsByAreaIdResponse getLightsByAreaIdResponse = response.body();
                    lightDataItemList = getLightsByAreaIdResponse.getData();
                    setData();
                } else {
                    try {
                        TokenErrorResponse tokenErrorResponse = new Gson().fromJson(response.errorBody().string(), TokenErrorResponse.class);
                        Toast.makeText(GuestRequestLightingDetailsActivity.this, "" + tokenErrorResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetLightsByAreaIdResponse> call, Throwable t) {
                mProgress.dismiss();
                Toast.makeText(GuestRequestLightingDetailsActivity.this, "Something went wrong\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setData() {
        RequestLightingAdapter adapter = new RequestLightingAdapter(lightDataItemList, this);
        guestRequestLightingBinding.requestLightsRoomRv.setAdapter(adapter);
    }
}