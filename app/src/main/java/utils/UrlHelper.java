package utils;

public class UrlHelper {

    /**
     * Building a custom url containing '<lat>,<long>' format
     * @return
     */
    public static String getLatLongBaseUrl(){
        return Constants.LOCATION_LAT+","+Constants.LOCATION_LONG;
    }

}
