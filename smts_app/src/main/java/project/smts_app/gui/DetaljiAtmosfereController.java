package project.smts_app.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class DetaljiAtmosfereController implements Initializable {
    @FXML
    private ImageView atmosferaImageView;
    @FXML
    private Label detaljiLabel;
    @FXML
    private Button zatvoriButton;

    private static final double[] GRANICE_SLOJEVA_KM = {0, 20, 50, 85, 700};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try (InputStream is = getClass().getResourceAsStream("/images/atmosphere_layers.png")) {
            if (is != null) {
                Image image = new Image(is);
                atmosferaImageView.setImage(image);
            } else {
                System.err.println("Slika nije pronađena: /images/atmosphere_layers.png");
            }
        } catch (Exception e) {
            System.err.println("Greška prilikom učitavanja slike: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void setDetaljiSatelita(String nazivSatelita, double visinaKm) {
        String sloj = odrediSloj(visinaKm);
        detaljiLabel.setText("Satelit " + nazivSatelita + " se nalazi u sloju: " + sloj + ".");
    }

    private String odrediSloj(double visinaKm) {
        if (visinaKm <= GRANICE_SLOJEVA_KM[1]) {
            return "Troposfera";
        } else if (visinaKm <= GRANICE_SLOJEVA_KM[2]) {
            return "Stratosfera";
        } else if (visinaKm <= GRANICE_SLOJEVA_KM[3]) {
            return "Mezosfera";
        } else if (visinaKm <= GRANICE_SLOJEVA_KM[4]) {
            return "Termosfera";
        } else {
            return "Egzosfera";
        }
    }

    @FXML
    private void zatvoriProzor() {
        Stage stage = (Stage) zatvoriButton.getScene().getWindow();
        stage.close();
    }
}
