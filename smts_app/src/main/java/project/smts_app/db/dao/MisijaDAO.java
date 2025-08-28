package project.smts_app.db.dao;

import project.smts_app.util.SmtsConnection;

import project.smts_app.db.obj.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MisijaDAO {

    /**
     * Dohvata detalje o misijama i njihovim partnerima iz pogleda Misije_Detalji_Partnera.
     * @return Lista objekata MisijaPartner
     */
    public List<MisijaPartner> dohvatiMisijeDetaljePartnera() {
        List<MisijaPartner> listaMisija = new ArrayList<>();
        String query = "SELECT * FROM Misije_Detalji_Partnera";

        try (Connection conn = SmtsConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String nazivMisije = rs.getString("naziv_misije");
                String statusMisije = rs.getString("status_misije");
                String zemlja = rs.getString("zemlja");
                String operater = rs.getString("operater");
                String ulogaOperatera = rs.getString("uloga_operatera");

                MisijaPartner misija = new MisijaPartner(nazivMisije, statusMisije, zemlja, operater, ulogaOperatera);
                listaMisija.add(misija);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaMisija;
    }

    public List<MisijaStatus> dohvatiMisijePoStatusu() {
        List<MisijaStatus> listaMisija = new ArrayList<>();
        String query = "SELECT naziv_misije, status FROM Misije_Po_Statusu";

        try (Connection conn = SmtsConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String nazivMisije = rs.getString("naziv_misije");
                String status = rs.getString("status");

                MisijaStatus misija = new MisijaStatus(nazivMisije, status);
                listaMisija.add(misija);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaMisija;
    }

    /**
     * Dohvata sve misije s njihovim ID-evima i nazivima.
     * @return Lista objekata MisijaDetalji
     */
    public List<MisijaDetalji> dohvatiSveMisije() {
        List<MisijaDetalji> listaMisija = new ArrayList<>();
        String query = "SELECT misija_id, naziv FROM Misija";

        try (Connection conn = SmtsConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int misijaId = rs.getInt("misija_id");
                String naziv = rs.getString("naziv");

                listaMisija.add(new MisijaDetalji(misijaId, naziv));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaMisija;
    }

    public MisijaDetalji dohvatiDetaljeMisijePoLansiranjuId(int lansiranjeId) throws SQLException {
        String query = "SELECT m.misija_id, m.naziv, m.datum_pocetka, m.datum_kraja, m.cilj_misije, m.status " +
                "FROM Misija m " +
                "JOIN Lansiranje l ON m.misija_id = l.misija_id " +
                "WHERE l.lansiranje_id = ?";

        try (Connection conn = SmtsConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, lansiranjeId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int misijaId = rs.getInt("misija_id");
                    String naziv = rs.getString("naziv");
                    LocalDate datumPocetka = rs.getDate("datum_pocetka") != null ? rs.getDate("datum_pocetka").toLocalDate() : null;
                    LocalDate datumKraja = rs.getDate("datum_kraja") != null ? rs.getDate("datum_kraja").toLocalDate() : null;
                    String ciljMisije = rs.getString("cilj_misije");
                    String status = rs.getString("status");
                    return new MisijaDetalji(misijaId, naziv, datumPocetka, datumKraja, ciljMisije, status);
                }
            }
        }
        return null;
    }
}