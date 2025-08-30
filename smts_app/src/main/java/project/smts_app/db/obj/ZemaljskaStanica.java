package project.smts_app.db.obj;

public class ZemaljskaStanica {
    private int stanicaId;
    private String naziv;
    private String lokacija;
    private String zemljaStanice;

    public ZemaljskaStanica(int stanicaId, String naziv, String lokacija, String zemljaStanice) {
        this.stanicaId = stanicaId;
        this.naziv = naziv;
        this.lokacija = lokacija;
        this.zemljaStanice = zemljaStanice;
    }

    // Getteri i setteri
    public int getStanicaId() { return stanicaId; }
    public void setStanicaId(int stanicaId) { this.stanicaId = stanicaId; }
    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }
    public String getLokacija() { return lokacija; }
    public void setLokacija(String lokacija) { this.lokacija = lokacija; }
    public String getZemljaStanice() { return zemljaStanice; }
    public void setZemljaStanice(String zemljaStanice) { this.zemljaStanice = zemljaStanice; }

    @Override
    public String toString() {
        return naziv;
    }
}