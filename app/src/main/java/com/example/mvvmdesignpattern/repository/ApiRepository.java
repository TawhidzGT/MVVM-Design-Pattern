package com.example.mvvmdesignpattern.repository;

import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmdesignpattern.http.ApiClient;
import com.example.mvvmdesignpattern.http.ApiInterface;
import com.example.mvvmdesignpattern.model.SampleApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiRepository {
    private ApiInterface apiInterface= ApiClient.getBaseClient().create(ApiInterface.class);

    private MutableLiveData<List<SampleApi>> sampleMutableData = new MutableLiveData<>();
    private MutableLiveData<Boolean> progressbarObserver = new MutableLiveData<>();

    public void get_api_list() {

        progressbarObserver.postValue(true);
        try {
            try {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        Call<List<SampleApi>> call = apiInterface.get_api();
                        call.enqueue(new Callback<List<SampleApi>>() {
                            @Override
                            public void onResponse(@NonNull Call<List<SampleApi>> call, @NonNull Response<List<SampleApi>> response) {
                                progressbarObserver.postValue(false);
                                if (response.body() != null) {
                                    Log.d("response size ", response.body().get(1).getUrl() + "");
                                    sampleMutableData.postValue(response.body());
                                }
                            }

                            @Override
                            public void onFailure(@NonNull Call<List<SampleApi>> call, @NonNull Throwable t) {
                                progressbarObserver.postValue(false);
                                Log.d("Error ", t.toString());
                            }
                        });
                    }
                }, 5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            Log.d("Error Line Number", Log.getStackTraceString(e));
        }
    }

    public LiveData<List<SampleApi>> getApiLiveData() {
        return sampleMutableData;
    }

    public MutableLiveData<Boolean> getProgressBarData() {
        return progressbarObserver;
    }
}
