package com.example.mvvmdesignpattern.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.example.mvvmdesignpattern.R;
import com.example.mvvmdesignpattern.model.SampleApi;
import com.example.mvvmdesignpattern.recyclerAdapter.ApiRecyclerViewAdapter;
import com.example.mvvmdesignpattern.viewmodel.ApiViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ApiViewModel apiViewModel;
    List<SampleApi> sampleApiList = new ArrayList<>();
    ApiRecyclerViewAdapter apiRecyclerViewAdapter;
    ProgressDialog progressBarDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiViewModel = new ViewModelProvider(this).get(ApiViewModel.class);

        apiViewModel.start();
        apiCallCheck();
        recycler_list();
    }

    private void apiCallCheck() {
        progressBarDialog = new ProgressDialog(this);

        apiViewModel.getProgressData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(final Boolean progressObserve) {
                if (progressObserve) {
                    progressBarDialog.setCancelable(false);
                    progressBarDialog.setMessage("Api loading ");
                    progressBarDialog.show();
                } else {
                    progressBarDialog.cancel();
                }
            }
        });

        apiViewModel.getLiveData().observe(this, new Observer<List<SampleApi>>() {
            @Override
            public void onChanged(List<SampleApi> Response) {
                if (Response != null) {
                    Log.d("item size", Response.size() + "");
                    sampleApiList.clear();
                    sampleApiList.addAll(Response);
                    apiRecyclerViewAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    public void recycler_list() {
        apiRecyclerViewAdapter = new ApiRecyclerViewAdapter(sampleApiList, this);
        RecyclerView apiRecyclerView= findViewById(R.id.recyclerview);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        apiRecyclerView.setLayoutManager(mLayoutManager);
        apiRecyclerView.setItemAnimator(new DefaultItemAnimator());
        apiRecyclerView.setAdapter(apiRecyclerViewAdapter);
    }
}
