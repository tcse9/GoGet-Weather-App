package components;

import javax.inject.Singleton;

import daggermodules.ApiCallerModule;
import dagger.Component;
import networking.NetworkClient;
import daggermodules.NetworkModule;
import viewmodels.ContentViewModel;


/**
 * {@link BaseComponents} are the components throughout the whole project
 */
@Singleton
@Component (modules = {NetworkModule.class, ApiCallerModule.class})
public interface BaseComponents {

    //Inject method for each of the module where they have been implemented
    void inject(NetworkClient networkClient);
    void inject(ContentViewModel viewModel);

}
