package com.my.lit.activities.currentLighting;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.my.lit.api.RetrofitClient;
import com.my.lit.databinding.ActivityCurrentLightingAdminBinding;
import com.my.lit.models.AreaDataItem;
import com.my.lit.responses.GetAllAreasResponse;
import com.my.lit.responses.TokenErrorResponse;
import com.my.lit.storage.SharedPreferenceManager;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class currentLightingAdminActivity extends AppCompatActivity {
    private ActivityCurrentLightingAdminBinding currentLightingAdminBinding;
    private List<AreaDataItem> areaDataItemList;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentLightingAdminBinding = ActivityCurrentLightingAdminBinding.inflate(LayoutInflater.from(this));
        setContentView(currentLightingAdminBinding.getRoot());
        mProgress = new ProgressDialog(this);
        getAllAreas();
    }

    private void getAllAreas() {
        mProgress.setTitle("Fetching Data");
        mProgress.setMessage("Please wait while we fetch your data");
        mProgress.setCanceledOnTouchOutside(false);
        mProgress.show();
        String token = "token " + SharedPreferenceManager.getInstance(this).getToken();
        Call<GetAllAreasResponse> getAllAreasResponseCall = RetrofitClient.getInstance().getUserServices().getAllAreasAdmin(token);
        getAllAreasResponseCall.enqueue(new Callback<GetAllAreasResponse>() {
            @Override
            public void onResponse(Call<GetAllAreasResponse> call, Response<GetAllAreasResponse> response) {
                mProgress.dismiss();
                if (response.isSuccessful()) {
                    GetAllAreasResponse getAllAreasResponse = response.body();
                    areaDataItemList = getAllAreasResponse.getData();
                } else {
                    try {
                        TokenErrorResponse tokenErrorResponse = new Gson().fromJson(response.errorBody().string(), TokenErrorResponse.class);
                        Toast.makeText(currentLightingAdminActivity.this, "" + tokenErrorResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllAreasResponse> call, Throwable t) {
                mProgress.dismiss();
                Toast.makeText(currentLightingAdminActivity.this, "Something went wrong\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}