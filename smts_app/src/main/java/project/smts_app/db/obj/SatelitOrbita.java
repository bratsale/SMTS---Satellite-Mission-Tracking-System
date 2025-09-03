package project.smts_app.db.obj;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class SatelitOrbita {

    private final SimpleIntegerProperty satelitId;
    private final SimpleStringProperty nazivSatelita;
    private final SimpleStringProperty tipSatelita;
    private final SimpleStringProperty nazivMisije;
    private final SimpleDoubleProperty visinaOrbiteKm;
    private final SimpleDoubleProperty inklinacijaOrbite;

    public SatelitOrbita(int satelitId, String nazivSatelita, String tipSatelita, String nazivMisije, double visinaOrbiteKm, double inklinacijaOrbite) {
        this.satelitId = new SimpleIntegerProperty(satelitId);
        this.nazivSatelita = new SimpleStringProperty(nazivSatelita);
        this.tipSatelita = new SimpleStringProperty(tipSatelita);
        this.nazivMisije = new SimpleStringProperty(nazivMisije);
        this.visinaOrbiteKm = new SimpleDoubleProperty(visinaOrbiteKm);
        this.inklinacijaOrbite = new SimpleDoubleProperty(inklinacijaOrbite);
    }

    public int getSatelitId() { return satelitId.get(); }
    public SimpleIntegerProperty satelitIdProperty() { return satelitId; }

    public String getNazivSatelita() { return nazivSatelita.get(); }
    public SimpleStringProperty nazivSatelitaProperty() { return nazivSatelita; }

    public String getTipSatelita() { return tipSatelita.get(); }
    public SimpleStringProperty tipSatelitaProperty() { return tipSatelita; }

    public String getNazivMisije() { return nazivMisije.get(); }
    public SimpleStringProperty nazivMisijeProperty() { return nazivMisije; }

    public double getVisinaOrbiteKm() { return visinaOrbiteKm.get(); }
    public SimpleDoubleProperty visinaOrbiteKmProperty() { return visinaOrbiteKm; }

    public double getInklinacijaOrbite() { return inklinacijaOrbite.get(); }
    public SimpleDoubleProperty inklinacijaOrbiteProperty() { return inklinacijaOrbite; }
}