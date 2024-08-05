package com.example.demo4;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TranslationView implements Observer {

    private String imgUrl = "logo.png";
    private ImageView imageView;
    private int x;
    private int y;

    public TranslationView() {
        Image image = new Image(imgUrl, 300, 300, false, false);
        imageView = new ImageView(image);
    }

    @Override
    public void update(Double x, Double y, double zoom) {
        imageView.setTranslateX(x);

        imageView.setTranslateY(y);
    }

    @Override
    public void update(String imgUrl) {
        this.imgUrl = imgUrl;
        imageView.setImage(new Image(imgUrl, 300, 300, false, false));
    }

    public ImageView getView() {
        return imageView;
    }
}
