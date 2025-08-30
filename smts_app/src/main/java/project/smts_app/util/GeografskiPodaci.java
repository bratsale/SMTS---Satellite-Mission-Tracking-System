package project.smts_app.util;

import java.util.HashMap;
import java.util.Map;

public class GeografskiPodaci {

    /**
     * Mapa s koordinatama glavnih zemalja.
     * Koordinate su u formatu: {geografska širina, geografska dužina}.
     */
    private static final Map<String, double[]> KOORDINATE = new HashMap<>();

    static {
        // Dodajte koordinate za svaku zemlju koja se pojavljuje u vašim podacima
        KOORDINATE.put("SAD", new double[]{39.8283, -98.5795});
        KOORDINATE.put("Rusija", new double[]{61.5240, 105.3188});
        KOORDINATE.put("Evropska unija", new double[]{50.1109, 8.6821}); // Frankfurt kao centralna tačka
        KOORDINATE.put("Kina", new double[]{35.8617, 104.1954});
        KOORDINATE.put("Japan", new double[]{36.2048, 138.2529});
        KOORDINATE.put("Kanada", new double[]{56.1304, -106.3468});
    }

    public static double[] dohvatiKoordinateZaDrzavu(String nazivDrzave) {
        return KOORDINATE.getOrDefault(nazivDrzave, new double[]{0.0, 0.0}); // Vraća {0,0} ako zemlja nije pronađena
    }
}