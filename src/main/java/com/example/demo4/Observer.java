package com.example.demo4;

public interface Observer {
    void update(Double x, Double y, double zoom);

    void update(String imgUrl);
}
