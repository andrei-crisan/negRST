package com.neg;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Engine {
    private Map<String, String> stringuriCandidate = new HashMap<>();

    public Engine() {
        putInMap();
    }

    private void putInMap() {
        stringuriCandidate.put("	photosAppliedV4 = 12,\\", "	photosAppliedV4 = 0,\\");
        stringuriCandidate.put("	photosAppliedV4 = 11,\\", " photosAppliedV4 = 0,\\");
        stringuriCandidate.put("	photosAppliedV4 = 10,\\", " photosAppliedV4 = 0,\\");
        stringuriCandidate.put("	photosAppliedV4 = 9,\\", " 	photosAppliedV4 = 0,\\");
        stringuriCandidate.put("	photosAppliedV4 = 8,\\", " 	photosAppliedV4 = 0,\\");
        stringuriCandidate.put("	photosAppliedV4 = 7,\\", " 	photosAppliedV4 = 0,\\");
        stringuriCandidate.put("	photosAppliedV4 = 6,\\", " 	photosAppliedV4 = 0,\\");
        stringuriCandidate.put("	photosAppliedV4 = 5,\\", " 	photosAppliedV4 = 0,\\");
        stringuriCandidate.put("	photosAppliedV4 = 4,\\", " 	photosAppliedV4 = 0,\\");
        stringuriCandidate.put("	photosAppliedV4 = 3,\\", " 	photosAppliedV4 = 0,\\");
        stringuriCandidate.put("	photosAppliedV4 = 2,\\", " 	photosAppliedV4 = 0,\\");
        stringuriCandidate.put("	photosAppliedV4 = 1,\\", " 	photosAppliedV4 = 0,\\");
    }

    public String inlocuire(String linieX, Map<String, String> stringuriCandidate) {
        for (Map.Entry<String, String> entry : stringuriCandidate.entrySet()) {
            if (linieX.contains(entry.getKey())) {
                linieX = linieX.replace(entry.getKey(), entry.getValue());
            }
        }
        return linieX;
    }

    public String counterFoto(Path pathFisier) {
        int contor = 0;
        String rezultat = "";
        try {
            Stream<String> indexLinie = Files.lines(pathFisier);
            List<String> nrFotografii = indexLinie
                    .filter(x -> x.contains("	photosAppliedV4 ="))
                    .collect(Collectors.toList());
            String counterFirstReplace = nrFotografii.get(0).replaceAll("\\s+[a-zA-Z]+\\d\\s[=]", "");
            String counterSecondReplace = counterFirstReplace.replaceAll("[\\s,\\\\]", "");
            contor = 12 - Integer.parseInt(counterSecondReplace);
            rezultat = String.valueOf(contor);
            indexLinie.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rezultat;
    }

    public void resetCounter(Path pathFisier, Map<String, String> stringuriCandidate) {
        try {
            Stream<String> indexLinie = Files.lines(pathFisier);
            List<String> textParsat = indexLinie
                    .map(x -> inlocuire(x, stringuriCandidate))
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

    public Map<String, String> getStringuriCandidate() {
        return stringuriCandidate;
    }
}