package com.neg;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.nio.file.Path;
import java.nio.file.Paths;

public class NegApp extends Application {
    Engine engine = new Engine();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Path pathFisier = Paths.get("C:\\Users\\" + System.getenv("username") + "\\AppData\\Roaming\\Adobe\\Lightroom\\Preferences\\Lightroom Classic CC 7 Preferences.agprefs");

        try {
            Group root = new Group();
            Scene scene = new Scene(root, 300, 150, Color.DARKOLIVEGREEN);
            stage.setTitle("NegRST");
            Image logo = new Image("logo.png");
            stage.getIcons().add(logo);
            stage.setResizable(false);

            Rectangle incadrare = new Rectangle();
            incadrare.setX(5);
            incadrare.setY(5);
            incadrare.setWidth(290);
            incadrare.setHeight(140);
            incadrare.setFill(Color.LIGHTSLATEGRAY);
            incadrare.setStrokeWidth(1);
            incadrare.setStroke(Color.BLACK);
            root.getChildren().add(incadrare);

            Image backgroundElem1 = new Image("symbol1.png");
            ImageView imgViewbackgroundElem1 = new ImageView(backgroundElem1);
            imgViewbackgroundElem1.setX(26);
            imgViewbackgroundElem1.setY(25);
            root.getChildren().add(imgViewbackgroundElem1);

            Image backgroundElem2 = new Image("symbol2.png");
            ImageView imgViewbackgroundElem2 = new ImageView(backgroundElem2);
            imgViewbackgroundElem2.setX(226);
            imgViewbackgroundElem2.setY(22);
            root.getChildren().add(imgViewbackgroundElem2);

            Image logoInsideBox = new Image("logo.png");
            ImageView imgView = new ImageView(logoInsideBox);
            imgView.setX(90);
            imgView.setY(13);
            root.getChildren().add(imgView);

            Text titluApp = new Text();
            Font fontTitluApp = Font.font("Verdana", FontWeight.BOLD, 12);
            titluApp.setText("negative\n    RST");
            titluApp.setFont(fontTitluApp);
            titluApp.setFill(Color.LIGHTGRAY);
            titluApp.setX(126);
            titluApp.setY(75);
            root.getChildren().add(titluApp);

            Text disclamer = new Text();
            Font disclamerFont = Font.font("Verdana", FontWeight.BOLD, 8);
            disclamer.setText("for educational purpose");
            disclamer.setFont(disclamerFont);
            disclamer.setFill(Color.BLACK);
            disclamer.setX(183);
            disclamer.setY(133);
            root.getChildren().add(disclamer);

            Rectangle rectStatus = new Rectangle();
            rectStatus.setY(135);
            rectStatus.setX(10);
            rectStatus.setFill(Color.BLACK);
            rectStatus.setStrokeWidth(1);
            rectStatus.setStroke(Color.BLACK);
            rectStatus.setWidth(280);
            rectStatus.setHeight(5);
            root.getChildren().add(rectStatus);

            Rectangle rectStatus2 = new Rectangle();
            rectStatus2.setY(10);
            rectStatus2.setX(10);
            rectStatus2.setFill(Color.BLACK);
            rectStatus2.setStrokeWidth(1);
            rectStatus2.setStroke(Color.BLACK);
            rectStatus2.setWidth(280);
            rectStatus2.setHeight(5);
            root.getChildren().add(rectStatus2);

            Rectangle rectangleLabelContor = new Rectangle();
            rectangleLabelContor.setY(55);
            rectangleLabelContor.setX(15);
            rectangleLabelContor.setFill(Color.WHITE);
            rectangleLabelContor.setStrokeWidth(1);
            rectangleLabelContor.setStroke(Color.BLACK);
            rectangleLabelContor.setWidth(70);
            rectangleLabelContor.setHeight(20);
            root.getChildren().add(rectangleLabelContor);

            Rectangle rectangleContor = new Rectangle();
            rectangleContor.setY(75);
            rectangleContor.setX(15);
            rectangleContor.setFill(Color.BLACK);
            rectangleContor.setStrokeWidth(1);
            rectangleContor.setStroke(Color.BLACK);
            rectangleContor.setWidth(70);
            rectangleContor.setHeight(30);
            root.getChildren().add(rectangleContor);

            Text labelContor = new Text();
            labelContor.setText("Negatives:");
            labelContor.setX(23);
            labelContor.setY(69);
            root.getChildren().add(labelContor);

            Text contor = new Text();
            contor.setText(engine.counterFoto(pathFisier));
            contor.setFill(Color.WHITE);
            contor.setX(43);
            contor.setY(93);
            root.getChildren().add(contor);

            Button reset = new Button();
            reset.setText("Reset");
            reset.setLayoutX(230);
            reset.setLayoutY(50);
            root.getChildren().add(reset);

            Button ext = new Button();
            ext.setText("  Exit ");
            ext.setLayoutX(230);
            ext.setLayoutY(80);
            root.getChildren().add(ext);

            ext.setOnAction(arg0 -> engine.exitApp());
            reset.setOnAction(arg0 -> {
                engine.resetCounter(pathFisier, engine.getStringuriCandidate());
                double parametru = 0;
                for (int i = 0; i <= 12; i++) {
                    parametru += 20;
                    root.getChildren().add(engine.loader(parametru));
                    contor.setText(engine.counterFoto(pathFisier));
                }
            });
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
