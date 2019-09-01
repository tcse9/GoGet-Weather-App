package viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;

import core.ApplicationSingleton;
import daggermodules.ApiCallerModule;
import models.WeatherBase;

public class ContentViewModel extends AndroidViewModel {

    @Inject
    ApiCallerModule apiCallerModule;
    private MutableLiveData<String> currentTemperature = new MutableLiveData<>();

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
     * Returns the {@link androidx.lifecycle.LiveData} i.e. weather data provided by the server
     * @return
     */
    public MutableLiveData<WeatherBase> getContentListLiveData() {

        return apiCallerModule.getWeatherBaseMutableLiveData();
    }

    /**
     * Returns the internet connectivity status
     * @return
     */
    public boolean isInternetConnected(){
        return apiCallerModule.isInternetConnected();
    }




}
