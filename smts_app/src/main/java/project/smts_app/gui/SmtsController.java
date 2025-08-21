package project.smts_app.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import  project.smts_app.db.obj.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import project.smts_app.db.dao.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.net.URL;
import java.util.ResourceBundle;

public class SmtsController implements Initializable {


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
    @FXML
    private TableView<SatelitOrbita> satelitiOrbiteTable;
    @FXML
    private TableView<SatelitDetalji> sviSatelitiDetaljiTable;
    @FXML
    private TableView<MisijaPartner> misijePartneriTable;
    @FXML
    private TableView<KomunikacijaStanicaSatelit> komunikacijaTable;

    // Instanca DAO klase
    private LansiranjeDAO lansiranjeDAO = new LansiranjeDAO();
    private MisijaDAO misijaDAO = new MisijaDAO();
    private KomunikacijaDAO komunikacijaDAO = new KomunikacijaDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Ova metoda se poziva nakon što su svi @FXML elementi učitani.
        // Ovdje postaviš `CellValueFactory` za kolone.
        // Nemaš ih u svom kodu, ali je dobra praksa dodati ih ovdje.
        // Na primjer:
        // satelitColumn.setCellValueFactory(new PropertyValueFactory<>("satelit"));

        // Provjeri da li je `detaljiLansiranjaTable` inicijalizovana
        if (detaljiLansiranjaTable != null) {
            popuniTabeluLansiranja();
        }
    }

    @FXML
    private VBox mainContentPane;

    @FXML
    private TableView<DetaljiLansiranja> detaljiLansiranjaTable;
    // ... (ostali GUI elementi i DAO su isti) ...

    @FXML
    protected void showKreirajLansiranje() {
        loadView("/project/smts_app/kreiraj-lansiranje.fxml");
    }

    @FXML
    protected void showMainMenu() {
        loadView("/project/smts_app/main-menu-content.fxml");
    }

    @FXML
    protected void showDetaljiLansiranja() {
        loadView("/project/smts_app/detalji-lansiranja.fxml");
        // Popuni tabelu s podacima
    }

    // Ključna metoda za popunjavanje tabele
    private void popuniTabeluLansiranja() {
        try {
            // Dohvati podatke iz DAO klase
            List<DetaljiLansiranja> lansiranjaList = lansiranjeDAO.dohvatiSveDetaljeLansiranja();
            ObservableList<DetaljiLansiranja> data = FXCollections.observableArrayList(lansiranjaList);
            detaljiLansiranjaTable.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    // Dodajemo novu metodu koja će se pozvati klikom na dugme "Obriši"
    @FXML
    protected void obrisiLansiranje() {
        // Provjeri je li korisnik odabrao red u tabeli
        DetaljiLansiranja odabranoLansiranje = detaljiLansiranjaTable.getSelectionModel().getSelectedItem();

        if (odabranoLansiranje != null) {
            try {
                // Pozovi DAO metodu da obrise lansiranje i proslijedi joj ID
                lansiranjeDAO.obrisiLansiranje(odabranoLansiranje.getLansiranjeId());
                System.out.println("Lansiranje je uspješno obrisano.");

                // Nakon brisanja, osvježi tabelu
                popuniTabeluLansiranja();
            } catch (SQLException e) {
                System.err.println("Greška prilikom brisanja lansiranja: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Nijedan red nije odabran.");
        }
    }

    @FXML
    protected void showSatelitiOrbite() {
        loadView("/project/smts_app/sateliti-i-orbite.fxml");
        popuniTabeluSatelitaIOrbite();
    }

    private void popuniTabeluSatelitaIOrbite() {
        try {
            List<SatelitOrbita> satelitiList = lansiranjeDAO.dohvatiSveSateliteIOrbite();
            ObservableList<SatelitOrbita> data = FXCollections.observableArrayList(satelitiList);
            satelitiOrbiteTable.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void showSviSatelitiDetalji() {
        loadView("/project/smts_app/svi-sateliti-detalji.fxml");
        popuniTabeluSvihSatelita();
    }

    private void popuniTabeluSvihSatelita() {
        try {
            List<SatelitDetalji> satelitiList = lansiranjeDAO.dohvatiSveSateliteDetalje();
            ObservableList<SatelitDetalji> data = FXCollections.observableArrayList(satelitiList);
            sviSatelitiDetaljiTable.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void showMisijePartneri() {
        loadView("/project/smts_app/misije-partneri.fxml");
        popuniTabeluMisijePartneri();
    }

    private void popuniTabeluMisijePartneri() {
        try {
            // POZOVITE NOVI DAO!
            List<MisijaPartner> misijeList = misijaDAO.dohvatiMisijeDetaljePartnera();
            ObservableList<MisijaPartner> data = FXCollections.observableArrayList(misijeList);
            misijePartneriTable.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void showKomunikacija() {
        loadView("/project/smts_app/komunikacija.fxml");
        popuniTabeluKomunikacija();
    }

    private void popuniTabeluKomunikacija() {
        try {
            List<KomunikacijaStanicaSatelit> komunikacijeList = komunikacijaDAO.dohvatiDetaljeKomunikacije();
            ObservableList<KomunikacijaStanicaSatelit> data = FXCollections.observableArrayList(komunikacijeList);
            komunikacijaTable.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}