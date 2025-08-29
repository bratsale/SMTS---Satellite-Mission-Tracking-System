package project.smts_app.db.obj;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;
import java.time.LocalDateTime;

public class SatelitDetalji {

    private SimpleIntegerProperty satelitId;
    private SimpleStringProperty nazivSatelita;
    private SimpleStringProperty zemljaProizvodnje;
    private SimpleDoubleProperty masaKg;
    private SimpleStringProperty tipSatelita;
    private SimpleStringProperty nazivMisije;
    private SimpleObjectProperty<LocalDateTime> vrijemeLansiranja;
    private SimpleStringProperty raketaNosac;
    private SimpleStringProperty mjestoLansiranja;
    private SimpleDoubleProperty visinaKm;
    private SimpleDoubleProperty inklinacijaStepeni;


    public SatelitDetalji(int satelitId, String nazivSatelita, String zemljaProizvodnje, double masaKg,
                          String tipSatelita, String nazivMisije, LocalDateTime vrijemeLansiranja,
                          String raketaNosac, String mjestoLansiranja) {
        this.satelitId = new SimpleIntegerProperty(satelitId);
        this.nazivSatelita = new SimpleStringProperty(nazivSatelita);
        this.zemljaProizvodnje = new SimpleStringProperty(zemljaProizvodnje);
        this.masaKg = new SimpleDoubleProperty(masaKg);
        this.tipSatelita = new SimpleStringProperty(tipSatelita);
        this.nazivMisije = new SimpleStringProperty(nazivMisije);
        this.vrijemeLansiranja = new SimpleObjectProperty<>(vrijemeLansiranja);
        this.raketaNosac = new SimpleStringProperty(raketaNosac);
        this.mjestoLansiranja = new SimpleStringProperty(mjestoLansiranja);
    }

    public SatelitDetalji(int satelitId, String nazivSatelita, String zemljaProizvodnje, double masaKg,
                          String tipSatelita, String nazivMisije) {
        this(satelitId, nazivSatelita, zemljaProizvodnje, masaKg, tipSatelita, nazivMisije, null, null, null);
    }

    public SatelitDetalji(int satelitId, String nazivSatelita, String zemljaProizvodnje, double masaKg,
                          String tipSatelita, String nazivMisije, double visinaKm, double inklinacijaStepeni) {
        this(satelitId, nazivSatelita, zemljaProizvodnje, masaKg, tipSatelita, nazivMisije);
        this.visinaKm = new SimpleDoubleProperty(visinaKm);
        this.inklinacijaStepeni = new SimpleDoubleProperty(inklinacijaStepeni);
    }

    public int getSatelitId() { return satelitId.get(); }
    public SimpleIntegerProperty satelitIdProperty() { return satelitId; }

    public String getNazivSatelita() { return nazivSatelita.get(); }
    public SimpleStringProperty nazivSatelitaProperty() { return nazivSatelita; }

    public String getZemljaProizvodnje() { return zemljaProizvodnje.get(); }
    public SimpleStringProperty zemljaProizvodnjeProperty() { return zemljaProizvodnje; }

    public double getMasaKg() { return masaKg.get(); }
    public SimpleDoubleProperty masaKgProperty() { return masaKg; }

    public String getTipSatelita() { return tipSatelita.get(); }
    public SimpleStringProperty tipSatelitaProperty() { return tipSatelita; }

    public String getNazivMisije() { return nazivMisije.get(); }
    public SimpleStringProperty nazivMisijeProperty() { return nazivMisije; }

    public LocalDateTime getVrijemeLansiranja() { return vrijemeLansiranja.get(); }
    public SimpleObjectProperty<LocalDateTime> vrijemeLansiranjaProperty() { return vrijemeLansiranja; }

    public String getRaketaNosac() { return raketaNosac.get(); }
    public SimpleStringProperty raketaNosacProperty() { return raketaNosac; }

    public String getMjestoLansiranja() { return mjestoLansiranja.get(); }
    public SimpleStringProperty mjestoLansiranjaProperty() { return mjestoLansiranja; }

    public double getVisinaKm() { return visinaKm.get(); }
    public SimpleDoubleProperty visinaKmProperty() { return visinaKm; }

    public double getInklinacijaStepeni() { return inklinacijaStepeni.get(); }
    public SimpleDoubleProperty inklinacijaStepeniProperty() { return inklinacijaStepeni; }
}