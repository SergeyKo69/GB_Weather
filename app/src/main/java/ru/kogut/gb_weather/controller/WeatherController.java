package ru.kogut.gb_weather.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Properties;
import java.util.stream.Collectors;

import android.content.res.AssetManager;
import android.os.Build;
import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;

import javax.net.ssl.HttpsURLConnection;

import androidx.annotation.RequiresApi;
import ru.kogut.gb_weather.activitystate.TemporaryDatas;
import ru.kogut.gb_weather.interfaces.UpdateValuesInt;
import ru.kogut.gb_weather.model.ModelWeatherResult;
import ru.kogut.gb_weather.observer.Publisher;

/**
 * Created by Sergey Kogut on 05.04.2020.
 */
public class WeatherController {

    private final String PROPERTY_FILE_NAME = "main.properties";
    private final String PROPERTY_WEATHER_URL = "weather_url";
    private final String PROPERTY_WEATHER_API_KEY = "weather_api_key";
    private final double KELVIN_DEGREES_CELSIUS = 273.15;

    private String TAG = "Weather controller";

    @RequiresApi(api = Build.VERSION_CODES.N)
    public double findDegreeToCity(String city, AssetManager assetManager, UpdateValuesInt updateValues) {
        Properties properties = new Properties();
        try {
            properties.load(assetManager.open(PROPERTY_FILE_NAME));
        } catch (IOException e) {
            Log.e(TAG, "Property file is found", e);
            e.printStackTrace();
        }
        String weather_url = properties.getProperty(PROPERTY_WEATHER_URL);
        if (weather_url == null || weather_url.isEmpty()) {
            Log.e(TAG, "Property weather_url found");
            return -1;
        }
        String weather_api_key = properties.getProperty(PROPERTY_WEATHER_API_KEY);
        if (weather_api_key == null || weather_api_key.isEmpty()) {
            Log.e(TAG, "Property weather_api_key found");
            return -1;
        }
        try {
            final URL uri = new URL(weather_url + city + "&appid=" + weather_api_key);
            final Handler handler = new Handler();
            new Thread(new Runnable() {
                public void run() {
                    HttpsURLConnection urlConnection = null;
                    try {
                        urlConnection = (HttpsURLConnection) uri.openConnection();
                        urlConnection.setRequestMethod("GET");
                        urlConnection.setReadTimeout(10000);
                        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream())); // читаем  данные в поток
                        String result = getLines(in);
                        Gson gson = new Gson();
                        final ModelWeatherResult weatherResult = gson.fromJson(result, ModelWeatherResult.class);
                        if (weatherResult != null && weatherResult.getMain() != null) {
                            TemporaryDatas temporaryDatas = TemporaryDatas.getInstance();
                            DecimalFormat df2 = new DecimalFormat("#.#");
                            temporaryDatas.setDegree(Double.parseDouble(df2.format(weatherResult.getMain().getTemp() - KELVIN_DEGREES_CELSIUS)));
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    updateValues.updateDegrees();
                                }
                            });
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "Fail connection", e);
                        e.printStackTrace();
                    } finally {
                        if (null != urlConnection) {
                            urlConnection.disconnect();
                        }
                    }
                }
            }).start();
        } catch (MalformedURLException e) {
            Log.e(TAG, "Fail URI", e);
            e.printStackTrace();
        }

        TemporaryDatas temporaryDatas = TemporaryDatas.getInstance();
        if (temporaryDatas.getDegree() == null) {
            return -999;
        }
        return temporaryDatas.getDegree();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String getLines(BufferedReader in) {
        return in.lines().collect(Collectors.joining("\n"));
    }

}
