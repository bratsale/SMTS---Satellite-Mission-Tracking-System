package project.smts_app.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import project.smts_app.db.dao.LansiranjeDAO;
import java.time.LocalDateTime;

public class SmtsController {

    // GUI elementi iz smts-view.fxml
    @FXML
    private TextField nazivSatelitaField;
    @FXML
    private TextField zemljaProizvodnjeField;
    @FXML
    private TextField masaKgField;
    @FXML
    private TextField misijaIdField;
    @FXML
    private TextField tipIdField;
    @FXML
    private TextField vrijemeLansiranjaField; // Za sada, String. Kasnije ga parsiramo.
    @FXML
    private TextField raketaIdField;
    @FXML
    private TextField mjestoIdField;
    @FXML
    private Label porukaLabel;

    // Instanca DAO klase za pozivanje logike
    private LansiranjeDAO lansiranjeDAO = new LansiranjeDAO();

    /**
     * Metoda koja se poziva klikom na dugme "Kreiraj lansiranje".
     */
    @FXML
    protected void onKreirajLansiranjeClick() {
        try {
            // Dohvatanje podataka iz GUI elemenata
            String nazivSatelita = nazivSatelitaField.getText();
            String zemljaProizvodnje = zemljaProizvodnjeField.getText();
            double masaKg = Double.parseDouble(masaKgField.getText());
            int misijaId = Integer.parseInt(misijaIdField.getText());
            int tipId = Integer.parseInt(tipIdField.getText());
            LocalDateTime vrijemeLansiranja = LocalDateTime.parse(vrijemeLansiranjaField.getText());
            int raketaId = Integer.parseInt(raketaIdField.getText());
            int mjestoId = Integer.parseInt(mjestoIdField.getText());

            // Pozivanje DAO metode s unesenim podacima
            lansiranjeDAO.kreirajNovoLansiranje(
                    nazivSatelita, zemljaProizvodnje, masaKg, misijaId, tipId,
                    vrijemeLansiranja, raketaId, mjestoId
            );

            // Ažuriranje poruke za korisnika
            porukaLabel.setText("Uspješno kreirano novo lansiranje!");
            porukaLabel.setStyle("-fx-text-fill: green;");

        } catch (Exception e) {
            porukaLabel.setText("Greška prilikom unosa: " + e.getMessage());
            porukaLabel.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        }
    }
}