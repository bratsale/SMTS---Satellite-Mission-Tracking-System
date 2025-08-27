package project.smts_app.db.obj;

public class RaketaDetalji {
    private int raketaId;
    private String naziv;
    private String proizvodjac;
    private int kapacitetTovarKg;

    public RaketaDetalji(int raketaId, String naziv, String proizvodjac, int kapacitetTovarKg) {
        this.raketaId = raketaId;
        this.naziv = naziv;
        this.proizvodjac = proizvodjac;
        this.kapacitetTovarKg = kapacitetTovarKg;
    }

    public int getRaketaId() { return raketaId; }
    public String getNaziv() { return naziv; }
    public String getProizvodjac() { return proizvodjac; }
    public int getKapacitetTovarKg() { return kapacitetTovarKg; }
}