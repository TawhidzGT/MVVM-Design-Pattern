package com.example.mvvmdesignpattern.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmdesignpattern.model.SampleApi;
import com.example.mvvmdesignpattern.repository.ApiRepository;

import java.util.List;

public class ApiViewModel extends AndroidViewModel {
    private ApiRepository apiRepository;
    private LiveData<List<SampleApi>> apiLiveDate;
    private MutableLiveData<Boolean> progressbarObserver;

    public ApiViewModel(@NonNull Application application) {
        super(application);
    }

    public void start() {
        apiRepository = new ApiRepository();
        apiRepository.get_api_list();
        apiLiveDate = apiRepository.getApiLiveData();
        progressbarObserver = apiRepository.getProgressBarData();
    }

    public LiveData<List<SampleApi>> getLiveData() {
        return apiLiveDate;
    }


    public MutableLiveData<Boolean> getProgressData() {
        return progressbarObserver;
    }
}
