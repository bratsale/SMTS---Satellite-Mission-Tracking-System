// project.smts_app.db.obj/MisijaStatus.java
package project.smts_app.db.obj;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class MisijaStatus {
    private SimpleStringProperty nazivMisije;
    private SimpleStringProperty status;

    public MisijaStatus(String nazivMisije, String status) {
        this.nazivMisije = new SimpleStringProperty(nazivMisije);
        this.status = new SimpleStringProperty(status);
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