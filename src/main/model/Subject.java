package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {

    protected List<Observer> observers = new ArrayList<>();

    // MODIFIES: this
    // EFFECTS: adds a new observer to the list of observers
    void addObserver(Observer observer) {
        observers.add(observer);
    }

    // EFFECTS: notifies each observer of a change made to a given task
    void notifyObservers(String taskName) {
        for (Observer o : observers) {
            o.update(taskName);
        }
    }

    public List<Observer> getObservers() {
        return this.observers;
    }
}
