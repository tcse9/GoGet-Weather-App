package binders;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.binate.gogetweatherapp.BR;

public class UiManager extends BaseObservable {


    public int isLoadingProgressBar = View.VISIBLE;
    public String currentTemperature = "";
    public String todaysTemperature = "";
    public String tomorrowsTemperature = "";
    public int layoutRootVisibility = View.GONE;
    public String statusToday = "";
    public String statusTomorrow = "";
    public String statusDayAfterTomorrow = "";
    public int backgroundColorRootLayout = 0;
    public String showMore = "[+] Show More";
    public int recyclerViewVisibility = View.GONE;

    /**
     * Method to set progressbar visibility statusToday
     * @param isLoading
     */
    public void setLoadingProgressBarVisibility(int isLoading){
        this.isLoadingProgressBar = isLoading;
        notifyPropertyChanged(BR._all);
    }


    /**
     * Returns an integer value which determines if the progressbar will be shown or not
     * @return
     */
    @Bindable
    public int getLoadingProgressBarVisibility(){
        return this.isLoadingProgressBar;
    }


    /**
     * Method to set current temperature
     * @param currentTemperature
     */
    public void setCurrentTemperature(String currentTemperature){
        this.currentTemperature = currentTemperature;
        notifyPropertyChanged(BR._all);

    }

    /**
     * Returns a string value which determines current temperature
     * @return
     */
    @Bindable
    public String getCurrentTemperature(){
        return this.currentTemperature;
    }

    /**
     * Method to set today's temperature
     * @param todaysTemperature
     */
    public void setTodaysTemperature(String todaysTemperature){
        this.todaysTemperature = todaysTemperature;
        notifyPropertyChanged(BR._all);
    }

    /**
     * Returns a string value which determines today's temperature
     * @return
     */
    @Bindable
    public String getTodaysTemperature(){
        return this.todaysTemperature;
    }

    /**
     * Method to set tomorrow's temperature
     * @param tomorrowsTemperature
     */
    public void setTomorrowsTemperature(String tomorrowsTemperature) {
        this.tomorrowsTemperature = tomorrowsTemperature;
        notifyPropertyChanged(BR._all);
    }


    /**
     * Returns a string value which determines tomorrow's temperature
     * @return
     */
    @Bindable
    public String getTomorrowsTemperature() {
        return tomorrowsTemperature;
    }

    /**
     * Method to set root layout visibility while loading data
     * @param layoutRootVisibility
     */
    public void setLayoutRootVisibility(int layoutRootVisibility) {
        this.layoutRootVisibility = layoutRootVisibility;
        notifyPropertyChanged(BR._all);
    }


    /**
     * Returns the statusToday of the layout root's visibility
     * @return
     */
    public int getLayoutRootVisibility() {
        return layoutRootVisibility;
    }

    /**
     * Method to set statusToday of the weather
     * @param statusToday
     */

    public void setStatusToday(String statusToday) {
        this.statusToday = statusToday;
        notifyPropertyChanged(BR._all);
    }

    /**
     * Returns the statusToday of the weather
     * @return
     */

    public String getStatusToday() {
        return statusToday;
    }


    /**
     * Method to set statusTomorrow of the weather
     * @param statusTomorrow
     */
    public void setStatusTomorrow(String statusTomorrow) {
        this.statusTomorrow = statusTomorrow;
        notifyPropertyChanged(BR._all);
    }


    /**
     * Returns the statusTomorrow of the weather
     * @return
     */
    public String getStatusTomorrow() {
        return statusTomorrow;
    }

    /**
     * Method to set statusDayAfterTomorrow of the weather
     * @param statusDayAfterTomorrow
     */
    public void setStatusDayAfterTomorrow(String statusDayAfterTomorrow) {
        this.statusDayAfterTomorrow = statusDayAfterTomorrow;
        notifyPropertyChanged(BR._all);
    }


    /**
     * Returns the statusDayAfterTomorrow of the weather
     * @return
     */
    public String getStatusDayAfterTomorrow() {
        return statusDayAfterTomorrow;
    }


    /**
     * Method to set the background color of root layout according to the weather status
     * @param backgroundColorRootLayout
     */
    public void setBackgroundColorRootLayout(int backgroundColorRootLayout) {
        this.backgroundColorRootLayout = backgroundColorRootLayout;
        notifyPropertyChanged(BR._all);
    }


    /**
     * Returns the background color of the root layout
     * @return
     */
    public int getBackgroundColorRootLayout() {
        return backgroundColorRootLayout;
    }


    /**
     * Returns teh current show more status
     * @return
     */
    public String getShowMore() {
        return showMore;
    }

    /**
     * Sets the show more functionality
     * @param isShowing
     */
    public void setShowMore(boolean isShowing) {
        if(isShowing){
            this.showMore = "[-] Show Less";
        }else {
            this.showMore = "[+] Show More";
        }
        notifyPropertyChanged(BR._all);
    }


    /**
     * Returns the recyclerview ccurrent visibility status
     * @return
     */
    public int getRecyclerViewVisibility() {
        return recyclerViewVisibility;
    }

    /**
     * Sets the recyclerview visibility status
     * @param recyclerViewVisibility
     */
    public void setRecyclerViewVisibility(int recyclerViewVisibility) {
        this.recyclerViewVisibility = recyclerViewVisibility;
        notifyPropertyChanged(BR._all);
    }
}
