package com.example.demo4;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {

        Image image = new Image("logo.png", 300, 300, false, false);

        Perspective pers = new Perspective();// les 2 modeles
        ImageModel img = new ImageModel();

        Controller cont = new Controller(img, pers);// le controlleur

        ThumbnailView Tv = new ThumbnailView();// les 3 vues
        ZoomView Zv = new ZoomView();
        TranslationView Tv2 = new TranslationView();

        img.addObserver(Tv);// abonnement
        img.addObserver(Zv);
        img.addObserver(Tv2);

        pers.addObserver(Zv);
        pers.addObserver(Tv2);

        ImageView iv1 = Tv.getView();
        ImageView iv2 = Zv.getView();
        ImageView iv3 = Tv2.getView();

        iv2.setPreserveRatio(true);
        iv2.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent scrollEvent) {

                if (scrollEvent.getDeltaY() > 0) { // zoom in
                    cont.ZoomIn();
                }
                if (scrollEvent.getDeltaY() < 0) { // zoom out
                    cont.ZoomOut();
                }
            }
        });

        iv3.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent scrollEvent) {
                cont.Translation(scrollEvent.getSceneX() / 7, scrollEvent.getSceneY() / 7);
            }
        });

        var root = new BorderPane();
        root.setLeft(iv1);
        root.setCenter(iv2);
        root.setRight(iv3);

        // set title for the stage
        primaryStage.setTitle("Application Edit Image");

        // create a menu
        Menu m = new Menu("Fichier");

        // create menuitems
        MenuItem m1 = new MenuItem("Choisir");
        MenuItem m2 = new MenuItem("Sauvegarder");
        MenuItem m3 = new MenuItem("Choisir XML");
        MenuItem m4 = new MenuItem("Quitter");
        // add menu items to menu
        m.getItems().add(m1);
        m.getItems().add(m2);
        m.getItems().add(m3);
        m.getItems().add(m4);

        // create a menubar
        MenuBar mb = new MenuBar();

        // add menu to menubar
        mb.getMenus().add(m);

        // create a VBox
        VBox vb = new VBox();
        vb.getChildren().addAll(mb, root);

        // create a scene
        Scene spScene = new Scene(vb);
        primaryStage.setScene(spScene);
        primaryStage.show();
    }

    public static void main(String args[]) {
        // launch the application
        launch(args);
    }
}