package com.example.demo4;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {


        ThumbnailView Tv = new ThumbnailView();
        ZoomView Zv = new ZoomView();
        Image image = new Image("logo.png",300,300,false,false);

        ImageView iv1 = Tv.getView();
        ImageView iv2 = Zv.getView();
        ImageView iv3 = new ImageView(image);

        iv2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Zv.update(0,0,50);
                Zv.update("logo2.png");
                System.out.println("test");
            }
        });

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

        GridPane grid = new GridPane();
        grid.setHgap(4);
        grid.setVgap(4);
        grid.setPadding(new Insets(0, 10, 0, 10));

        // create StackPane
        StackPane sPane1 = new StackPane();
        StackPane sPane2 = new StackPane();
        StackPane sPane3 = new StackPane();

        // add iv (image)
        sPane1.getChildren().add(iv1);
        sPane2.getChildren().add(iv2);
        sPane3.getChildren().add(iv3);

        grid.add(sPane1, 1, 0);
        grid.add(sPane2, 2, 0);
        grid.add(sPane3, 3, 0);

        // create a VBox
        VBox vb = new VBox();
        vb.getChildren().addAll(mb, grid);

        // create a scene
        Scene spScene = new Scene(vb, 800, 400);
        primaryStage.setScene(spScene);
        primaryStage.show();
    }

    public static void main(String args[]) {
        // launch the application
        launch(args);
    }
}