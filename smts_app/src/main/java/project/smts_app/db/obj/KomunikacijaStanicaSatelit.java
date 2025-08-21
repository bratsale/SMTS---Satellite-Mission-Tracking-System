// project.smts_app.db.obj/KomunikacijaStanicaSatelit.java
package project.smts_app.db.obj;

import java.time.LocalDateTime;

public class KomunikacijaStanicaSatelit {

    private String nazivStanice;
    private String lokacijaStanice;
    private String nazivSatelita;
    private LocalDateTime datumKomunikacije;
    private String tipKomunikacije;
    private String sadrzajPoruke;

    public KomunikacijaStanicaSatelit(String nazivStanice, String lokacijaStanice, String nazivSatelita, LocalDateTime datumKomunikacije, String tipKomunikacije, String sadrzajPoruke) {
        this.nazivStanice = nazivStanice;
        this.lokacijaStanice = lokacijaStanice;
        this.nazivSatelita = nazivSatelita;
        this.datumKomunikacije = datumKomunikacije;
        this.tipKomunikacije = tipKomunikacije;
        this.sadrzajPoruke = sadrzajPoruke;
    }

    // Dodaj gettere za sve atribute (neophodno za PropertyValueFactory)
    public String getNazivStanice() { return nazivStanice; }
    public String getLokacijaStanice() { return lokacijaStanice; }
    public String getNazivSatelita() { return nazivSatelita; }
    public LocalDateTime getDatumKomunikacije() { return datumKomunikacije; }
    public String getTipKomunikacije() { return tipKomunikacije; }
    public String getSadrzajPoruke() { return sadrzajPoruke; }
}