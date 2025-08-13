package project.smts_app.db.obj;
import java.time.LocalDateTime;
import java.util.Date;

public class Lansiranje {

    private int lansiranjeId;
    private LocalDateTime vrijemeLansiranja;
    private int raketaId;
    private int misijaId;
    private int satelitId;
    private int mjestoId;

    public Lansiranje() {
    }

    public Lansiranje(int lansiranjeId, LocalDateTime vrijemeLansiranja, int raketaId, int misijaId, int satelitId, int mjestoId) {
        this.lansiranjeId = lansiranjeId;
        this.vrijemeLansiranja = vrijemeLansiranja;
        this.raketaId = raketaId;
        this.misijaId = misijaId;
        this.satelitId = satelitId;
        this.mjestoId = mjestoId;
    }

    public int getLansiranjeId() {
        return lansiranjeId;
    }

    public void setLansiranjeId(int lansiranjeId) {
        this.lansiranjeId = lansiranjeId;
    }

    public LocalDateTime getVrijemeLansiranja() {
        return vrijemeLansiranja;
    }

    public void setVrijemeLansiranja(LocalDateTime vrijemeLansiranja) {
        this.vrijemeLansiranja = vrijemeLansiranja;
    }

    public int getRaketaId() {
        return raketaId;
    }

    public void setRaketaId(int raketaId) {
        this.raketaId = raketaId;
    }

    public int getMisijaId() {
        return misijaId;
    }

    public void setMisijaId(int misijaId) {
        this.misijaId = misijaId;
    }

    public int getSatelitId() {
        return satelitId;
    }

    public void setSatelitId(int satelitId) {
        this.satelitId = satelitId;
    }

    public int getMjestoId() {
        return mjestoId;
    }

    public void setMjestoId(int mjestoId) {
        this.mjestoId = mjestoId;
    }
}