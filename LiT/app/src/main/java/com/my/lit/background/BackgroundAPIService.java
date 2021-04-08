package com.my.lit.background;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

import com.google.gson.Gson;
import com.my.lit.api.RetrofitClient;
import com.my.lit.responses.ResetLightResponse;
import com.my.lit.responses.TokenErrorResponse;
import com.my.lit.storage.SharedPreferenceManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BackgroundAPIService extends JobService {
    private final static String TAG = "BackgroundAPIService";
    private boolean jobCancelled = false;

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.d(TAG, "Background API job started");
        doBackgroundAPIWork(jobParameters);
        return true;
    }

    private void doBackgroundAPIWork(JobParameters jobParameters) {
        if (jobCancelled) {
            return;
        }
        resetCurrentLighting();
        Log.d(TAG, "Background API job finished");
        jobFinished(jobParameters, false);
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.d(TAG, "Background API job cancelled before completion");
        jobCancelled = true;
        return true;
    }

    private void resetCurrentLighting() {
        String token = "token " + SharedPreferenceManager.getInstance(this).getToken();
        Call<ResetLightResponse> resetLightResponseCall = RetrofitClient.getInstance().getUserServices().resetLights(token);
        resetLightResponseCall.enqueue(new Callback<ResetLightResponse>() {
            @Override
            public void onResponse(Call<ResetLightResponse> call, Response<ResetLightResponse> response) {
                if (response.isSuccessful()) {
                    ResetLightResponse resetLightResponse = response.body();
                    Log.d(TAG, "" + resetLightResponse.getMessage());
                } else {
                    try {
                        TokenErrorResponse tokenErrorResponse = new Gson().fromJson(response.errorBody().string(), TokenErrorResponse.class);
                        Log.d(TAG, "" + "" + tokenErrorResponse.getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResetLightResponse> call, Throwable t) {
                Log.d(TAG, "Something went wrong\n" + t.getMessage());
            }
        });
    }
}
