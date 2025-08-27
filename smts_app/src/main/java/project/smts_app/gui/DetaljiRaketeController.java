package project.smts_app.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import project.smts_app.db.obj.RaketaDetalji;

public class DetaljiRaketeController {
    @FXML private Label idLabel;
    @FXML private Label nazivLabel;
    @FXML private Label proizvodjacLabel;
    @FXML private Label kapacitetLabel;

    public void setDetaljiRakete(RaketaDetalji detalji) {
        idLabel.setText(String.valueOf(detalji.getRaketaId()));
        nazivLabel.setText(detalji.getNaziv());
        proizvodjacLabel.setText(detalji.getProizvodjac());
        kapacitetLabel.setText(String.valueOf(detalji.getKapacitetTovarKg()));
    }

    @FXML
    private void zatvori() {
        Stage stage = (Stage) idLabel.getScene().getWindow();
        stage.close();
    }
}