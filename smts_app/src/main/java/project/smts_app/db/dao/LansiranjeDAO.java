package project.smts_app.db.dao; // Prilagodi package

import project.smts_app.db.obj.Lansiranje;
import project.smts_app.util.SmtsConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class LansiranjeDAO {

    public void kreirajNovoLansiranje(String nazivSatelita, String zemljaProizvodnje, double masaKg, int misijaId, int tipId, LocalDateTime vrijemeLansiranja, int raketaId, int mjestoId) throws SQLException {

        // SQL sintaksa za pozivanje pohranjene procedure
        String sql = "{CALL KreirajNovoLansiranje(?, ?, ?, ?, ?, ?, ?, ?)}";

        try (Connection conn = SmtsConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {

            // Postavljanje parametara za satelit
            cstmt.setString(1, nazivSatelita);
            cstmt.setString(2, zemljaProizvodnje);
            cstmt.setDouble(3, masaKg);
            cstmt.setInt(4, misijaId);
            cstmt.setInt(5, tipId);

            // Postavljanje parametara za lansiranje
            cstmt.setObject(6, vrijemeLansiranja);
            cstmt.setInt(7, raketaId);
            cstmt.setInt(8, mjestoId);

            // Izvršavanje procedure
            cstmt.executeUpdate();

            System.out.println("Novo lansiranje i satelit uspješno dodani kroz DAO.");

        } catch (SQLException e) {
            System.err.println("Greška prilikom kreiranja lansiranja: " + e.getMessage());
            e.printStackTrace();
            throw e; // Proslijedi grešku dalje u aplikaciju
        }
    }
}