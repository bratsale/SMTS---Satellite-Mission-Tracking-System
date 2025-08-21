// project.smts_app.db.obj/SatelitDetalji.java
package project.smts_app.db.obj;

import java.time.LocalDateTime;

public class SatelitDetalji {

    private int satelitId;
    private String nazivSatelita;
    private String zemljaProizvodnje;
    private double masaKg;
    private String tipSatelita;
    private String nazivMisije;
    private LocalDateTime vrijemeLansiranja;
    private String raketaNosac;
    private String mjestoLansiranja;

    public SatelitDetalji(int satelitId, String nazivSatelita, String zemljaProizvodnje, double masaKg, String tipSatelita, String nazivMisije, LocalDateTime vrijemeLansiranja, String raketaNosac, String mjestoLansiranja) {
        this.satelitId = satelitId;
        this.nazivSatelita = nazivSatelita;
        this.zemljaProizvodnje = zemljaProizvodnje;
        this.masaKg = masaKg;
        this.tipSatelita = tipSatelita;
        this.nazivMisije = nazivMisije;
        this.vrijemeLansiranja = vrijemeLansiranja;
        this.raketaNosac = raketaNosac;
        this.mjestoLansiranja = mjestoLansiranja;
    }

    // Dodaj gettere za sve atribute (važno za PropertyValueFactory)
    public int getSatelitId() { return satelitId; }
    public String getNazivSatelita() { return nazivSatelita; }
    public String getZemljaProizvodnje() { return zemljaProizvodnje; }
    public double getMasaKg() { return masaKg; }
    public String getTipSatelita() { return tipSatelita; }
    public String getNazivMisije() { return nazivMisije; }
    public LocalDateTime getVrijemeLansiranja() { return vrijemeLansiranja; }
    public String getRaketaNosac() { return raketaNosac; }
    public String getMjestoLansiranja() { return mjestoLansiranja; }
}