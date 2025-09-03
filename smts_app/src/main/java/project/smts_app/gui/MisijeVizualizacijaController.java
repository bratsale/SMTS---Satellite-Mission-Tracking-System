package project.smts_app.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import project.smts_app.db.obj.*;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.scene.Node;
import javafx.stage.Stage;

public class MisijeVizualizacijaController implements Initializable {

    @FXML
    private PieChart ulogePieChart;
    @FXML
    private TableView<MisijaPartner> detaljiMisijaTable;
    @FXML
    private Label tabelaNaslov;

    private SmtsController parentController;
    private List<MisijaPartner> sviPartneri;

    public void setParentController(SmtsController parentController) {
        this.parentController = parentController;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicijalizacija će se dogoditi nakon što se pozove metoda 'prikaziVizualizaciju'
    }

    public void prikaziVizualizaciju(List<MisijaPartner> partneri) {
        this.sviPartneri = partneri;

        Map<String, Long> brojPartneraPoUlogama = partneri.stream()
                .collect(Collectors.groupingBy(MisijaPartner::getUlogaOperatera, Collectors.counting()));

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        brojPartneraPoUlogama.forEach((uloga, count) -> {
            pieChartData.add(new PieChart.Data(uloga + " (" + count + ")", count));
        });
        ulogePieChart.setData(pieChartData);
        ulogePieChart.setTitle("Udio uloga partnera");

        ulogePieChart.getData().forEach(data -> {
            data.getNode().setOnMouseClicked(event -> {
                String uloga = data.getName().substring(0, data.getName().indexOf(" ("));
                filtrirajTabeluPoUlozi(uloga);
            });
        });

        detaljiMisijaTable.setItems(FXCollections.observableArrayList(sviPartneri));
    }

    private void filtrirajTabeluPoUlozi(String uloga) {
        List<MisijaPartner> filtriraniPartneri = sviPartneri.stream()
                .filter(p -> p.getUlogaOperatera().equalsIgnoreCase(uloga))
                .collect(Collectors.toList());

        detaljiMisijaTable.setItems(FXCollections.observableArrayList(filtriraniPartneri));
        tabelaNaslov.setText("Partneri s ulogom: " + uloga);
    }

    @FXML
    public void nazad(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}