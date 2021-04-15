package com.my.lit.activities.currentLighting;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.my.lit.adapters.ViewLightsAdapter;
import com.my.lit.api.RetrofitClient;
import com.my.lit.databinding.ActivityCurrentLightDetailsGuestBinding;
import com.my.lit.models.LightDataItem;
import com.my.lit.responses.GetLightsByAreaIdResponse;
import com.my.lit.responses.TokenErrorResponse;
import com.my.lit.storage.SharedPreferenceManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class currentLightDetailsGuestActivity extends AppCompatActivity {

    private ActivityCurrentLightDetailsGuestBinding currentLightDetailsGuestBinding;
    private List<LightDataItem> lightDataItemList;
    private ProgressDialog mProgress;
    private String areaId;
    private String token;
    private ViewLightsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentLightDetailsGuestBinding = ActivityCurrentLightDetailsGuestBinding.inflate(LayoutInflater.from(this));
        setContentView(currentLightDetailsGuestBinding.getRoot());
        mProgress = new ProgressDialog(this);
        areaId = getIntent().getStringExtra("AreaId");
        getLightDetails();
        searchSetUp();
    }

    private void searchSetUp() {
        currentLightDetailsGuestBinding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                filter(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.isEmpty()) {
                    adapter.filterLights(lightDataItemList);
                } else {
                    filter(s);
                }
                return true;
            }
        });
    }

    private void filter(String s) {
        List<LightDataItem> filteredLightDataItemList = new ArrayList<>();
        for (LightDataItem lightDataItem : lightDataItemList) {
            if (lightDataItem.getName().toLowerCase().contains(s.toLowerCase())) {
                filteredLightDataItemList.add(lightDataItem);
            }
        }
        if (filteredLightDataItemList.size() == 0) {
            Toast.makeText(this, "Sorry!! No data Found", Toast.LENGTH_SHORT).show();
        }
        adapter.filterLights(filteredLightDataItemList);
    }

    private void getLightDetails() {
        mProgress.setTitle("Fetching Data...");
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
                    setData();
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

    private void setData() {
        adapter = new ViewLightsAdapter(lightDataItemList, this);
        currentLightDetailsGuestBinding.lightDetailsRv.setAdapter(adapter);
    }
}