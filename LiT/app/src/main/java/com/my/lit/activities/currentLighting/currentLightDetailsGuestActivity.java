package com.my.lit.activities.currentLighting;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.my.lit.api.RetrofitClient;
import com.my.lit.databinding.ActivityCurrentLightDetailsGuestBinding;
import com.my.lit.models.LightDataItem;
import com.my.lit.responses.GetLightsByAreaIdResponse;
import com.my.lit.responses.TokenErrorResponse;
import com.my.lit.storage.SharedPreferenceManager;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class currentLightDetailsGuestActivity extends AppCompatActivity {

    private ActivityCurrentLightDetailsGuestBinding currentLightDetailsBinding;
    private List<LightDataItem> lightDataItemList;
    private ProgressDialog mProgress;
    private String areaId;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentLightDetailsBinding = ActivityCurrentLightDetailsGuestBinding.inflate(LayoutInflater.from(this));
        setContentView(currentLightDetailsBinding.getRoot());
        getLightDetails();
        areaId = getIntent().getStringExtra("Lights");


    }

    private void getLightDetails() {
        mProgress.setTitle("Fetching Data");
        mProgress.setMessage("Please wait while we fetch your data");
        mProgress.setCanceledOnTouchOutside(false);
        mProgress.show();
        token = "token " + SharedPreferenceManager.getInstance(this).getToken();
        Call<GetLightsByAreaIdResponse> getLightsByAreaIdResponseCall = RetrofitClient.getInstance().getUserServices().getLightsByAreaIdGuest(areaId, token);
        getLightsByAreaIdResponseCall.enqueue(new Callback<GetLightsByAreaIdResponse>() {
            @Override
            public void onResponse(Call<GetLightsByAreaIdResponse> call, Response<GetLightsByAreaIdResponse> response) {
                mProgress.dismiss();
                if (response.isSuccessful()) {
                    GetLightsByAreaIdResponse getLightsByAreaIdResponse = response.body();
                    lightDataItemList = getLightsByAreaIdResponse.getData();
                } else {
                    try {
                        TokenErrorResponse tokenErrorResponse = new Gson().fromJson(response.errorBody().string(), TokenErrorResponse.class);
                        Toast.makeText(currentLightDetailsGuestActivity.this, "" + tokenErrorResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetLightsByAreaIdResponse> call, Throwable t) {
                mProgress.dismiss();
                Toast.makeText(currentLightDetailsGuestActivity.this, "Something went wrong\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}