package com.example.demo4;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ThumbnailView implements Observer{

    private String imgUrl = "logo.png";
    private ImageView Iv;

    public ThumbnailView(){
        Image image = new Image(imgUrl,300,300,false,false);
        Iv = new ImageView(image);
    }

    @Override
    public void update(int x, int y, int zoom) {
        //ici fait rien avec cela car cette vue n'update pas la perspective
    }

    @Override
    public void update(String imgUrl) {
        this.imgUrl = imgUrl; // change l'image
    }

    public ImageView getView(){
        return Iv;
    }
}
