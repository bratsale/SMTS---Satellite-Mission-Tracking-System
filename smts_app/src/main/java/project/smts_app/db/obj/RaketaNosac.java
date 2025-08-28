package project.smts_app.db.obj;

public class RaketaNosac {
    private int raketaId;
    private String naziv;

    public RaketaNosac(int raketaId, String naziv) {
        this.raketaId = raketaId;
        this.naziv = naziv;
    }

    public int getRaketaId() {
        return raketaId;
    }

    public String getNaziv() {
        return naziv;
    }

    @Override
    public String toString() {
        return naziv;
    }
}