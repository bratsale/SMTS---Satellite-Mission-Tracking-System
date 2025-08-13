package project.smts_app.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class SmtsApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Učitavanje FXML fajla
        FXMLLoader fxmlLoader = new FXMLLoader(SmtsApp.class.getResource("/smts-view.fxml"));

        // Kreiranje scene iz FXML fajla
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);

        // Postavljanje naslova prozora
        stage.setTitle("SMTS - Aplikacija za lansiranja");

        // Postavljanje scene na stage (prozor)
        stage.setScene(scene);

        // Prikazivanje prozora
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}