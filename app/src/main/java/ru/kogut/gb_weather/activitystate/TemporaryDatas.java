package ru.kogut.gb_weather.activitystate;

import androidx.fragment.app.Fragment;

/**
 * Created by Sergey Kogut on 18.03.2020.
 */
public final class TemporaryDatas {

    private static TemporaryDatas instance = null;

    private static final Object syncObj = new Object();

    private Integer degree;

    private Fragment pressureAndSpeedFragment;

    private boolean pressureAndSpeed;

    private boolean setTheme;

    private boolean setDergreeFahrenheit;

    private String city;

    public String getCity() {
        return city;
    }

    public Fragment getPressureAndSpeedFragment() {
        return pressureAndSpeedFragment;
    }

    public void setPressureAndSpeedFragment(Fragment pressureAndSpeedFragment) {
        this.pressureAndSpeedFragment = pressureAndSpeedFragment;
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
