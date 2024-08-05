package com.example.demo4;

public class Controller {
    ImageModel Im;
    Perspective p;

    public Controller(ImageModel im, Perspective p) {
        this.Im = im;
        this.p = p;
    }

    public void ZoomIn() {
        p.setZoom(p.getZoom() + 0.5);
    }

    public void ZoomOut() {
        if (p.getZoom() > 0.5) {
            p.setZoom(p.getZoom() - 0.5);
        }

    }

    public void Translation(double x, double y) {
        p.setTranslation(x, y);
    }
    // rajouter les actions
}
