// project.smts_app.db.dao/KomunikacijaDAO.java
package project.smts_app.db.dao;

import project.smts_app.util.SmtsConnection;
import project.smts_app.db.obj.KomunikacijaStanicaSatelit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class KomunikacijaDAO {

    /**
     * Dohvata detalje komunikacije sa zemaljskim stanicama iz pogleda Komunikacija_Stanica_Satelit.
     * @return Lista objekata KomunikacijaStanicaSatelit
     */
    public List<KomunikacijaStanicaSatelit> dohvatiDetaljeKomunikacije() {
        List<KomunikacijaStanicaSatelit> listaKomunikacija = new ArrayList<>();
        String query = "SELECT * FROM Komunikacija_Stanica_Satelit";

        try (Connection conn = SmtsConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String nazivStanice = rs.getString("naziv_stanice");
                String lokacijaStanice = rs.getString("lokacija_stanice");
                String nazivSatelita = rs.getString("naziv_satelita");
                LocalDateTime datumKomunikacije = rs.getTimestamp("datum_komunikacije").toLocalDateTime();
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
}