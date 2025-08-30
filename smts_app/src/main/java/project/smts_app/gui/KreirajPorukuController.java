package project.smts_app.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import project.smts_app.db.dao.KomunikacijaDAO;
import project.smts_app.db.dao.SatelitDAO;
import project.smts_app.db.dao.ZemaljskaStanicaDAO;
import project.smts_app.db.obj.Satelit;
import project.smts_app.db.obj.ZemaljskaStanica;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class KreirajPorukuController {

    @FXML
    private ComboBox<String> posiljalacComboBox;
    @FXML
    private ComboBox<String> primalacComboBox;
    @FXML
    private ComboBox<String> tipPorukeComboBox;
    @FXML
    private TextArea sadrzajTextArea;

    private SatelitDAO satelitDAO = new SatelitDAO();
    private ZemaljskaStanicaDAO stanicaDAO = new ZemaljskaStanicaDAO();
    private KomunikacijaDAO komunikacijaDAO = new KomunikacijaDAO();

    private List<Satelit> sviSateliti;
    private List<ZemaljskaStanica> sveStanice;

    private Runnable refreshCallback;

    public void initialize() {
        try {
            sviSateliti = satelitDAO.dohvatiSveSatelite();
            sveStanice = stanicaDAO.dohvatiSveStanice();

            // Puni ComboBox za pošiljaoca sa satelitima i stanicama
            ObservableList<String> posiljaoci = FXCollections.observableArrayList();
            sviSateliti.forEach(s -> posiljaoci.add("Satelit: " + s.getNaziv()));
            sveStanice.forEach(s -> posiljaoci.add("Stanica: " + s.getNaziv()));
            posiljalacComboBox.setItems(posiljaoci);

            // Puni ComboBox za tipove poruka
            tipPorukeComboBox.setItems(FXCollections.observableArrayList(
                    "Telemetry", "Scientific Data", "Health Check", "Orbital Maneuver Command", "Software Update"
            ));

        } catch (SQLException e) {
            System.err.println("Greška prilikom učitavanja podataka za ComboBoxe: " + e.getMessage());
        }
    }

    @FXML
    private void handlePosiljalacChange() {
        String odabir = posiljalacComboBox.getSelectionModel().getSelectedItem();
        ObservableList<String> primaoci = FXCollections.observableArrayList();
        if (odabir != null) {
            if (odabir.startsWith("Satelit:")) {
                // Ako je pošiljalac satelit, primalac može biti samo stanica
                sveStanice.forEach(s -> primaoci.add("Stanica: " + s.getNaziv()));
            } else {
                // Ako je pošiljalac stanica, primalac može biti samo satelit
                sviSateliti.forEach(s -> primaoci.add("Satelit: " + s.getNaziv()));
            }
        }
        primalacComboBox.setItems(primaoci);
        primalacComboBox.getSelectionModel().selectFirst();
    }

    @FXML
    private void sacuvajPoruku() {
        try {
            String odabranPosiljalac = posiljalacComboBox.getSelectionModel().getSelectedItem();
            String odabranPrimalac = primalacComboBox.getSelectionModel().getSelectedItem();
            String tipPoruke = tipPorukeComboBox.getSelectionModel().getSelectedItem();
            String sadrzaj = sadrzajTextArea.getText();

            if (odabranPosiljalac == null || odabranPrimalac == null || tipPoruke == null || sadrzaj.isEmpty()) {
                System.err.println("Svi podaci moraju biti popunjeni.");
                return;
            }

            int satelitId = -1;
            int stanicaId = -1;

            // Određivanje satelit_id i stanica_id na temelju odabira
            String[] posiljalacParts = odabranPosiljalac.split(": ");
            String[] primalacParts = odabranPrimalac.split(": ");

            if (posiljalacParts[0].equals("Satelit")) {
                satelitId = sviSateliti.stream()
                        .filter(s -> s.getNaziv().equals(posiljalacParts[1]))
                        .findFirst().map(Satelit::getSatelitId).orElse(-1);
                stanicaId = sveStanice.stream()
                        .filter(s -> s.getNaziv().equals(primalacParts[1]))
                        .findFirst().map(ZemaljskaStanica::getStanicaId).orElse(-1);
            } else { // Pošiljalac je Stanica
                stanicaId = sveStanice.stream()
                        .filter(s -> s.getNaziv().equals(posiljalacParts[1]))
                        .findFirst().map(ZemaljskaStanica::getStanicaId).orElse(-1);
                satelitId = sviSateliti.stream()
                        .filter(s -> s.getNaziv().equals(primalacParts[1]))
                        .findFirst().map(Satelit::getSatelitId).orElse(-1);
            }

            if (satelitId != -1 && stanicaId != -1) {
                KomunikacijaDAO.sacuvajNovuPoruku(satelitId, stanicaId, LocalDate.now(), tipPoruke, sadrzaj);
                if (refreshCallback != null) {
                    refreshCallback.run();
                }
                zatvoriProzor();
            } else {
                System.err.println("Greška u odabiru satelita ili stanice.");
            }

        } catch (SQLException e) {
            System.err.println("Greška prilikom spremanja poruke u bazu: " + e.getMessage());
        }
    }

    public void setRefreshCallback(Runnable callback) {
        this.refreshCallback = callback;
    }

    @FXML
    private void zatvoriProzor() {
        Stage stage = (Stage) sadrzajTextArea.getScene().getWindow();
        stage.close();
    }
}
