package ru.kogut.gb_weather.model;

/**
 * Created by Sergey Kogut on 05.04.2020.
 */
public class ModelCity {

    private String id;
    private String name;
    private String state;
    private String country;
    private ModelCoord coord;

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public ModelCoord getCoord() {
        return coord;
    }

    public void setCoord(ModelCoord coord) {
        this.coord = coord;
    }
}
