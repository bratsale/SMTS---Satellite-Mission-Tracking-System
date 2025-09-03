package project.smts_app.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import project.smts_app.db.obj.KomunikacijaStanicaSatelit;

public class DetaljiKomunikacijeController {

    @FXML
    private Label posiljalacLabel;
    @FXML
    private Label primalacLabel;
    @FXML
    private Label datumTipLabel;
    @FXML
    private TextArea porukaTextArea;

    public void setDetaljiKomunikacije(KomunikacijaStanicaSatelit komunikacija) {
        String tipKomunikacije = komunikacija.getTipKomunikacije().toLowerCase();

        if (tipKomunikacije.contains("command") || tipKomunikacije.contains("update")) {
            posiljalacLabel.setText("Od: " + komunikacija.getNazivStanice());
            primalacLabel.setText("Prema: " + komunikacija.getNazivSatelita());
        } else {
            posiljalacLabel.setText("Od: " + komunikacija.getNazivSatelita());
            primalacLabel.setText("Prema: " + komunikacija.getNazivStanice());
        }

        datumTipLabel.setText("Datum: " + komunikacija.getDatumKomunikacije() + " | Tip: " + komunikacija.getTipKomunikacije());
        porukaTextArea.setText(komunikacija.getSadrzajPoruke());
    }

    @FXML
    protected void zatvoriProzor() {
        Stage stage = (Stage) porukaTextArea.getScene().getWindow();
        stage.close();
    }
}