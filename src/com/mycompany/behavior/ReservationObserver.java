package com.mycompany.behavior;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que impelementa patrón de comportamiento Observer
 */
public class ReservationObserver {
    private List<Observer> observers = new ArrayList<>();

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
