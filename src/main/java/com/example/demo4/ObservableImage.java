package com.example.demo4;

public interface ObservableImage {
    void addObserver(Observer obs);
    void removeObserver(Observer obs);
    void notifyObserver();
}
