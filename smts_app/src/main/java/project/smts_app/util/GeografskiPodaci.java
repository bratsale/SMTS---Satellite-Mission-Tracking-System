package project.smts_app.util;

import java.util.HashMap;
import java.util.Map;

public class GeografskiPodaci {


    private static final Map<String, double[]> KOORDINATE = new HashMap<>();

    static {
        KOORDINATE.put("SAD", new double[]{39.8283, -98.5795});
        KOORDINATE.put("Rusija", new double[]{61.5240, 105.3188});
        KOORDINATE.put("Evropska unija", new double[]{50.1109, 8.6821});
        KOORDINATE.put("Kina", new double[]{35.8617, 104.1954});
        KOORDINATE.put("Japan", new double[]{36.2048, 138.2529});
        KOORDINATE.put("Kanada", new double[]{56.1304, -106.3468});
    }

    public static double[] dohvatiKoordinateZaDrzavu(String nazivDrzave) {
        return KOORDINATE.getOrDefault(nazivDrzave, new double[]{0.0, 0.0});
    }
}