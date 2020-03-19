package ru.kogut.gb_weather.activitystate;

/**
 * Created by Sergey Kogut on 18.03.2020.
 */
public final class RestoreActivity {

    private static RestoreActivity instance = null;

    private static final Object syncObj = new Object();

    private Integer degree;

    public void setDegree(Integer degree) {
        this.degree = degree;
    }

    public Integer getDegree() {
        return degree;
    }

    public static RestoreActivity getInstance(){
        synchronized (syncObj) {
            if (instance == null) {
                instance = new RestoreActivity();
            }
            return instance;
        }
    }

}
