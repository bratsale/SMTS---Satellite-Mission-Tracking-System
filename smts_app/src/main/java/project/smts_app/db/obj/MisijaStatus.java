package project.smts_app.db.obj;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class MisijaStatus {
    private SimpleIntegerProperty misijaId;
    private SimpleStringProperty nazivMisije;
    private SimpleStringProperty status;

    public MisijaStatus(int misijaId, String nazivMisije, String status) {
        this.misijaId = new SimpleIntegerProperty(misijaId);
        this.nazivMisije = new SimpleStringProperty(nazivMisije);
        this.status = new SimpleStringProperty(status);
    }

    public int getMisijaId() {
        return misijaId.get();
    }

    public SimpleIntegerProperty misijaIdProperty() {
        return misijaId;
    }

    public String getNazivMisije() {
        return nazivMisije.get();
    }

    public SimpleStringProperty nazivMisijeProperty() {
        return nazivMisije;
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }
}
