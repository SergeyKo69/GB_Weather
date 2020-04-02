package ru.kogut.gb_weather.observer;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
    private static Publisher instance = null;
    private List<IObserver> observers = new ArrayList<>();   // Все обозреватели

    private Publisher() {}

    public static Publisher getInstance() {
        if(instance == null) {
            instance = new Publisher();
        }

        return instance;
    }

    // Подписать
    public void subscribe(IObserver observer) {
        observers.add(observer);
    }

    // Отписать
    public void unsubscribe(IObserver observer) {
        observers.remove(observer);
    }

    // Разослать событие
    public void notifyMain() {
        for (IObserver observer : observers) {
            observer.update();
        }
    }
}
