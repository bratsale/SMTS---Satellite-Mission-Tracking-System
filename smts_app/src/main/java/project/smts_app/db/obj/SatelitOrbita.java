// project.smts_app.db.obj/SatelitOrbita.java
package project.smts_app.db.obj;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class SatelitOrbita {

    private final SimpleStringProperty nazivSatelita;
    private final SimpleStringProperty tipSatelita;
    private final SimpleStringProperty nazivMisije;
    private final SimpleDoubleProperty visinaOrbiteKm;
    private final SimpleDoubleProperty inklinacijaOrbite;

    public SatelitOrbita(String nazivSatelita, String tipSatelita, String nazivMisije, double visinaOrbiteKm, double inklinacijaOrbite) {
        this.nazivSatelita = new SimpleStringProperty(nazivSatelita);
        this.tipSatelita = new SimpleStringProperty(tipSatelita);
        this.nazivMisije = new SimpleStringProperty(nazivMisije);
        this.visinaOrbiteKm = new SimpleDoubleProperty(visinaOrbiteKm);
        this.inklinacijaOrbite = new SimpleDoubleProperty(inklinacijaOrbite);
    }

    // Getteri za sve property-je
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