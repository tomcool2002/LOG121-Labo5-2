package com.example.demo4;

public interface ObservablePerspective {
    void addObserver(Observer obs);
    void removeObserver(Observer obs);
    void notifyObserver();
}

