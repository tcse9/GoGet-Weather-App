package core;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import com.binate.gogetweatherapp.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import components.BaseComponents;
import components.DaggerBaseComponents;
import daggermodules.ApiCallerModule;
import daggermodules.NetworkModule;

public class ApplicationSingleton extends Application {

    private static ApplicationSingleton sInstance;
    private SharedPreferences mPref;
    private BaseComponents baseComponents;


    public static ApplicationSingleton getInstance() {
        return sInstance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        sInstance.initializeInstance();
    }


    /**
     * Initializes the singletone instance with other singletones.
     */
    private void initializeInstance() {

        //initializing the application preference file with MODE_PRIVATE
        mPref = this.getApplicationContext().getSharedPreferences(BuildConfig.APPLICATION_ID + "_pref", MODE_PRIVATE);

        baseComponents = DaggerBaseComponents.builder().apiCallerModule(new ApiCallerModule()).networkModule(new NetworkModule()).build();

    }

    public SharedPreferences getSharedPreference() {
        return mPref;
    }


    /**
     * Returns true if internet is connected, false otherwise.
     *
     * @return
     */

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }


    /**
     * Prints the SHA1 hash key, useful for Facebook, Google apis etc.
     *
     * @param pContext
     * @return
     */
    public String printHashKey(Context pContext) {
        String hashKey = "";
        try {
            PackageInfo info = pContext.getPackageManager().getPackageInfo(pContext.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                hashKey = new String(Base64.encode(md.digest(), 0));
            }
        } catch (NoSuchAlgorithmException e) {
            Log.e("KEY_HASH", "printHashKey()", e);
        } catch (Exception e) {
            Log.e("KEY_HASH", "printHashKey()", e);
        }
        return hashKey;
    }


    /**
     * Returns true if a URL is exists, false otherwise.
     *
     * @param URLName
     * @return
     */
    public boolean isUrlExists(String URLName) {
        try {
            HttpURLConnection.setFollowRedirects(false);
            // note : you may also need
            //        HttpURLConnection.setInstanceFollowRedirects(false)
            HttpURLConnection con =
                    (HttpURLConnection) new URL(URLName).openConnection();
            con.setRequestMethod("HEAD");
            return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Returns a {@link String} after parsing them in {@link Date} according to input and output format.
     *
     * @param inputFormat
     * @param outputFormat
     * @param value
     * @return
     */

    public String getFormattedDateString(String inputFormat, String outputFormat, String value) {

        SimpleDateFormat inputPattern = new SimpleDateFormat(inputFormat);
        SimpleDateFormat outputPattern = new SimpleDateFormat(outputFormat);

        Date date = null;
        String str = null;

        try {
            date = inputPattern.parse(value);
            str = outputPattern.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }


    /**
     * Returns int value in dp
     *
     * @param px
     * @return
     */
    public int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    /**
     * Returns int value in px
     *
     * @param dp
     * @return
     */
    public int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }


    /**
     * Returns a {@link String} as the device id i.e. ANDROID_ID.
     *
     * @return
     */
    public String getDeviceID() {
        String android_id = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return android_id;
    }


    /**
     * Returns a {@link String} after converting a {@link JsonElement} object.
     *
     * @param jsonElement
     * @return
     */
    public String convertJsonElementToString(JsonElement jsonElement) {
        Gson gson = new Gson();
        JsonElement element = gson.fromJson(jsonElement.toString(), JsonElement.class);
        JsonObject jsonObj = element.getAsJsonObject();

        return jsonObj.toString();
    }


    /**
     * Returns true if the param {@link CharSequence} is a valid email id.
     *
     * @param target
     * @return
     */
    public boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public BaseComponents getBaseComponents() {
        return baseComponents;
    }


    /**
     * Capilizes the first letter of a given word
     * @param original
     * @return
     */
    public String capitalizeFirstLetter(String original) {
        if (original == null || original.length() == 0) {
            return original;
        }
        return original.substring(0, 1).toUpperCase() + original.substring(1);
    }


    /**
     * This method converts Unix time to Date with format yyyy-MM-dd
     * @param time
     * @return
     */

    public String convertUnixTimeToDateFormat(long time){
        Date date = new java.util.Date(time*1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+10"));
        String formattedDate = sdf.format(date);

        return formattedDate;
    }

}
