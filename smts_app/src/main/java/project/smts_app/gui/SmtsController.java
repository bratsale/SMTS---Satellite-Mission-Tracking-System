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
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableView;
import javafx.scene.chart.PieChart;
import java.util.stream.Collectors;

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
    private TextField filterField;
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

    // Instanca DAO klase
    private LansiranjeDAO lansiranjeDAO = new LansiranjeDAO();
    private MisijaDAO misijaDAO = new MisijaDAO();
    private KomunikacijaDAO komunikacijaDAO = new KomunikacijaDAO();
    private FilteredList<DetaljiLansiranja> filteredData;
    private ObservableList<DetaljiLansiranja> masterData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Provjeri da li je glavna tabela inicijalizovana
        if (detaljiLansiranjaTable != null) {
            popuniTabeluLansiranja();
        }
        // Provjeri da li je filterField inicijalizovano
        if (filterField != null && detaljiLansiranjaTable != null) {
            setupSearchFilter();
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
        // Omotaj "masterData" u "FilteredList" (inicijalno prikazuje sve podatke).
        filteredData = new FilteredList<>(masterData, p -> true);

        // Dodaj Listener koji će pratiti promjene u tekstualnom polju za pretragu.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(detaljiLansiranja -> {
                // Ako je polje za pretragu prazno, prikaži sve podatke.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Uporedi pojam za pretragu s podacima u kolonama (case-insensitive).
                String lowerCaseFilter = newValue.toLowerCase();

                if (detaljiLansiranja.getSatelit().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Match found in satelit.
                } else if (detaljiLansiranja.getRaketaNosac().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Match found in raketa_nosac.
                } else if (detaljiLansiranja.getMisija().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Match found in misija.
                }
                return false; // No match found.
            });
        });

        // Omotaj filtriranu listu u SortedList (za sortiranje).
        SortedList<DetaljiLansiranja> sortedData = new SortedList<>(filteredData);

        // Poveži SortedList komparator s komparatorom TableView-a.
        sortedData.comparatorProperty().bind(detaljiLansiranjaTable.comparatorProperty());

        // Postavi SortedList kao izvor podataka za TableView.
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

            // Grupiraj misije po statusu i broji ih
            Map<String, Long> statusCount = lista.stream()
                    .collect(Collectors.groupingBy(MisijaStatus::getStatus, Collectors.counting()));

            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

            // Kreiraj podatke za grafikon
            for (Map.Entry<String, Long> entry : statusCount.entrySet()) {
                pieChartData.add(new PieChart.Data(entry.getKey() + " (" + entry.getValue() + ")", entry.getValue()));
            }

            misijeStatusChart.setData(pieChartData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}