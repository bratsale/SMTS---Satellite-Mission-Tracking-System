package project.smts_app.db.obj;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class KomunikacijaStanicaSatelit {

    private int komunikacijaId;
    private int satelitId;
    private String nazivSatelita;
    private int stanicaId;
    private String nazivStanice;
    private String lokacijaStanice;
    private LocalDate datumKomunikacije;
    private String tipKomunikacije;
    private String sadrzajPoruke;

    public KomunikacijaStanicaSatelit(int komunikacijaId, int satelitId, String nazivSatelita, int stanicaId, String nazivStanice, String lokacijaStanice, LocalDate datumKomunikacije, String tipKomunikacije, String sadrzajPoruke) {
        this.komunikacijaId = komunikacijaId;
        this.satelitId = satelitId;
        this.nazivSatelita = nazivSatelita;
        this.stanicaId = stanicaId;
        this.nazivStanice = nazivStanice;
        this.lokacijaStanice = lokacijaStanice;
        this.datumKomunikacije = datumKomunikacije;
        this.tipKomunikacije = tipKomunikacije;
        this.sadrzajPoruke = sadrzajPoruke;
    }

    // Novi konstruktor za podatke iz Komunikacija_Stanica_Satelit pogleda (VIEW)
    public KomunikacijaStanicaSatelit(String nazivStanice, String lokacijaStanice, String nazivSatelita, LocalDate datumKomunikacije, String tipKomunikacije, String sadrzajPoruke) {
        this.nazivStanice = nazivStanice;
        this.lokacijaStanice = lokacijaStanice;
        this.nazivSatelita = nazivSatelita;
        this.datumKomunikacije = datumKomunikacije;
        this.tipKomunikacije = tipKomunikacije;
        this.sadrzajPoruke = sadrzajPoruke;
    }

    // Getteri
    public int getKomunikacijaId() { return komunikacijaId; }
    public int getSatelitId() { return satelitId; }
    public String getNazivSatelita() { return nazivSatelita; }
    public int getStanicaId() { return stanicaId; }
    public String getNazivStanice() { return nazivStanice; }
    public String getLokacijaStanice() { return lokacijaStanice; }
    public LocalDate getDatumKomunikacije() { return datumKomunikacije; }
    public String getTipKomunikacije() { return tipKomunikacije; }
    public String getSadrzajPoruke() { return sadrzajPoruke; }
    public String getFormatiranDatum() { return datumKomunikacije.format(DateTimeFormatter.ofPattern("dd.MM.yyyy.")); }
}