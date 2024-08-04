package com.example.demo4;

public interface Observer {
    void update(int x,int y,int zoom);
    void update(String imgUrl);
}
