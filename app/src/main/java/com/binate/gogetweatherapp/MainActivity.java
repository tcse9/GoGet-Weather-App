package com.binate.gogetweatherapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;


import com.binate.gogetweatherapp.databinding.ActivityMainBinding;

import binders.UiManager;
import models.WeatherBase;
import viewmodels.ContentViewModel;

public class MainActivity extends AppCompatActivity {

    private ContentViewModel contentViewModel;
    private ActivityMainBinding binding;
    private UiManager uiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Initializing data binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        uiManager = new UiManager();
        binding.setUimanager(uiManager);


        //Initializing viewmodel
        contentViewModel = ViewModelProviders.of(this).get(ContentViewModel.class);

        initAction();
    }



    /**
     * This method handles actions on  views
     */
    private void initAction(){

        contentViewModel.getContentListLiveData().observe(this, new Observer<WeatherBase>() {
            @Override
            public void onChanged(@Nullable WeatherBase weatherBase) {
                if(weatherBase != null){
                    Log.e("WEATHER", "is: "+weatherBase.getCurrently().getSummary());

                    uiManager.setLoadingProgressBarVisibility(View.GONE);
                }
            }
        });


    }
}
