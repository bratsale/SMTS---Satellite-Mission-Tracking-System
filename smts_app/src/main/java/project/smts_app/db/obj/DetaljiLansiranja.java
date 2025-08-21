package project.smts_app.db.obj;

import java.time.LocalDateTime;

public class DetaljiLansiranja {

    private String satelit;
    private LocalDateTime vrijemeLansiranja;
    private String raketaNosac;
    private String misija;
    private String mjestoLansiranja;
    private int lansiranjeId;

    public DetaljiLansiranja(String satelit, LocalDateTime vrijemeLansiranja, String raketaNosac, String misija, String mjestoLansiranja, int lansiranjeId) {
        this.satelit = satelit;
        this.vrijemeLansiranja = vrijemeLansiranja;
        this.raketaNosac = raketaNosac;
        this.misija = misija;
        this.mjestoLansiranja = mjestoLansiranja;
        this.lansiranjeId = lansiranjeId;
    }

    // Getteri za sve atribute

    public String getSatelit() {
        return satelit;
    }

    public LocalDateTime getVrijemeLansiranja() {
        return vrijemeLansiranja;
    }

    public String getRaketaNosac() {
        return raketaNosac;
    }

    public String getMisija() {
        return misija;
    }

    public String getMjestoLansiranja() {
        return mjestoLansiranja;
    }

    public int getLansiranjeId() {
        return lansiranjeId;
    }
}