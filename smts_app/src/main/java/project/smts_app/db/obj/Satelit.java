package project.smts_app.db.obj;

public class Satelit {

    private int satelitId;
    private String naziv;
    private String zemljaProizvodnje;
    private double masaKg;
    private int misijaId;
    private int tipId;

    public Satelit() {
    }

    public Satelit(int satelitId, String naziv, String zemljaProizvodnje, double masaKg, int misijaId, int tipId) {
        this.satelitId = satelitId;
        this.naziv = naziv;
        this.zemljaProizvodnje = zemljaProizvodnje;
        this.masaKg = masaKg;
        this.misijaId = misijaId;
        this.tipId = tipId;
    }

    public int getSatelitId() {
        return satelitId;
    }

    public void setSatelitId(int satelitId) {
        this.satelitId = satelitId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getZemljaProizvodnje() {
        return zemljaProizvodnje;
    }

    public void setZemljaProizvodnje(String zemljaProizvodnje) {
        this.zemljaProizvodnje = zemljaProizvodnje;
    }

    public double getMasaKg() {
        return masaKg;
    }

    public void setMasaKg(double masaKg) {
        this.masaKg = masaKg;
    }

    public int getMisijaId() {
        return misijaId;
    }

    public void setMisijaId(int misijaId) {
        this.misijaId = misijaId;
    }

    public int getTipId() {
        return tipId;
    }

    public void setTipId(int tipId) {
        this.tipId = tipId;
    }

    @Override
    public String toString() {
        return naziv;
    }

    public static class Tip {
        private int tipId;
        private String naziv;

        public Tip(int tipId, String naziv) {
            this.tipId = tipId;
            this.naziv = naziv;
        }

        public int getTipId() {
            return tipId;
        }

        public String getNaziv() {
            return naziv;
        }

        @Override
        public String toString() {
            return naziv;
        }
    }
}