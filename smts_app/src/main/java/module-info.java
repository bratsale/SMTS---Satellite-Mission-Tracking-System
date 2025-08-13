module project.smts_app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql; // Za JDBC konekciju

    exports project.smts_app.gui; // Izvozi glavni GUI paket
    opens project.smts_app.gui to javafx.fxml; // Omogućava FXML-u pristup GUI elementima i kontroleru
}