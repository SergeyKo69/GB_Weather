package ru.kogut.gb_weather.service;

/**
 * Created by Sergey Kogut on 18.03.2020.
 */
public class WeatherService {

    public Integer getDegree() {
        return (int) (Math.random()*30);
    }
}
