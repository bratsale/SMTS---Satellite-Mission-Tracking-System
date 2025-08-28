package project.smts_app.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import project.smts_app.db.obj.*;
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
import javafx.scene.Parent;
import java.io.IOException;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseButton;

public class SmtsController implements Initializable {

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
    private ComboBox<Satelit.Tip> tipComboBox; // Promijenjeno
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

    // FXML varijable za ostale poglede
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

    // Instance DAO klase
    private LansiranjeDAO lansiranjeDAO = new LansiranjeDAO();
    private MisijaDAO misijaDAO = new MisijaDAO();
    private KomunikacijaDAO komunikacijaDAO = new KomunikacijaDAO();
    private RaketaDAO raketaDAO = new RaketaDAO();
    private MjestoDAO mjestoDAO = new MjestoDAO();
    private SatelitDAO satelitDAO = new SatelitDAO(); // Nova DAO instanca

    // Liste za pretragu
    private FilteredList<DetaljiLansiranja> filteredData;
    private ObservableList<DetaljiLansiranja> masterData = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Provjeri je li tabela inicijalizirana (nalazi se u FXML-u)
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
    }

    private void popuniComboBoxes() {
        try {
            // Popunjavanje Misija ComboBox
            List<MisijaDetalji> misije = misijaDAO.dohvatiSveMisije();
            misijaComboBox.getItems().addAll(misije);

            // Popunjavanje Tipa satelita pomoću SatelitDAO-a
            List<Satelit.Tip> tipovi = satelitDAO.dohvatiSveTipoveSatelita();
            tipComboBox.setItems(FXCollections.observableArrayList(tipovi));

            // Popunjavanje Raketa ComboBox
            List<RaketaNosac> rakete = raketaDAO.dohvatiSveRakete();
            raketaComboBox.getItems().addAll(rakete);

            // Popunjavanje Mjesto lansiranja ComboBox
            List<MjestoLansiranja> mjesta = mjestoDAO.dohvatiSvaMjestaLansiranja();
            mjestoComboBox.getItems().addAll(mjesta);

        } catch (Exception e) {
            statusLabel.setText("Greška pri dohvaćanju podataka iz baze.");
            statusLabel.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        }
    }

    private void popuniDatumVrijemeDropdowns() {
        // Popuni godine od 1950. do tekuće godine
        int currentYear = Year.now().getValue();
        godinaComboBox.getItems().addAll(IntStream.rangeClosed(1950, currentYear).boxed().collect(Collectors.toList()));

        // Popuni mjesece
        mjesecComboBox.getItems().addAll(IntStream.rangeClosed(1, 12).boxed().collect(Collectors.toList()));

        // Popuni sate i minute
        satComboBox.getItems().addAll(IntStream.rangeClosed(0, 23).boxed().collect(Collectors.toList()));
        minutComboBox.getItems().addAll(IntStream.rangeClosed(0, 59).boxed().collect(Collectors.toList()));

        // Dodaj listenere za dinamičko ažuriranje dana
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

    //
    // Glavne metode za prebacivanje prikaza
    //
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

    /**
     * Metoda koja se poziva klikom na dugme "Kreiraj lansiranje".
     */
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

            // Dohvatanje ID-jeva iz odabranih objekata
            int misijaId = misijaComboBox.getValue().getMisijaId();
            int tipId = tipComboBox.getValue().getTipId(); // Promijenjeno
            int raketaId = raketaComboBox.getValue().getRaketaId();
            int mjestoId = mjestoComboBox.getValue().getMjestoId();

            // Kreiranje LocalDateTime objekta iz ComboBox-ova
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


    //
    // Metode za ostale poglede i funkcionalnosti
    //

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
        popuniTabeluMisijeStatusa();
        popuniGrafikonMisijeStatusa();
    }

    private void popuniTabeluMisijeStatusa() {
        try {
            List<MisijaStatus> lista = misijaDAO.dohvatiMisijePoStatusu();
            ObservableList<MisijaStatus> data = FXCollections.observableArrayList(lista);
            misijeStatusTable.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void popuniGrafikonMisijeStatusa() {
        try {
            List<MisijaStatus> lista = misijaDAO.dohvatiMisijePoStatusu();
            Map<String, Long> statusCount = lista.stream()
                    .collect(Collectors.groupingBy(MisijaStatus::getStatus, Collectors.counting()));
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            for (Map.Entry<String, Long> entry : statusCount.entrySet()) {
                pieChartData.add(new PieChart.Data(entry.getKey() + " (" + entry.getValue() + ")", entry.getValue()));
            }
            misijeStatusChart.setData(pieChartData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Prikazuje detalje o odabranom satelitu.
     * Ova metoda se poziva dvoklikom na red u tabeli.
     */
    protected void prikaziDetaljeSatelita() {
        DetaljiLansiranja odabranoLansiranje = detaljiLansiranjaTable.getSelectionModel().getSelectedItem();

        if (odabranoLansiranje != null) {
            try {
                // Ovdje treba dohvatiti detalje satelita iz baze na osnovu ID-a lansiranja
                // (Ovaj dio zahtijeva metodu u SatelitDAO)
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

    /**
     * Pomoćna metoda za otvaranje novog prozora s detaljima.
     */
    private void prikaziProzorSDetaljima(SatelitDetalji detalji) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/smts_app/detalji-satelita.fxml"));
        Parent root = loader.load();

        DetaljiSatelitaController controller = loader.getController();
        controller.setDetaljiSatelita(detalji);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Detalji Satelita");
        stage.initModality(Modality.APPLICATION_MODAL); // Blokira glavni prozor
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
}