package viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;

import javax.inject.Inject;

import daggermodules.ApiCallerModule;
import core.ApplicationSingleton;
import models.WeatherBase;

public class ContentViewModel extends AndroidViewModel {

    @Inject
    ApiCallerModule apiCallerModule;

    /**
     * View model constructor
     * @param application of type {@link Application}
     */
    public ContentViewModel (Application application){
        super(application);

        //Ijects ApiCallerModule
        ApplicationSingleton.getInstance().getBaseComponents().inject(this);

        init();

    }

    /**
     * Initialization i.e. primary works
     */
    private void init(){
        apiCallerModule.invokeApiWeatherBase();
    }


    /**
     * Returns the {@link android.arch.lifecycle.LiveData} i.e. weather data provided by the server
     * @return
     */
    public MutableLiveData<WeatherBase> getContentListLiveData() {

        return apiCallerModule.getWeatherBaseMutableLiveData();
    }








}
