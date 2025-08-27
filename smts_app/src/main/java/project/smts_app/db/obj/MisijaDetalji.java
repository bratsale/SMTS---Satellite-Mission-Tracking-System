package project.smts_app.db.obj;

import java.time.LocalDate;

public class MisijaDetalji {
    private int misijaId;
    private String naziv;
    private LocalDate datumPocetka;
    private LocalDate datumKraja;
    private String ciljMisije;
    private String status;
    private int brojSatelita; // Možda ti ovo zatreba

    // Postojeći konstruktor sa dva argumenta
    public MisijaDetalji(int misijaId, String naziv) {
        this.misijaId = misijaId;
        this.naziv = naziv;
    }

    // Novi, prošireni konstruktor sa svim detaljima
    public MisijaDetalji(int misijaId, String naziv, LocalDate datumPocetka, LocalDate datumKraja, String ciljMisije, String status) {
        this.misijaId = misijaId;
        this.naziv = naziv;
        this.datumPocetka = datumPocetka;
        this.datumKraja = datumKraja;
        this.ciljMisije = ciljMisije;
        this.status = status;
    }

    // Getteri za sva polja
    public int getMisijaId() {
        return misijaId;
    }

    public String getNaziv() {
        return naziv;
    }

    public LocalDate getDatumPocetka() {
        return datumPocetka;
    }

    public LocalDate getDatumKraja() {
        return datumKraja;
    }

    public String getCiljMisije() {
        return ciljMisije;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return naziv;
    }
}