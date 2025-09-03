package project.smts_app.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SmtsConnection {

    // Podaci za konekciju
    private static final String URL = "jdbc:mysql://localhost:3306/smts_baza";
    private static final String USER = "smts"; // Ažuriraj sa svojim korisničkim imenom
    private static final String PASSWORD = "smts"; // Ažuriraj sa svojom lozinkom

    /**
     * Vraća uspostavljenu konekciju sa bazom podataka.
     * @return Connection objekat.
     * @throws SQLException ako dođe do greške pri konekciji.
     */
    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Greška pri uspostavljanju konekcije sa bazom: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}