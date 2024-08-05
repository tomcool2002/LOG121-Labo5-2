package com.example.demo4;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ZoomView implements Observer {

    private String imgUrl = "logo.png";
    private double zoom = 1.0;
    private ImageView Iv;

    public ZoomView() {
        Iv = new ImageView(new Image(imgUrl, 300, 300, false, false));
    }

    @Override
    public void update(Double x, Double y, double zoom) {
        this.zoom = zoom;
        Iv.setFitWidth(300 * zoom);
        // trouver comment appliquer un zoom sur une image
    }

    @Override
    public void update(String imgUrl) {
        this.imgUrl = imgUrl; // change l'image
        Iv.setImage(new Image(imgUrl, 300, 300, false, false));
    }

    public ImageView getView() {
        return Iv;
    }
}
