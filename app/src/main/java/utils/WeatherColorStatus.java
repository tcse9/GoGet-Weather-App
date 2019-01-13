package utils;

import com.binate.gogetweatherapp.R;

/**
 * This enum is for maintaining the color status per weather type i,e. icon
 */
public enum WeatherColorStatus {
    CLEAR, CLOUDY, SNOW, RAIN, WIND, FOG;

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

            case WIND:
                return R.color.colorWind;

            case FOG:
                return R.color.colorFog;

            default:
                return R.color.colorClear;
        }
    }
}
