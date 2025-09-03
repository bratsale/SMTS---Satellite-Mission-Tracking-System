package project.smts_app.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import project.smts_app.db.obj.SatelitOrbita;

public class UrediOrbituController {

    @FXML
    private Label satelitNazivLabel;
    @FXML
    private TextField novaVisinaField;
    @FXML
    private TextField novaInklinacijaField;

    private SatelitOrbita odabraniSatelit;
    private boolean azurirano = false;

    public void setSatelit(SatelitOrbita satelit) {
        this.odabraniSatelit = satelit;
        satelitNazivLabel.setText("Ažuriranje orbite za: " + satelit.getNazivSatelita());
    }

    public boolean isAzurirano() {
        return azurirano;
    }

    public double getNovaVisina() {
        return Double.parseDouble(novaVisinaField.getText());
    }

    public double getNovaInklinacija() {
        return Double.parseDouble(novaInklinacijaField.getText());
    }

    @FXML
    private void azurirajOrbitu() {
        try {
            double novaVisina = Double.parseDouble(novaVisinaField.getText());
            double novaInklinacija = Double.parseDouble(novaInklinacijaField.getText());

            if (novaVisina >= 0 && novaInklinacija >= 0 && novaInklinacija <= 180) {
                azurirano = true;
                zatvoriProzor();
            } else {
                System.out.println("Greška: Unesite ispravne vrijednosti za visinu i inklinaciju.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Greška: Visina i inklinacija moraju biti brojevi.");
        }
    }

    @FXML
    private void odustani() {
        azurirano = false;
        zatvoriProzor();
    }

    private void zatvoriProzor() {
        Stage stage = (Stage) novaVisinaField.getScene().getWindow();
        stage.close();
    }
}