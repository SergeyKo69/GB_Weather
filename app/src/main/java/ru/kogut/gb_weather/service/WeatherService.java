package ru.kogut.gb_weather.service;

import android.content.res.AssetManager;
import android.os.Build;

import androidx.annotation.RequiresApi;
import ru.kogut.gb_weather.controller.CityController;
import ru.kogut.gb_weather.controller.WeatherController;
import ru.kogut.gb_weather.interfaces.UpdateValuesInt;
import ru.kogut.gb_weather.repository.WeatherRepository;

/**
 * Created by Sergey Kogut on 18.03.2020.
 */
public class WeatherService {

    private WeatherRepository weatherRepository;
    private CityController cityController;
    private WeatherController weatherController;

    public WeatherService() {
        this.weatherRepository = new WeatherRepository();
        this.cityController = new CityController();
        this.weatherController = new WeatherController();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public double getDegree(String city, AssetManager assetManager, UpdateValuesInt updateValues) {
        return weatherController.findDegreeToCity(city, assetManager, updateValues);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String[] findAllCities(AssetManager assetManager) {
        return cityController.getAllCityJSON(assetManager);
    }

    public  String[] findDegreesToDays() {
        return weatherRepository.findDegreesToDays();
    }
}
