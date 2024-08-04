package com.example.demo4;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TranslationView implements Observer{

    private String imgUrl = "logo.png";
    private ImageView Iv;
    private int x;
    private int y;

    public TranslationView(){
        Image image = new Image(imgUrl,300,300,false,false);
        Iv = new ImageView(image);
    }

    @Override
    public void update(int x, int y, double zoom) {
        this.x=x;
        this.y=y;
        //trouver comment faire une translation sur l'image
    }

    @Override
    public void update(String imgUrl) {
        this.imgUrl=imgUrl;
        Iv.setImage(new Image(imgUrl,300,300,false,false));
    }

    public ImageView getView(){
        return Iv;
    }

}
