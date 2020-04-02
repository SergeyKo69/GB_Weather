package ru.kogut.gb_weather.repository;

/**
 * Created by Sergey Kogut on 30.03.2020.
 */
public class WeatherRepository {

    public int findDegree() {
        return (int) (Math.random()*30);
    }

    public  String[] findAllCities() {
        return new String[] {"Москва", "Санкт-Петербург", "Екатеринбург", "Новосибирск", "Самара"};
    }

    public  String[] findDegreesToDays() {
        String[] arr = new String[10];
        int day = 13;
        for (int i = 0; i < 10; i++) {
            day++;
            arr[i] = day +"\n" + String.valueOf(findDegree()) + "℃";
        }
        return arr;
    }
}
