package project.smts_app.db.obj;

import java.time.LocalDateTime;

public class SatelitDetalji {
    private int satelitId;
    private String nazivSatelita;
    private String zemljaProizvodnje;
    private double masaKg;
    private String nazivMisije;
    private String tipSatelita; // Preimenovano u tipSatelita
    private LocalDateTime vrijemeLansiranja;
    private String raketaNosac;
    private String mjestoLansiranja;

    // Konstruktor s 9 argumenata za dohvaćanje svih detalja
    public SatelitDetalji(int satelitId, String nazivSatelita, String zemljaProizvodnje, double masaKg,
                          String tipSatelita, String nazivMisije, LocalDateTime vrijemeLansiranja,
                          String raketaNosac, String mjestoLansiranja) {
        this.satelitId = satelitId;
        this.nazivSatelita = nazivSatelita;
        this.zemljaProizvodnje = zemljaProizvodnje;
        this.masaKg = masaKg;
        this.tipSatelita = tipSatelita; // Usklađeno s imenom polja
        this.nazivMisije = nazivMisije;
        this.vrijemeLansiranja = vrijemeLansiranja;
        this.raketaNosac = raketaNosac;
        this.mjestoLansiranja = mjestoLansiranja;
    }

    // Konstruktor sa 6 argumenata za prikaz detalja iz Lansiranje tabele
    public SatelitDetalji(int satelitId, String nazivSatelita, String zemljaProizvodnje, double masaKg,
                          String nazivMisije, String tipSatelita) { // Usklađeno s imenom polja
        this.satelitId = satelitId;
        this.nazivSatelita = nazivSatelita;
        this.zemljaProizvodnje = zemljaProizvodnje;
        this.masaKg = masaKg;
        this.nazivMisije = nazivMisije;
        this.tipSatelita = tipSatelita; // Usklađeno s imenom polja
    }

    // Getteri za sva polja
    public int getSatelitId() { return satelitId; }
    public String getNazivSatelita() { return nazivSatelita; }
    public String getZemljaProizvodnje() { return zemljaProizvodnje; }
    public double getMasaKg() { return masaKg; }
    public String getNazivMisije() { return nazivMisije; }
    public String getTipSatelita() { return tipSatelita; } // Preimenovan getter
    public LocalDateTime getVrijemeLansiranja() { return vrijemeLansiranja; }
    public String getRaketaNosac() { return raketaNosac; }
    public String getMjestoLansiranja() { return mjestoLansiranja; }
}