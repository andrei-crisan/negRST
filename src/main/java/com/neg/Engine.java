package com.neg;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Engine {

    public String counterAvailablePhotos(Path pathFisier) {
        int contor = 0;
        String availablePhotos = "";
        try {
            Stream<String> indexLinie = Files.lines(pathFisier);
            List<String> nrFotografii = indexLinie
                    .filter(x -> x.matches("\\s+[a-zA-Z]+\\d{1}\\s=\\s\\d+,\\\\"))
                    .collect(Collectors.toList());
            String counterFirstReplace = nrFotografii.get(0).replaceAll("\\s+[a-zA-Z]+\\d\\s[=]", "");
            String counterSecondReplace = counterFirstReplace.replaceAll("[\\s,\\\\]", "");
            contor = 12 - Integer.parseInt(counterSecondReplace);
            availablePhotos = String.valueOf(contor);
            indexLinie.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return availablePhotos;
    }

    public void resetCounter(Path pathFisier) {
        try {
            Stream<String> indexLinie = Files.lines(pathFisier);
            List<String> textParsat = indexLinie
                    .map(x -> x.replaceAll("[a-zA-Z]+\\d{1}\\s=\\s[0-9]+", "photosAppliedV4 = 0"))
                    .collect(Collectors.toList());
            Files.write(pathFisier, textParsat, Charset.forName("UTF-8"));
            indexLinie.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exitApp() {
        Platform.exit();
    }

    public Rectangle loader(double pozitie) {
        Rectangle rectStatus3 = new Rectangle();
        rectStatus3.setY(10);
        rectStatus3.setX(pozitie);
        rectStatus3.setFill(Color.DARKRED);
        rectStatus3.setStrokeWidth(1);
        rectStatus3.setStroke(Color.BLACK);
        rectStatus3.setWidth(20);
        rectStatus3.setHeight(5);
        return rectStatus3;
    }
}