package project.smts_app.db.dao;

import project.smts_app.util.SmtsConnection;
import project.smts_app.db.obj.KomunikacijaStanicaSatelit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class KomunikacijaDAO {

    public List<KomunikacijaStanicaSatelit> dohvatiDetaljeKomunikacije() {
        List<KomunikacijaStanicaSatelit> listaKomunikacija = new ArrayList<>();
        String query = "SELECT * FROM Komunikacija_Stanica_Satelit ORDER BY datum_komunikacije DESC, komunikacija_id DESC";

        try (Connection conn = SmtsConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String nazivStanice = rs.getString("naziv_stanice");
                String lokacijaStanice = rs.getString("lokacija_stanice");
                String nazivSatelita = rs.getString("naziv_satelita");
                LocalDate datumKomunikacije = rs.getDate("datum_komunikacije").toLocalDate();
                String tipKomunikacije = rs.getString("tip_komunikacije");
                String sadrzajPoruke = rs.getString("sadrzaj_poruke");

                KomunikacijaStanicaSatelit komunikacija = new KomunikacijaStanicaSatelit(nazivStanice, lokacijaStanice, nazivSatelita, datumKomunikacije, tipKomunikacije, sadrzajPoruke);
                listaKomunikacija.add(komunikacija);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaKomunikacija;
    }

    public static void sacuvajNovuPoruku(int satelitId, int stanicaId, LocalDate datum, String tip, String sadrzaj) throws SQLException {
        String sql = "INSERT INTO Komunikacija (satelit_id, stanica_id, datum_komunikacije, tip_komunikacije, sadrzaj_poruke) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = SmtsConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, satelitId);
            pstmt.setInt(2, stanicaId);
            pstmt.setDate(3, java.sql.Date.valueOf(datum));
            pstmt.setString(4, tip);
            pstmt.setString(5, sadrzaj);

            pstmt.executeUpdate();
            System.out.println("Nova poruka uspješno spremljena.");
        }
    }
}
