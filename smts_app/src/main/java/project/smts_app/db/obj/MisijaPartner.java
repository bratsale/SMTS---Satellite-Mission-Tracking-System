// project.smts_app.db.obj/MisijaPartner.java
package project.smts_app.db.obj;

public class MisijaPartner {
    private String nazivMisije;
    private String statusMisije;
    private String zemlja;
    private String operater;
    private String ulogaOperatera;

    public MisijaPartner(String nazivMisije, String statusMisije, String zemlja, String operater, String ulogaOperatera) {
        this.nazivMisije = nazivMisije;
        this.statusMisije = statusMisije;
        this.zemlja = zemlja;
        this.operater = operater;
        this.ulogaOperatera = ulogaOperatera;
    }

    // Dodaj gettere za sve atribute
    public String getNazivMisije() { return nazivMisije; }
    public String getStatusMisije() { return statusMisije; }
    public String getZemlja() { return zemlja; }
    public String getOperater() { return operater; }
    public String getUlogaOperatera() { return ulogaOperatera; }
}