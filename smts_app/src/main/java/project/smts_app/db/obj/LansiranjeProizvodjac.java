// project.smts_app.db.obj/LansiranjeProizvodjac.java
package project.smts_app.db.obj;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class LansiranjeProizvodjac {

    private final SimpleStringProperty proizvodjac;
    private final SimpleStringProperty raketaNosac;
    private final SimpleIntegerProperty brojLansiranja;

    public LansiranjeProizvodjac(String proizvodjac, String raketaNosac, int brojLansiranja) {
        this.proizvodjac = new SimpleStringProperty(proizvodjac);
        this.raketaNosac = new SimpleStringProperty(raketaNosac);
        this.brojLansiranja = new SimpleIntegerProperty(brojLansiranja);
    }

    // Getteri za property-je
    public String getProizvodjac() { return proizvodjac.get(); }
    public SimpleStringProperty proizvodjacProperty() { return proizvodjac; }

    public String getRaketaNosac() { return raketaNosac.get(); }
    public SimpleStringProperty raketaNosacProperty() { return raketaNosac; }

    public int getBrojLansiranja() { return brojLansiranja.get(); }
    public SimpleIntegerProperty brojLansiranjaProperty() { return brojLansiranja; }
}