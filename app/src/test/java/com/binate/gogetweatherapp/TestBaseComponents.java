package com.binate.gogetweatherapp;

import javax.inject.Singleton;

import dagger.Component;
import daggermodules.ApiCallerModule;
import daggermodules.NetworkModule;
import networking.NetworkClient;
import viewmodels.ContentViewModel;


/**
 * {@link TestBaseComponents} are the components throughout the whole test project
 */
@Singleton
@Component (modules = {ApiCallerModule.class})
public interface TestBaseComponents {

    //Inject method for each of the module where they have been implemented
    void inject(MainActivityTest mainActivityTest);

}
