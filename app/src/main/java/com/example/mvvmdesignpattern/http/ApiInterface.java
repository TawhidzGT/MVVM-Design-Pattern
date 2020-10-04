package com.example.mvvmdesignpattern.http;

import com.example.mvvmdesignpattern.model.SampleApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("photos")
    Call<List<SampleApi>> get_api();
}
