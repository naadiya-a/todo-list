package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {

    protected List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    void notifyObservers(String taskName) {
        for (Observer o : observers) {
            o.update(taskName);
        }
    }
}
