package project.smts_app.gui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import project.smts_app.db.dao.*;
import project.smts_app.db.obj.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class KreirajLansiranjeController implements Initializable {

    // FXML varijable za Kreiraj Lansiranje
    @FXML
    private TextField satelitNazivField;
    @FXML
    private TextField zemljaProizvodnjeField;
    @FXML
    private TextField masaKgField;

    // Promijenjeni ComboBox-ovi umjesto TextField-a
    @FXML
    private ComboBox<MisijaDetalji> misijaComboBox;
    @FXML
    private ComboBox<Satelit.Tip> tipComboBox;
    @FXML
    private ComboBox<RaketaNosac> raketaComboBox;
    @FXML
    private ComboBox<MjestoLansiranja> mjestoComboBox;

    @FXML
    private ComboBox<Integer> danComboBox;
    @FXML
    private ComboBox<Integer> mjesecComboBox;
    @FXML
    private ComboBox<Integer> godinaComboBox;
    @FXML
    private ComboBox<Integer> satComboBox;
    @FXML
    private ComboBox<Integer> minutComboBox;
    @FXML
    private Label statusLabel;

    // Instance DAO klase
    private LansiranjeDAO lansiranjeDAO = new LansiranjeDAO();
    private MisijaDAO misijaDAO = new MisijaDAO();
    private RaketaDAO raketaDAO = new RaketaDAO();
    private MjestoDAO mjestoDAO = new MjestoDAO();
    private SatelitDAO satelitDAO = new SatelitDAO();

    private SmtsController parentController; // Referenca na glavni kontroler

    /**
     * Postavlja referencu na glavni kontroler.
     * @param parentController Glavni kontroler aplikacije.
     */
    public void setParentController(SmtsController parentController) {
        this.parentController = parentController;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (godinaComboBox != null) {
            popuniDatumVrijemeDropdowns();
            popuniComboBoxes();
        }
    }

    private void popuniComboBoxes() {
        try {
            List<MisijaDetalji> misije = misijaDAO.dohvatiSveMisije();
            misijaComboBox.getItems().addAll(misije);

            List<Satelit.Tip> tipovi = satelitDAO.dohvatiSveTipoveSatelita();
            tipComboBox.setItems(FXCollections.observableArrayList(tipovi));

            List<RaketaNosac> rakete = raketaDAO.dohvatiSveRakete();
            raketaComboBox.getItems().addAll(rakete);

            List<MjestoLansiranja> mjesta = mjestoDAO.dohvatiSvaMjestaLansiranja();
            mjestoComboBox.getItems().addAll(mjesta);

        } catch (Exception e) {
            statusLabel.setText("Greška pri dohvaćanju podataka iz baze.");
            statusLabel.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        }
    }

    private void popuniDatumVrijemeDropdowns() {
        int currentYear = Year.now().getValue();
        godinaComboBox.getItems().addAll(IntStream.rangeClosed(1950, currentYear).boxed().collect(Collectors.toList()));
        mjesecComboBox.getItems().addAll(IntStream.rangeClosed(1, 12).boxed().collect(Collectors.toList()));
        satComboBox.getItems().addAll(IntStream.rangeClosed(0, 23).boxed().collect(Collectors.toList()));
        minutComboBox.getItems().addAll(IntStream.rangeClosed(0, 59).boxed().collect(Collectors.toList()));

        mjesecComboBox.valueProperty().addListener((obs, oldVal, newVal) -> azurirajDane());
        godinaComboBox.valueProperty().addListener((obs, oldVal, newVal) -> azurirajDane());
    }

    private void azurirajDane() {
        danComboBox.getItems().clear();
        Integer godina = godinaComboBox.getValue();
        Integer mjesec = mjesecComboBox.getValue();

        if (godina != null && mjesec != null) {
            int brojDanaUMjesecu = java.time.YearMonth.of(godina, mjesec).lengthOfMonth();
            danComboBox.getItems().addAll(IntStream.rangeClosed(1, brojDanaUMjesecu).boxed().collect(Collectors.toList()));
        }
    }

    @FXML
    protected void kreirajLansiranje() {
        try {
            if (satelitNazivField.getText().isEmpty() ||
                    zemljaProizvodnjeField.getText().isEmpty() ||
                    masaKgField.getText().isEmpty() ||
                    misijaComboBox.getValue() == null ||
                    tipComboBox.getValue() == null ||
                    danComboBox.getValue() == null ||
                    mjesecComboBox.getValue() == null ||
                    godinaComboBox.getValue() == null ||
                    satComboBox.getValue() == null ||
                    minutComboBox.getValue() == null ||
                    raketaComboBox.getValue() == null ||
                    mjestoComboBox.getValue() == null) {

                statusLabel.setText("Greška: Popunite sva polja!");
                statusLabel.setStyle("-fx-text-fill: red;");
                return;
            }

            String naziv = satelitNazivField.getText();
            String zemlja = zemljaProizvodnjeField.getText();
            double masa = Double.parseDouble(masaKgField.getText());

            int misijaId = misijaComboBox.getValue().getMisijaId();
            int tipId = tipComboBox.getValue().getTipId();
            int raketaId = raketaComboBox.getValue().getRaketaId();
            int mjestoId = mjestoComboBox.getValue().getMjestoId();

            LocalDate datum = LocalDate.of(godinaComboBox.getValue(), mjesecComboBox.getValue(), danComboBox.getValue());
            LocalTime vrijeme = LocalTime.of(satComboBox.getValue(), minutComboBox.getValue());
            LocalDateTime vrijemeLansiranja = LocalDateTime.of(datum, vrijeme);

            lansiranjeDAO.kreirajNovoLansiranje(
                    naziv, zemlja, masa, misijaId, tipId,
                    vrijemeLansiranja, raketaId, mjestoId
            );

            resetirajPolja();
            statusLabel.setText("Uspješno kreirano novo lansiranje!");
            statusLabel.setStyle("-fx-text-fill: green;");
        } catch (NumberFormatException e) {
            statusLabel.setText("Greška pri unosu brojeva. Provjerite polje 'Masa (kg)'.");
            statusLabel.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        } catch (Exception e) {
            statusLabel.setText("Greška: " + e.getMessage());
            statusLabel.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        }
    }

    private void resetirajPolja() {
        satelitNazivField.clear();
        zemljaProizvodnjeField.clear();
        masaKgField.clear();
        misijaComboBox.setValue(null);
        tipComboBox.setValue(null);
        danComboBox.setValue(null);
        mjesecComboBox.setValue(null);
        godinaComboBox.setValue(null);
        satComboBox.setValue(null);
        minutComboBox.setValue(null);
        raketaComboBox.setValue(null);
        mjestoComboBox.setValue(null);
    }

    @FXML
    protected void showMainMenu() {
        if (parentController != null) {
            parentController.showMainMenu();
        }
    }
}
