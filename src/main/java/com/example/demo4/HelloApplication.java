package com.example.demo4;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.FileOutputStream;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.fxml.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

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
        System.out.println("mouse click detected! ");

        m1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent mouseEvent) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("All Images", "*.*"),
                        new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                        new FileChooser.ExtensionFilter("PNG", "*.png"));
                File file = fileChooser.showOpenDialog(primaryStage);
                if (file != null) {
                    Tv.update(file.getAbsolutePath());
                    Zv.update(file.getAbsolutePath());
                    Tv2.update(file.getAbsolutePath());
                }
            }
        });

        m2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent mouseEvent) {
                XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();

                try {
                    FileOutputStream fileOutputStream = new FileOutputStream("LOG121-LABO5-2");
                    XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(fileOutputStream, "UTF-8");
                    // En-tête du document XML
                    xmlStreamWriter.writeStartDocument("UTF-8", "1.0");

                    // Element racine
                    xmlStreamWriter.writeStartElement("fenetres");
                    xmlStreamWriter.writeAttribute("Path", Tv.getImgUml());

                    xmlStreamWriter.writeStartElement("image");

                    // Attributs de l'image
                    xmlStreamWriter.writeAttribute("x", String.valueOf(Tv2.getX()));
                    xmlStreamWriter.writeAttribute("y", String.valueOf(Tv2.getY()));
                    xmlStreamWriter.writeAttribute("zoom", String.valueOf(Zv.GetZoom()));

                    // Fermeture de l'élément image
                    xmlStreamWriter.writeEndElement();

                    // Fermeture de l'élément racine
                    xmlStreamWriter.writeEndElement();

                    // Fin du document XML
                    xmlStreamWriter.writeEndDocument();

                    System.out.println("Les informations ont été sauvegardées dans le fichier XML avec succès.");

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        m3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent mouseEvent) {

                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("All Images", "*.*"),
                        new FileChooser.ExtensionFilter(".xml", "*.xml"));
                File selectedFile = fileChooser.showOpenDialog(primaryStage);

                try {
                    // Spécifiez le chemin du fichier XML

                    // Créez un objet File pour représenter le fichier XML
                    File xmlFile = new File(selectedFile.getAbsolutePath());

                    // Créez un constructeur de documentss
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = factory.newDocumentBuilder();

                    // Chargez le document XML à partir du fichier
                    Document document = builder.parse(xmlFile);

                    // Obtenez la racine du document
                    Element root = document.getDocumentElement();

                    String path = root.getAttribute("Path");

                    // Obtenez tous les éléments "image" sous la balise "fenetres"
                    NodeList imageList = root.getElementsByTagName("image");

                    // Parcourez la liste des éléments "image"
                    for (int i = 0; i < imageList.getLength(); i++) {
                        Element imageElement = (Element) imageList.item(i);

                        // Obtenez les attributs x, y, length et width de l'élément image
                        double x = Double.parseDouble(imageElement.getAttribute("x"));
                        double y = Double.parseDouble(imageElement.getAttribute("y"));
                        double zoom = Double.parseDouble(imageElement.getAttribute("zoom"));

                        Tv.update(path);
                        Zv.update(path);
                        Zv.update(x, y, zoom);
                        Tv2.update(path);
                        Tv2.update(x, y, zoom);
                    }

                } catch (Exception ez) {
                    ez.printStackTrace();
                }

            }
        });

        m4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent mouseEvent) {
                Platform.exit();
            }
        });
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