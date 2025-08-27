package project.smts_app.db.obj;

public class MjestoLansiranja {
    private int mjestoId;
    private String naziv;

    public MjestoLansiranja(int mjestoId, String naziv) {
        this.mjestoId = mjestoId;
        this.naziv = naziv;
    }

    public int getMjestoId() {
        return mjestoId;
    }

    public String getNaziv() {
        return naziv;
    }

    @Override
    public String toString() {
        return naziv;
    }
}