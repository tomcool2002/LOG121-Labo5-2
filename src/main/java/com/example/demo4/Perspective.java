package com.example.demo4;

import java.util.ArrayList;

public class Perspective implements ObservablePerspective {

    double x;
    double y;
    double zoom;
    ArrayList<Observer> list = new ArrayList<>();

    @Override
    public void addObserver(Observer obs) {
        list.add(obs);
    }

    @Override
    public void removeObserver(Observer obs) {
        list.remove(obs);
    }

    public double getZoom() {
        return zoom;
    }

    public void setZoom(double zoom) {
        this.zoom = zoom;
        notifyObserver();
    }

    public void setTranslation(double x, double y) {
        this.x = x;
        this.y = y;
        notifyObserver();
    }

    @Override
    public void notifyObserver() {
        for (Observer obs : list) {
            obs.update(x, y, zoom);
        }
    }
}
