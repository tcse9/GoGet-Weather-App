package com.binate.gogetweatherapp;
import core.ApplicationSingleton;
import daggermodules.ApiCallerModule;

/**
 * Application class for testing purpose
 */
public class TestApplication extends ApplicationSingleton {

    private TestBaseComponents mApplicationComponent;

    /**
     * Returns the application dagger components
     * @return
     */
    public TestBaseComponents getOrCreateApplicationComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerTestBaseComponents.builder()
                    .apiCallerModule(new ApiCallerModule())
                    .build();
        }
        return mApplicationComponent;
    }
}
