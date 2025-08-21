package project.smts_app.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class SmtsApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        try {
            // Ispravna putanja do FXML fajla, bazirana na tvojoj strukturi
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/project/smts_app/smts-view.fxml"));

            Parent root = fxmlLoader.load();
            SmtsController controller = fxmlLoader.getController();

            // Prikazujemo početni meni pri pokretanju
            controller.showMainMenu();

            Scene scene = new Scene(root, 800, 600);
            stage.setTitle("SMTS - Aplikacija za lansiranja");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Greška prilikom učitavanja FXML fajla: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}