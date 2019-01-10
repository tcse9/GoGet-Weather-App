package networking;


import javax.inject.Inject;

import core.ApplicationSingleton;
import daggermodules.NetworkModule;
import retrofit2.Retrofit;

public class NetworkClient {


    @Inject
    NetworkModule networkModule;

    /**
     * Constructor
     */
    public NetworkClient(){
        //Injecting NetworkModule
        ApplicationSingleton.getInstance().getBaseComponents().inject(this);

    }

    /**
     * Returns the Retrofit client
     * @return
     */

    public Retrofit getRetrofit(){
        return networkModule.getClient().getRetrofit();
    }


}
