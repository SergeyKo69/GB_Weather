package ru.kogut.gb_weather.service;

import ru.kogut.gb_weather.repository.WeatherRepository;

/**
 * Created by Sergey Kogut on 18.03.2020.
 */
public class WeatherService {

    private WeatherRepository weatherRepository;

    public WeatherService() {
        this.weatherRepository = new WeatherRepository();
    }

    public Integer getDegree() {
        return weatherRepository.findDegree();
    }

    public String[] findAllCities() {
        return weatherRepository.findAllCities();
    }

    public  String[] findDegreesToDays() {
        return weatherRepository.findDegreesToDays();
    }
}
