package utils;

import com.binate.gogetweatherapp.R;

public enum WeatherColorStatus {
    CLEAR,
    CLOUDY,
    SNOW,
    RAIN;

    public static int getColorValue(WeatherColorStatus colorStatus) {
        switch (colorStatus) {
            case CLEAR:
                return R.color.colorClear;
            case CLOUDY:
                return R.color.colorCloudy;

            case SNOW:
                return R.color.colorSnow;

            case RAIN:
                return R.color.colorRainy;

            default:
                return R.color.colorClear;
        }
    }
}
