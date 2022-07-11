package application;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Engine {
	public static String inlocuire(String linieX, Map<String, String> stringuriCandidate) {
		for(Map.Entry<String, String> entry : stringuriCandidate.entrySet()) {
			if(linieX.contains(entry.getKey())) {
				linieX = linieX.replace(entry.getKey(), entry.getValue());
			}
		}
		return linieX;
	}
	public static String counterFoto(Path pathFisier) {
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
		} catch(IOException e) {
			e.printStackTrace();
		}
		return rezultat;
	}
	public static void resetCounter(Path pathFisier, Map<String, String> stringuriCandidate) {
		try {
			Stream<String> indexLinie = Files.lines(pathFisier);
			List<String> textParsat = indexLinie
					.map(x -> Engine.inlocuire(x, stringuriCandidate))
					.collect(Collectors.toList());
			Files.write(pathFisier, textParsat, Charset.forName("UTF-8"));
			indexLinie.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static void exitApp() {
		Platform.exit();
	}
	public static Rectangle loader(double pozitie) {
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