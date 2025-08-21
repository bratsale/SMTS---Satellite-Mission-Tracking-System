module project.smts_app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens project.smts_app.gui to javafx.fxml;
    opens project.smts_app.db.obj to javafx.base; // Dodaj ovu liniju

    exports project.smts_app.gui;
    exports project.smts_app.db.obj;
    exports project.smts_app.db.dao;
    exports project.smts_app.util;
}