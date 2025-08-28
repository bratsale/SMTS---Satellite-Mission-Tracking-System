package project.smts_app.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import project.smts_app.db.obj.*;

public class DetaljiMisijeController {
    @FXML private Label idLabel;
    @FXML private Label nazivLabel;
    @FXML private Label datumPocetkaLabel;
    @FXML private Label datumKrajaLabel;
    @FXML private Label ciljMisijeLabel;
    @FXML private Label statusLabel;

    public void setDetaljiMisije(MisijaDetalji detalji) {
        idLabel.setText(String.valueOf(detalji.getMisijaId()));
        nazivLabel.setText(detalji.getNaziv());
        datumPocetkaLabel.setText(detalji.getDatumPocetka() != null ? detalji.getDatumPocetka().toString() : "N/A");
        datumKrajaLabel.setText(detalji.getDatumKraja() != null ? detalji.getDatumKraja().toString() : "N/A");
        ciljMisijeLabel.setText(detalji.getCiljMisije());
        statusLabel.setText(detalji.getStatus());
    }

    @FXML
    private void zatvori() {
        Stage stage = (Stage) idLabel.getScene().getWindow();
        stage.close();
    }
}