package daggermodules;


import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import models.WeatherBase;
import networking.NetworkClient;
import networking.NetworkInterface;
import utils.Constants;
import utils.UrlHelper;

@Module
public class ApiCallerModule {


    private final CompositeDisposable disposables = new CompositeDisposable();
    private MutableLiveData<WeatherBase> weatherBaseMutableLiveData= new MutableLiveData<WeatherBase>();
    private NetworkClient networkClient;



    /**
     * This method fetches data from server using RxJava and Retrofit
     */
    @Singleton
    @Provides
    public ApiCallerModule invokeApiWeatherBase(){
        networkClient = new NetworkClient();

        NetworkInterface networkInterface = networkClient.getRetrofit().create(NetworkInterface.class);

        disposables.add(networkInterface.getWeatherBase(Constants.API_KEY, UrlHelper.getLatLongBaseUrl())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserver()));

        return this;

    }

    /**
     * Returns an {@link DisposableObserver} type object
     * @return
     */
    private DisposableObserver<WeatherBase> getObserver(){

        return new DisposableObserver<WeatherBase>() {
            @Override
            public void onNext(WeatherBase weatherBase) {

                weatherBaseMutableLiveData.setValue(weatherBase);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("ERROR_OVI", ""+e.getMessage());
            }

            @Override
            public void onComplete() {
                this.dispose();
            }
        };
    }

    /**
     * Retunds a live data containing {@link WeatherBase} list
     * @return
     */
    @Singleton
    @Provides
    public MutableLiveData<WeatherBase> getWeatherBaseMutableLiveData() {
        return weatherBaseMutableLiveData;
    }

}
