package project.smts_app.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import project.smts_app.db.obj.*;
import project.smts_app.util.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import project.smts_app.db.dao.*;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Year;
import java.util.stream.IntStream;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import java.util.function.Predicate;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import java.sql.SQLException;


public class SmtsController implements Initializable {

    // FXML varijable za Kreiraj Lansiranje
    @FXML
    private TextField satelitNazivField;
    @FXML
    private TextField zemljaProizvodnjeField;
    @FXML
    private TextField masaKgField;

    @FXML
    private ComboBox<MisijaDetalji> misijaComboBox;
    @FXML
    private ComboBox<Satelit.Tip> tipComboBox;
    @FXML
    private ComboBox<RaketaNosac> raketaComboBox;
    @FXML
    private ComboBox<MjestoLansiranja> mjestoComboBox;
    @FXML
    private ComboBox<String> zemljaProizvodnjeComboBox;

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
    @FXML
    private VBox glavniKonktejner;

    @FXML
    private TextField filterField;
    @FXML
    private TableView<DetaljiLansiranja> detaljiLansiranjaTable;
    @FXML
    private TableView<SatelitOrbita> satelitiOrbiteTable;
    @FXML
    private TableView<SatelitDetalji> sviSatelitiDetaljiTable;
    @FXML
    private TableView<MisijaPartner> misijePartneriTable;
    @FXML
    private TableView<KomunikacijaStanicaSatelit> komunikacijaTable;
    @FXML
    private TableView<LansiranjeProizvodjac> lansiranjaProizvodjacTable;
    @FXML
    private BarChart<String, Number> proizvodjacChart;
    @FXML
    private TableView<MisijaStatus> misijeStatusTable;
    @FXML
    private PieChart misijeStatusChart;
    @FXML
    private VBox mainContentPane;

    @FXML
    private TextField misijeFilterField;
    @FXML
    private ComboBox<String> misijeStatusComboBox;
    @FXML
    private TextField satelitFilterField;
    @FXML
    private TextField satelitOrbitaFilterField;
    @FXML
    private ComboBox<String> sferaComboBox;

    // Instance DAO klase
    private final LansiranjeDAO lansiranjeDAO = new LansiranjeDAO();
    private final MisijaDAO misijaDAO = new MisijaDAO();
    private final KomunikacijaDAO komunikacijaDAO = new KomunikacijaDAO();
    private final RaketaDAO raketaDAO = new RaketaDAO();
    private final MjestoDAO mjestoDAO = new MjestoDAO();
    private final SatelitDAO satelitDAO = new SatelitDAO();

    // Liste za pretragu
    private FilteredList<DetaljiLansiranja> filteredData;
    private ObservableList<DetaljiLansiranja> masterData = FXCollections.observableArrayList();
    private final ObservableList<SatelitOrbita> masterSatelitiOrbiteData = FXCollections.observableArrayList();
    private FilteredList<SatelitOrbita> filteredSatelitiOrbiteData;

    private final ObservableList<MisijaStatus> masterMisijeStatusData = FXCollections.observableArrayList();
    private FilteredList<MisijaStatus> filteredMisijeStatusData;

    private final ObservableList<SatelitDetalji> masterSatelitiDetaljiData = FXCollections.observableArrayList();
    private FilteredList<SatelitDetalji> filteredSatelitiDetaljiData;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (detaljiLansiranjaTable != null) {
            popuniTabeluLansiranja();
            setupDetaljiLansiranjaTableContextMenu();
        }

        if (filterField != null && detaljiLansiranjaTable != null) {
            setupSearchFilter();
        }

        if (godinaComboBox != null) {
            popuniDatumVrijemeDropdowns();
            popuniComboBoxes();
        }

        if (satelitiOrbiteTable != null) {
            popuniTabeluSatelitaIOrbite();
            setupSatelitiOrbiteSearchAndFilter();
            setupSatelitiOrbiteDoubleClick();
            setupSatelitiOrbiteContextMenu();
        }

        if (sferaComboBox != null) {
            sferaComboBox.getItems().addAll("Sve sfere", "Troposfera", "Stratosfera", "Mezosfera", "Termosfera", "Egzosfera");
            sferaComboBox.getSelectionModel().selectFirst();
            sferaComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> applySatelitOrbitaFilters());
        }

        if (sviSatelitiDetaljiTable != null) {
            popuniTabeluSvihSatelita();
            setupSatelitiDetaljiSearch();
        }

        if (misijeStatusTable != null) {
            popuniMisijeTabeleIGrafikone();
            setupMisijeSearchAndFilter();
            setupMisijeStatusTableDoubleClick();
        }
    }

    private void popuniComboBoxes() {
        try {
            // Popunjavanje Misija ComboBox
            List<MisijaDetalji> misije = misijaDAO.dohvatiSveMisije();
            misijaComboBox.getItems().addAll(misije);

            // Popunjavanje Tipa satelita pomoću SatelitDAO
            List<Satelit.Tip> tipovi = satelitDAO.dohvatiSveTipoveSatelita();
            tipComboBox.setItems(FXCollections.observableArrayList(tipovi));

            // Popunjavanje Raketa ComboBox
            List<RaketaNosac> rakete = raketaDAO.dohvatiSveRakete();
            raketaComboBox.getItems().addAll(rakete);

            // Popunjavanje Mjesto lansiranja ComboBox
            List<MjestoLansiranja> mjesta = mjestoDAO.dohvatiSvaMjestaLansiranja();
            mjestoComboBox.getItems().addAll(mjesta);

            List<String> zemlje = Arrays.asList(
                    "SAD", "Rusija", "Kina", "Japan", "Indija", "Evropska unija",
                    "Kanada", "Ujedinjeno Kraljevstvo", "Izrael", "Južna Koreja",
                    "Brazil", "Australija", "Francuska", "Njemačka", "Italija"
            );
            // Provjera da li je ComboBox inicijalizovan (zbog učitavanja različitih FXML-ova)
            if (zemljaProizvodnjeComboBox != null) {
                zemljaProizvodnjeComboBox.getItems().addAll(zemlje);
            }

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
    }

    private void loadView(String fxmlFileName) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFileName));
            fxmlLoader.setController(this);
            Parent newContent = fxmlLoader.load();
            mainContentPane.getChildren().setAll(newContent.getChildrenUnmodifiable());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    protected void kreirajLansiranje() {
        try {
            // Provjera popunjenosti sada provjerava ComboBox
            if (satelitNazivField.getText().isEmpty() ||
                    misijaComboBox.getValue() == null ||
                    tipComboBox.getValue() == null ||
                    zemljaProizvodnjeComboBox.getValue() == null || // KLJUČNA IZMJENA
                    masaKgField.getText().isEmpty() ||
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
            String zemlja = zemljaProizvodnjeComboBox.getValue(); // KLJUČNA IZMJENA
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
        masaKgField.clear();
        zemljaProizvodnjeComboBox.setValue(null); // KLJUČNA IZMJENA
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
    protected void obrisiLansiranje() {
        DetaljiLansiranja odabranoLansiranje = detaljiLansiranjaTable.getSelectionModel().getSelectedItem();
        if (odabranoLansiranje != null) {
            try {
                lansiranjeDAO.obrisiLansiranje(odabranoLansiranje.getLansiranjeId());
                System.out.println("Lansiranje je uspješno obrisano.");
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
        setupSatelitiOrbiteSearchAndFilter();
    }

    private void popuniTabeluSatelitaIOrbite() {
        try {
            List<SatelitOrbita> satelitiList = satelitDAO.dohvatiSveSateliteIOrbite();
            masterSatelitiOrbiteData.clear();
            masterSatelitiOrbiteData.addAll(satelitiList);
            satelitiOrbiteTable.setItems(masterSatelitiOrbiteData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupSatelitiOrbiteSearchAndFilter() {
        filteredSatelitiOrbiteData = new FilteredList<>(masterSatelitiOrbiteData, p -> true);

        satelitOrbitaFilterField.textProperty().addListener((observable, oldValue, newValue) -> applySatelitOrbitaFilters());

        SortedList<SatelitOrbita> sortedData = new SortedList<>(filteredSatelitiOrbiteData);
        sortedData.comparatorProperty().bind(satelitiOrbiteTable.comparatorProperty());
        satelitiOrbiteTable.setItems(sortedData);
    }

    private void applySatelitOrbitaFilters() {
        filteredSatelitiOrbiteData.setPredicate(satelitOrbita -> {
            boolean matchesSearch = true;
            String searchText = satelitOrbitaFilterField.getText();
            if (searchText != null && !searchText.isEmpty()) {
                String lowerCaseFilter = searchText.toLowerCase();
                matchesSearch = satelitOrbita.getNazivSatelita().toLowerCase().contains(lowerCaseFilter) ||
                        satelitOrbita.getTipSatelita().toLowerCase().contains(lowerCaseFilter) ||
                        satelitOrbita.getNazivMisije().toLowerCase().contains(lowerCaseFilter) ||
                        String.valueOf(satelitOrbita.getVisinaOrbiteKm()).contains(lowerCaseFilter) ||
                        String.valueOf(satelitOrbita.getInklinacijaOrbite()).contains(lowerCaseFilter);
            }

            boolean matchesSfera = true;
            String selectedSfera = sferaComboBox.getSelectionModel().getSelectedItem();
            if (selectedSfera != null && !"Sve sfere".equals(selectedSfera)) {
                double visina = satelitOrbita.getVisinaOrbiteKm();
                switch (selectedSfera) {
                    case "Troposfera":
                        matchesSfera = visina >= 0 && visina <= 20;
                        break;
                    case "Stratosfera":
                        matchesSfera = visina > 20 && visina <= 50;
                        break;
                    case "Mezosfera":
                        matchesSfera = visina > 50 && visina <= 85;
                        break;
                    case "Termosfera":
                        matchesSfera = visina > 85 && visina <= 600;
                        break;
                    case "Egzosfera":
                        matchesSfera = visina > 600;
                        break;
                }
            }
            return matchesSearch && matchesSfera;
        });
    }

    private void setupSatelitiOrbiteDoubleClick() {
        satelitiOrbiteTable.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                SatelitOrbita odabraniSatelit = satelitiOrbiteTable.getSelectionModel().getSelectedItem();
                if (odabraniSatelit != null) {
                    try {
                        prikaziDetaljeAtmosfere(odabraniSatelit.getNazivSatelita(), odabraniSatelit.getVisinaOrbiteKm());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void prikaziDetaljeAtmosfere(String nazivSatelita, double visinaKm) throws IOException {
        URL resourceUrl = getClass().getResource("/project/smts_app/detalji-atmosfere.fxml");
        if (resourceUrl == null) {
            throw new IOException("FXML file not found: /project/smts_app/detalji-atmosfere.fxml");
        }

        FXMLLoader loader = new FXMLLoader(resourceUrl);
        Parent root = loader.load();

        DetaljiAtmosfereController controller = loader.getController();
        controller.setDetaljiSatelita(nazivSatelita, visinaKm);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Položaj satelita u atmosferi");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }


    @FXML
    protected void showSviSatelitiDetalji() {
        loadView("/project/smts_app/svi-sateliti-detalji.fxml");
        popuniTabeluSvihSatelita();
        setupSatelitiDetaljiSearch();
    }

    private void popuniTabeluSvihSatelita() {
        try {
            List<SatelitDetalji> satelitiList = satelitDAO.dohvatiSveSateliteDetalje();
            masterSatelitiDetaljiData.clear();
            masterSatelitiDetaljiData.addAll(satelitiList);
            sviSatelitiDetaljiTable.setItems(masterSatelitiDetaljiData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupSatelitiDetaljiSearch() {
        filteredSatelitiDetaljiData = new FilteredList<>(masterSatelitiDetaljiData, p -> true);
        satelitFilterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredSatelitiDetaljiData.setPredicate(satelitDetalji -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (satelitDetalji.getNazivSatelita().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (satelitDetalji.getZemljaProizvodnje().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (satelitDetalji.getNazivMisije().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (satelitDetalji.getTipSatelita().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(satelitDetalji.getMasaKg()).contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<SatelitDetalji> sortedData = new SortedList<>(filteredSatelitiDetaljiData);
        sortedData.comparatorProperty().bind(sviSatelitiDetaljiTable.comparatorProperty());
        sviSatelitiDetaljiTable.setItems(sortedData);
    }


    @FXML
    protected void showMisijePartneri() {
        loadView("/project/smts_app/misije-partneri.fxml");
        popuniTabeluMisijePartneri();
    }

    private void popuniTabeluMisijePartneri() {
        try {
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
        setupKomunikacijaTableDoubleClick(); // Dodajte ovu liniju
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

    private void popuniTabeluLansiranja() {
        try {
            List<DetaljiLansiranja> lansiranjaList = lansiranjeDAO.dohvatiSveDetaljeLansiranja();
            masterData.clear();
            masterData.addAll(lansiranjaList);
            detaljiLansiranjaTable.setItems(masterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupSearchFilter() {
        filteredData = new FilteredList<>(masterData, p -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(detaljiLansiranja -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (detaljiLansiranja.getSatelit().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (detaljiLansiranja.getRaketaNosac().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (detaljiLansiranja.getMisija().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<DetaljiLansiranja> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(detaljiLansiranjaTable.comparatorProperty());
        detaljiLansiranjaTable.setItems(sortedData);
    }

    @FXML
    protected void showLansiranjaPoProizvodjacu() {
        loadView("/project/smts_app/lansiranja-po-proizvodjacu.fxml");
        popuniTabeluLansiranjaPoProizvodjacu();
        popuniGrafikonProizvodjaca();
    }

    private void popuniTabeluLansiranjaPoProizvodjacu() {
        try {
            List<LansiranjeProizvodjac> lista = lansiranjeDAO.dohvatiLansiranjaPoProizvodjacu();
            ObservableList<LansiranjeProizvodjac> data = FXCollections.observableArrayList(lista);
            lansiranjaProizvodjacTable.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void popuniGrafikonProizvodjaca() {
        try {
            List<LansiranjeProizvodjac> lista = lansiranjeDAO.dohvatiLansiranjaPoProizvodjacu();
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Broj lansiranja");
            for (LansiranjeProizvodjac lp : lista) {
                series.getData().add(new XYChart.Data<>(lp.getProizvodjac(), lp.getBrojLansiranja()));
            }
            proizvodjacChart.getData().clear();
            proizvodjacChart.getData().add(series);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void showMisijePoStatusu() {
        loadView("/project/smts_app/misije-po-statusu.fxml");
        // Popuni i pripremi sve elemente nakon što se FXML učita
        popuniMisijeTabeleIGrafikone();
        setupMisijeSearchAndFilter();
        // Dodaj listener za dvoklik
        setupMisijeStatusTableDoubleClick();
    }

    private void popuniMisijeTabeleIGrafikone() {
        try {
            List<MisijaStatus> lista = misijaDAO.dohvatiMisijePoStatusu();
            masterMisijeStatusData.clear();
            masterMisijeStatusData.addAll(lista);
            misijeStatusTable.setItems(masterMisijeStatusData);

            ObservableList<String> statuses = FXCollections.observableArrayList();
            statuses.add("Sve misije");
            statuses.add("Aktivna");
            statuses.add("Završena");
            misijeStatusComboBox.setItems(statuses);
            misijeStatusComboBox.getSelectionModel().selectFirst();

            popuniGrafikonMisijeStatusa(masterMisijeStatusData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void popuniZemljeProizvodnjeComboBox() {
        List<String> zemlje = Arrays.asList(
                "SAD", "Rusija", "Kina", "Japan", "Indija", "Evropska unija", "Kanada", "Ujedinjeno Kraljevstvo",
                "Izrael", "Južna Koreja", "Brazil", "Australija", "Francuska", "Njemačka", "Italija"
        );
        zemljaProizvodnjeComboBox.getItems().addAll(zemlje);
    }

    private void setupMisijeSearchAndFilter() {
        filteredMisijeStatusData = new FilteredList<>(masterMisijeStatusData, p -> true);

        misijeFilterField.textProperty().addListener((observable, oldValue, newValue) -> {
            updateMisijeFilter();
        });

        misijeStatusComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            updateMisijeFilter();
        });

        SortedList<MisijaStatus> sortedData = new SortedList<>(filteredMisijeStatusData);
        sortedData.comparatorProperty().bind(misijeStatusTable.comparatorProperty());
        misijeStatusTable.setItems(sortedData);
    }

    private void updateMisijeFilter() {
        filteredMisijeStatusData.setPredicate(misijaStatus -> {
            String filterText = misijeFilterField.getText() == null ? "" : misijeFilterField.getText().toLowerCase();
            String selectedStatus = misijeStatusComboBox.getValue();

            boolean matchesName = misijaStatus.getNazivMisije().toLowerCase().contains(filterText);
            boolean matchesStatus = (selectedStatus == null || selectedStatus.equals("Sve misije") || misijaStatus.getStatus().equals(selectedStatus));

            return matchesName && matchesStatus;
        });

        popuniGrafikonMisijeStatusa(filteredMisijeStatusData);
    }

    private void popuniGrafikonMisijeStatusa(ObservableList<MisijaStatus> data) {
        Map<String, Long> statusCount = data.stream()
                .collect(Collectors.groupingBy(MisijaStatus::getStatus, Collectors.counting()));
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (Map.Entry<String, Long> entry : statusCount.entrySet()) {
            pieChartData.add(new PieChart.Data(entry.getKey() + " (" + entry.getValue() + ")", entry.getValue()));
        }
        misijeStatusChart.setData(pieChartData);
    }


    protected void prikaziDetaljeSatelita() {
        DetaljiLansiranja odabranoLansiranje = detaljiLansiranjaTable.getSelectionModel().getSelectedItem();

        if (odabranoLansiranje != null) {
            try {

                SatelitDetalji satelitDetalji = satelitDAO.dohvatiDetaljeSatelitaPoLansiranjuId(odabranoLansiranje.getLansiranjeId());

                if (satelitDetalji != null) {
                    // Pozivamo pomoćnu metodu koja otvara novi prozor
                    prikaziProzorSDetaljima(satelitDetalji);
                } else {
                    System.out.println("Detalji satelita nisu pronađeni.");
                }

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Greška prilikom dohvaćanja detalja satelita iz baze.");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Greška prilikom otvaranja prozora sa detaljima.");
            }
        }
    }


    private void prikaziProzorSDetaljima(SatelitDetalji detalji) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/smts_app/detalji-satelita.fxml"));
        Parent root = loader.load();

        DetaljiSatelitaController controller = loader.getController();
        controller.setDetaljiSatelita(detalji);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Detalji Satelita");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    private void setupDetaljiLansiranjaTableContextMenu() {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem showSatelitDetails = new MenuItem("Prikaži detalje satelita");
        MenuItem showRaketaDetails = new MenuItem("Prikaži detalje rakete");
        MenuItem showMisijaDetails = new MenuItem("Prikaži detalje misije");

        contextMenu.getItems().addAll(showSatelitDetails, showRaketaDetails, showMisijaDetails);

        detaljiLansiranjaTable.setContextMenu(contextMenu);

        showSatelitDetails.setOnAction(event -> prikaziDetaljeSatelita());
        showRaketaDetails.setOnAction(event -> prikaziDetaljeRakete());
        showMisijaDetails.setOnAction(event -> prikaziDetaljeMisije());
    }

    private void prikaziDetaljeRakete() {
        DetaljiLansiranja odabranoLansiranje = detaljiLansiranjaTable.getSelectionModel().getSelectedItem();
        if (odabranoLansiranje != null) {
            try {
                RaketaDetalji detalji = raketaDAO.dohvatiDetaljeRaketePoLansiranjuId(odabranoLansiranje.getLansiranjeId());
                if (detalji != null) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/smts_app/detalji-rakete.fxml"));
                    Parent root = loader.load();
                    DetaljiRaketeController controller = loader.getController();
                    controller.setDetaljiRakete(detalji);
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Detalji Rakete");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void prikaziDetaljeMisije() {
        DetaljiLansiranja odabranoLansiranje = detaljiLansiranjaTable.getSelectionModel().getSelectedItem();
        if (odabranoLansiranje != null) {
            try {
                MisijaDetalji detalji = misijaDAO.dohvatiDetaljeMisijePoLansiranjuId(odabranoLansiranje.getLansiranjeId());
                if (detalji != null) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/smts_app/detalji-misije.fxml"));
                    Parent root = loader.load();
                    DetaljiMisijeController controller = loader.getController();
                    controller.setDetaljiMisije(detalji);
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Detalji Misije");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setupMisijeStatusTableDoubleClick() {
        misijeStatusTable.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                MisijaStatus odabranaMisija = misijeStatusTable.getSelectionModel().getSelectedItem();
                if (odabranaMisija != null) {
                    try {
                        MisijaDetalji detalji = misijaDAO.dohvatiDetaljeMisijePoMisijiId(odabranaMisija.getMisijaId());
                        if (detalji != null) {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/smts_app/detalji-misije.fxml"));
                            Parent root = loader.load();
                            DetaljiMisijeController controller = loader.getController();
                            controller.setDetaljiMisije(detalji);

                            Stage stage = new Stage();
                            stage.setScene(new Scene(root));
                            stage.setTitle("Detalji Misije");
                            stage.initModality(Modality.APPLICATION_MODAL);
                            stage.showAndWait();
                        }
                    } catch (SQLException | IOException e) {
                        System.err.println("Greška prilikom dohvaćanja detalja misije ili otvaranja prozora.");
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void setupKomunikacijaTableDoubleClick() {
        komunikacijaTable.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                KomunikacijaStanicaSatelit odabranaKomunikacija = komunikacijaTable.getSelectionModel().getSelectedItem();
                if (odabranaKomunikacija != null) {
                    try {
                        prikaziDetaljeKomunikacije(odabranaKomunikacija);
                    } catch (Exception e) {
                        System.err.println("Greška prilikom otvaranja prozora s detaljima komunikacije: " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void setupSatelitiOrbiteContextMenu() {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem urediMenuItem = new MenuItem("Uredi");
        contextMenu.getItems().add(urediMenuItem);

        satelitiOrbiteTable.setContextMenu(contextMenu);

        urediMenuItem.setOnAction(event -> {
            SatelitOrbita odabraniSatelit = satelitiOrbiteTable.getSelectionModel().getSelectedItem();
            if (odabraniSatelit != null) {
                try {
                    prikaziProzorZaUredjivanjeOrbite(odabraniSatelit);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void prikaziProzorZaUredjivanjeOrbite(SatelitOrbita satelit) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/smts_app/uredi-orbitu.fxml"));
        Parent root = loader.load();

        UrediOrbituController controller = loader.getController();
        controller.setSatelit(satelit);

        Stage stage = new Stage();
        stage.setTitle("Postavi novu orbitu");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        // Nakon zatvaranja prozora, provjerite jesu li podaci ažurirani
        if (controller.isAzurirano()) {
            try {
                double novaVisina = controller.getNovaVisina();
                double novaInklinacija = controller.getNovaInklinacija();

                // Pozovite DAO metodu za ažuriranje u bazi
                satelitDAO.azurirajOrbitu(satelit.getSatelitId(), novaVisina, novaInklinacija);

                // Osvježite tabelu u glavnom prozoru
                popuniTabeluSatelitaIOrbite();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void prikaziDetaljeKomunikacije(KomunikacijaStanicaSatelit komunikacija) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/smts_app/detalji-komunikacije.fxml"));
        Parent root = loader.load();

        DetaljiKomunikacijeController controller = loader.getController();
        controller.setDetaljiKomunikacije(komunikacija);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Detalji komunikacije");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    protected void kreirajPoruku() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/smts_app/kreiraj-poruku.fxml"));
            Parent root = loader.load();

            KreirajPorukuController controller = loader.getController();
            controller.setRefreshCallback(() -> popuniTabeluKomunikacija());

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Kreiraj poruku");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            System.err.println("Greška prilikom otvaranja prozora za kreiranje poruke: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    protected void prikaziVizualizacijuMisija() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/smts_app/misije-vizualizacija.fxml"));
            Parent root = loader.load();

            MisijeVizualizacijaController controller = loader.getController();

            List<MisijaPartner> partneri = misijaDAO.dohvatiMisijeDetaljePartnera();
            controller.prikaziVizualizaciju(partneri);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Vizualizacija misija i partnera");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        } catch (IOException e) {
            System.err.println("Greška prilikom otvaranja vizualizacije misija: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

