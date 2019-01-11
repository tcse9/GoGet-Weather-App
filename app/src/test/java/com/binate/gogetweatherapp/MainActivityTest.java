package com.binate.gogetweatherapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import javax.inject.Inject;
import daggermodules.ApiCallerModule;


@RunWith(RobolectricTestRunner.class)

@Config(manifest=Config.NONE, sdk = 26, application = TestApplication.class)
public class MainActivityTest {

    @Inject
    ApiCallerModule apiCallerModule;

    @Before
    public void before(){

        TestApplication app = (TestApplication) RuntimeEnvironment.application;
        app.getOrCreateApplicationComponent().inject(this);



    }

    /**
     * This method simply test the api network call by executing apiCallerModule.invokeApiWeatherBase();
     */
    @Test
    public void test(){
        apiCallerModule.invokeApiWeatherBase();
    }


}