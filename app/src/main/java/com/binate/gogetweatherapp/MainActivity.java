package com.binate.gogetweatherapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.binate.gogetweatherapp.databinding.ActivityMainBinding;

import adapters.WeatherAdapter;
import binders.UiManager;
import core.ApplicationSingleton;
import models.WeatherBase;
import utils.WeatherColorStatus;
import viewmodels.ContentViewModel;

public class MainActivity extends AppCompatActivity {

    private ContentViewModel contentViewModel;
    private ActivityMainBinding binding;
    private UiManager uiManager;
    private WeatherAdapter adapter;
    private boolean isShoingMore = false;

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

        uiManager.setLayoutRootVisibility(View.GONE);

        contentViewModel.getContentListLiveData().observe(this, new Observer<WeatherBase>() {
            @Override
            public void onChanged(@Nullable WeatherBase weatherBase) {
                if(weatherBase != null){
                    Log.e("WEATHER", "is: "+weatherBase.getCurrently().getSummary());

                    uiManager.setLoadingProgressBarVisibility(View.GONE);
                    uiManager.setLayoutRootVisibility(View.VISIBLE);
                    populateData(weatherBase);

                }
            }
        });




    }

    private void populateData(WeatherBase weatherBase){

        //Populating current t emperature
        uiManager.setCurrentTemperature(String.valueOf(weatherBase.getCurrently().getTemperature())+" \u2109");


        //Populating today's temperature and weather status
        uiManager.setTodaysTemperature(String.valueOf(weatherBase.getDaily().getData().get(0).getTemperatureHigh())+"/"+
                String.valueOf(weatherBase.getDaily().getData().get(0).getTemperatureLow())+" \u2109");
        uiManager.setStatusToday(ApplicationSingleton.getInstance().capitalizeFirstLetter(weatherBase.getDaily().getData().get(0).getIcon()));


        //Populating tomorrow's temperature and weather status
        uiManager.setTomorrowsTemperature(String.valueOf(weatherBase.getDaily().getData().get(1).getTemperatureHigh())+"/"+
                String.valueOf(weatherBase.getDaily().getData().get(1).getTemperatureLow())+" \u2109");
        uiManager.setStatusTomorrow(ApplicationSingleton.getInstance().capitalizeFirstLetter(weatherBase.getDaily().getData().get(1).getIcon()));


        //Populating day after tomorrow's temperature and weather status
        uiManager.setStatusDayAfterTomorrow(String.valueOf(weatherBase.getDaily().getData().get(1).getTemperatureHigh())+"/"+
                String.valueOf(weatherBase.getDaily().getData().get(2).getTemperatureLow())+" \u2109");
        uiManager.setStatusDayAfterTomorrow(ApplicationSingleton.getInstance().capitalizeFirstLetter(weatherBase.getDaily().getData().get(2).getIcon()));


        //Populating background color according to the weather right now
        if(weatherBase.getCurrently().getIcon().contains("rain")){
            uiManager.setBackgroundColorRootLayout(ContextCompat.getColor(this, WeatherColorStatus.getColorValue(WeatherColorStatus.RAIN)));
        }else if(weatherBase.getCurrently().getIcon().contains("cloud")){
            uiManager.setBackgroundColorRootLayout(ContextCompat.getColor(this, WeatherColorStatus.getColorValue(WeatherColorStatus.CLOUDY)));
        }else if(weatherBase.getCurrently().getIcon().contains("clear")){
            uiManager.setBackgroundColorRootLayout(ContextCompat.getColor(this, WeatherColorStatus.getColorValue(WeatherColorStatus.CLEAR)));
        } else if(weatherBase.getCurrently().getIcon().contains("snow")){
            uiManager.setBackgroundColorRootLayout(ContextCompat.getColor(this, WeatherColorStatus.getColorValue(WeatherColorStatus.SNOW)));
        }


        //Initiating adapter and attaching it to recyclerview
        adapter =  new WeatherAdapter(weatherBase.getDaily().getData(), this);
        binding.recyclerView.setNestedScrollingEnabled(false);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        //Show more/less data functionality
        binding.txtShowMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isShoingMore = !isShoingMore;
                uiManager.setShowMore(isShoingMore);

                if(isShoingMore){
                    uiManager.setRecyclerViewVisibility(View.VISIBLE);
                }else {
                    uiManager.setRecyclerViewVisibility(View.GONE);
                }
            }
        });

    }
}
