package project.smts_app.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import project.smts_app.db.dao.LansiranjeDAO;
import java.io.IOException;
import java.time.LocalDateTime;

public class SmtsController {


    // GUI elementi za formu (samo za dio Kreiraj Lansiranje)
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
    private TextField vrijemeLansiranjaField;
    @FXML
    private TextField raketaIdField;
    @FXML
    private TextField mjestoIdField;
    @FXML
    private Label porukaLabel;

    // Instanca DAO klase
    private LansiranjeDAO lansiranjeDAO = new LansiranjeDAO();

    @FXML
    private VBox mainContentPane;

    // ... (ostali GUI elementi i DAO su isti) ...

    @FXML
    protected void showDetaljiLansiranja() {
        // Učitaj pregled tabele
        // loadView("detalji-lansiranja-view.fxml");
    }

    @FXML
    protected void showKreirajLansiranje() {
        loadView("/project/smts_app/kreiraj-lansiranje-view.fxml");
    }

    @FXML
    protected void showMainMenu() {
        loadView("/project/smts_app/main-menu-content.fxml");
    }

    // Ključna metoda za dinamičko učitavanje sadržaja
    private void loadView(String fxmlFileName) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFileName));
            // Reci FXMLLoader-u da koristi postojeći kontroler
            fxmlLoader.setController(this);

            Parent newContent = fxmlLoader.load();
            mainContentPane.getChildren().setAll(newContent.getChildrenUnmodifiable());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

// ... (ostatak koda je isti)

    /**
     * Metoda koja se poziva klikom na dugme "Kreiraj lansiranje" unutar forme.
     */
    @FXML
    protected void onKreirajLansiranjeClick() {
        try {
            String nazivSatelita = nazivSatelitaField.getText();
            String zemljaProizvodnje = zemljaProizvodnjeField.getText();
            double masaKg = Double.parseDouble(masaKgField.getText());
            int misijaId = Integer.parseInt(misijaIdField.getText());
            int tipId = Integer.parseInt(tipIdField.getText());
            LocalDateTime vrijemeLansiranja = LocalDateTime.parse(vrijemeLansiranjaField.getText());
            int raketaId = Integer.parseInt(raketaIdField.getText());
            int mjestoId = Integer.parseInt(mjestoIdField.getText());

            lansiranjeDAO.kreirajNovoLansiranje(
                    nazivSatelita, zemljaProizvodnje, masaKg, misijaId, tipId,
                    vrijemeLansiranja, raketaId, mjestoId
            );

            porukaLabel.setText("Uspješno kreirano novo lansiranje!");
            porukaLabel.setStyle("-fx-text-fill: green;");

        } catch (Exception e) {
            porukaLabel.setText("Greška prilikom unosa: " + e.getMessage());
            porukaLabel.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        }
    }
}