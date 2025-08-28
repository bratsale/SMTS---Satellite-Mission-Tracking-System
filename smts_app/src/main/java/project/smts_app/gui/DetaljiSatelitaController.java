package project.smts_app.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import project.smts_app.db.obj.SatelitDetalji;

public class DetaljiSatelitaController {
    @FXML private Label idLabel;
    @FXML private Label nazivLabel;
    @FXML private Label zemljaLabel;
    @FXML private Label masaLabel;
    @FXML private Label misijaLabel;
    @FXML private Label tipLabel;

    public void setDetaljiSatelita(SatelitDetalji detalji) {
        idLabel.setText(String.valueOf(detalji.getSatelitId()));
        nazivLabel.setText(detalji.getNazivSatelita());
        zemljaLabel.setText(detalji.getZemljaProizvodnje());
        masaLabel.setText(String.valueOf(detalji.getMasaKg()));
        misijaLabel.setText(detalji.getNazivMisije());
        tipLabel.setText(detalji.getTipSatelita());
    }

    @FXML
    private void zatvori() {
        Stage stage = (Stage) idLabel.getScene().getWindow();
        stage.close();
    }
}