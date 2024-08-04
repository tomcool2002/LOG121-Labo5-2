package com.example.demo4;

import java.util.ArrayList;

public class ImageModel implements ObservableImage{

    String imgUrl;
    ArrayList<Observer> list = new ArrayList<>();
    @Override
    public void addObserver(Observer obs) {
        list.add(obs);
    }

    @Override
    public void removeObserver(Observer obs) {
        list.remove(obs);
    }

    @Override
    public void notifyObserver() {
        for (Observer obs:list) {
            obs.update(imgUrl);
        }
    }
}
