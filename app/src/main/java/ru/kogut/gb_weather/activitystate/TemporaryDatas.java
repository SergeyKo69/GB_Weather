package ru.kogut.gb_weather.activitystate;

/**
 * Created by Sergey Kogut on 18.03.2020.
 */
public final class TemporaryDatas {

    private static TemporaryDatas instance = null;

    private static final Object syncObj = new Object();

    private Integer degree;

    private boolean pressureAndSpeed;

    private boolean setTheme;

    private boolean setDergreeFahrenheit;

    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isSetTheme() {
        return setTheme;
    }

    public void setSetTheme(boolean setTheme) {
        this.setTheme = setTheme;
    }

    public boolean isSetDergreeFahrenheit() {
        return setDergreeFahrenheit;
    }

    public void setSetDergreeFahrenheit(boolean setDergreeFahrenheit) {
        this.setDergreeFahrenheit = setDergreeFahrenheit;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }

    public Integer getDegree() {
        return degree;
    }

    public boolean getPressureAndSpeed() {
        return pressureAndSpeed;
    }

    public void setPressureAndSpeed(boolean pressureAndSpeed) {
        this.pressureAndSpeed = pressureAndSpeed;
    }

    public static TemporaryDatas getInstance(){
        synchronized (syncObj) {
            if (instance == null) {
                instance = new TemporaryDatas();
            }
            return instance;
        }
    }

}
