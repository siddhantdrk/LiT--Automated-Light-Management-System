package com.my.lit.activities.dashboard;

import android.app.ProgressDialog;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.my.lit.R;
import com.my.lit.activities.WelcomeActivity;
import com.my.lit.activities.controlLight.ControlsLightAdminActivity;
import com.my.lit.activities.currentLighting.currentLightingAdminActivity;
import com.my.lit.api.RetrofitClient;
import com.my.lit.background.BackgroundAPIService;
import com.my.lit.databinding.ActivityAdminDashBoardBinding;
import com.my.lit.responses.ResetLightResponse;
import com.my.lit.responses.TokenErrorResponse;
import com.my.lit.storage.SharedPreferenceManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminDashBoardActivity extends AppCompatActivity {
    private ActivityAdminDashBoardBinding binding;
    private ProgressDialog mProgress;
    private static final String TAG = "AdminDashBoardActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminDashBoardBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        mProgress = new ProgressDialog(this);
        binding.adminName.setText(SharedPreferenceManager.getInstance(this).getName());
        binding.logoutBtn.setOnClickListener(this::onClick);
        binding.viewCurrentLightingBtn.setOnClickListener(this::onClick);
        binding.controlLightingBtn.setOnClickListener(this::onClick);
        binding.resetCurrentLightBtn.setOnClickListener(this::onClick);

        if (SharedPreferenceManager.getInstance(this).isAdmin()) {
            if (!SharedPreferenceManager.getInstance(this).getToken().isEmpty()) {
                scheduleJob();
            }
        }
    }

    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.logout_btn:
                SharedPreferenceManager.getInstance(this).clear();
                startActivity(new Intent(this, WelcomeActivity.class));
                Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.view_current_lighting_btn:
                Intent intent = new Intent(AdminDashBoardActivity.this, currentLightingAdminActivity.class);
                startActivity(intent);
                break;
            case R.id.control_lighting_btn:
                Intent intent2 = new Intent(AdminDashBoardActivity.this, ControlsLightAdminActivity.class);
                startActivity(intent2);
                break;
            case R.id.reset_current_light_btn:
                resetCurrentLighting();
                break;
        }
    }

    private void resetCurrentLighting() {
        mProgress.setTitle("Resetting from IOT based update...");
        mProgress.setMessage("Please wait while we fetch your data");
        mProgress.setCanceledOnTouchOutside(false);
        mProgress.show();
        String token = "token " + SharedPreferenceManager.getInstance(this).getToken();
        Call<ResetLightResponse> resetLightResponseCall = RetrofitClient.getInstance().getUserServices().resetLights(token);
        resetLightResponseCall.enqueue(new Callback<ResetLightResponse>() {
            @Override
            public void onResponse(Call<ResetLightResponse> call, Response<ResetLightResponse> response) {
                mProgress.dismiss();
                if (response.isSuccessful()) {
                    ResetLightResponse resetLightResponse = response.body();
                    Toast.makeText(AdminDashBoardActivity.this, "" + resetLightResponse.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        TokenErrorResponse tokenErrorResponse = new Gson().fromJson(response.errorBody().string(), TokenErrorResponse.class);
                        Toast.makeText(AdminDashBoardActivity.this, "" + tokenErrorResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResetLightResponse> call, Throwable t) {
                mProgress.dismiss();
                Toast.makeText(AdminDashBoardActivity.this, "Something went wrong\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void scheduleJob() {
        ComponentName componentName = new ComponentName(this, BackgroundAPIService.class);
        JobInfo info = new JobInfo.Builder(123, componentName)
                .setRequiresCharging(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true)
                .setPeriodic(3000)
                .build();
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = scheduler.schedule(info);
        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "Job scheduled");
        } else {
            Log.d(TAG, "Job scheduling failed");
        }
    }

    public void cancelJob(View v) {
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.cancel(123);
        Log.d(TAG, "Job cancelled");
    }
}