package com.example.demo4;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ZoomView implements Observer{

    private String imgUrl = "logo.png";
    private int zoom=0;
    private ImageView Iv;

    public ZoomView(){
        Iv = new ImageView(new Image(imgUrl,300,300,false,false));
    }
    @Override
    public void update(int x, int y, int zoom) {
        this.zoom=zoom;
        Rectangle2D viewportReact = new Rectangle2D(0+(zoom/2),0+(zoom/2),300-zoom,300-zoom);
        Iv.setViewport(viewportReact);
    }

    @Override
    public void update(String imgUrl) {
        this.imgUrl = imgUrl; // change l'image
        Iv.setImage(new Image(imgUrl,300,300,false,false));
    }
    public ImageView getView(){
        return Iv;
    }
}
