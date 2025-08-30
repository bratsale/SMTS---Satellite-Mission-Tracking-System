module project.smts_app {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.swing;
    requires javafx.media;
    requires java.sql;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens project.smts_app.gui to javafx.fxml;
    opens project.smts_app.db.obj to javafx.base;

    exports project.smts_app.gui;
    exports project.smts_app.db.obj;
    exports project.smts_app.db.dao;
    exports project.smts_app.util;
}