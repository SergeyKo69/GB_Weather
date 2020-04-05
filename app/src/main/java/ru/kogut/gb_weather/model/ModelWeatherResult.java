package ru.kogut.gb_weather.model;

/**
 * Created by Sergey Kogut on 05.04.2020.
 */
public class ModelWeatherResult {

    private ModelCoord coord;
    private ModelWeather[] weather;
    private String base;
    private ModelMain main;
    private String visibility;
    private ModelWind wind;
    private ModelClouds clouds;
    private String dt;
    private ModelSys sys;
    private String timezone;
    private String id;
    private String name;
    private String cod;

    public ModelCoord getCoord() {
        return coord;
    }

    public void setCoord(ModelCoord coord) {
        this.coord = coord;
    }

    public ModelWeather[] getWeather() {
        return weather;
    }

    public void setWeather(ModelWeather[] weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public ModelMain getMain() {
        return main;
    }

    public void setMain(ModelMain main) {
        this.main = main;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public ModelWind getWind() {
        return wind;
    }

    public void setWind(ModelWind wind) {
        this.wind = wind;
    }

    public ModelClouds getClouds() {
        return clouds;
    }

    public void setClouds(ModelClouds clouds) {
        this.clouds = clouds;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public ModelSys getSys() {
        return sys;
    }

    public void setSys(ModelSys sys) {
        this.sys = sys;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }
}
