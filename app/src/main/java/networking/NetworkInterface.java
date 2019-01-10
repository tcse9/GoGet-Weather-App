package networking;

import io.reactivex.Observable;
import models.WeatherBase;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NetworkInterface {

    /**
     * The method to GET the weather report for a particular location i.e. lat-long based weather report GET api
     * @param apikey
     * @param latLong
     * @return
     */
    @GET("/forecast/{api_key}/{lat_long}")
    Observable<WeatherBase> getWeatherBase(@Path("api_key")String apikey, @Path("lat_long")String latLong);
}
