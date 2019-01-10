package daggermodules;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import utils.Constants;

@Module
public class NetworkModule {

    private Retrofit retrofit;


    /**
     * Returns the api client built by OkHttpClient, this can be customizable according to need
     * @return
     */
    @Singleton
    @Provides
    public  NetworkModule getClient(){

        if(retrofit==null){
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            OkHttpClient okHttpClient = builder.build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();

        }

        return this;
    }

    /**
     * Returns the Retrofit client
     * @return
     */
    @Singleton
    @Provides
    public Retrofit getRetrofit() {
        return retrofit;
    }
}
