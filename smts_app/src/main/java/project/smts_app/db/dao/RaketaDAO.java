// project.smts_app.db.dao/RaketaDAO.java
package project.smts_app.db.dao;

import project.smts_app.db.obj.MisijaDetalji;
import project.smts_app.db.obj.RaketaDetalji;
import project.smts_app.util.SmtsConnection;
import project.smts_app.db.obj.RaketaNosac;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RaketaDAO {

    public List<RaketaNosac> dohvatiSveRakete() throws SQLException {
        List<RaketaNosac> listaRaketa = new ArrayList<>();
        String query = "SELECT raketa_id, naziv FROM Raketa_Nosac";
        try (Connection conn = SmtsConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int raketaId = rs.getInt("raketa_id");
                String naziv = rs.getString("naziv");
                listaRaketa.add(new RaketaNosac(raketaId, naziv));
            }
        }
        return listaRaketa;
    }

    public RaketaDetalji dohvatiDetaljeRaketePoLansiranjuId(int lansiranjeId) throws SQLException {
        String query = "SELECT rn.raketa_id, rn.naziv, rn.proizvodjac, rn.kapacitet_tovar_kg " +
                "FROM Raketa_Nosac rn " +
                "JOIN Lansiranje l ON rn.raketa_id = l.raketa_id " +
                "WHERE l.lansiranje_id = ?";

        try (Connection conn = SmtsConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, lansiranjeId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int raketaId = rs.getInt("raketa_id");
                    String naziv = rs.getString("naziv");
                    String proizvodjac = rs.getString("proizvodjac");
                    int kapacitet = rs.getInt("kapacitet_tovar_kg");
                    return new RaketaDetalji(raketaId, naziv, proizvodjac, kapacitet);
                }
            }
        }
        return null;
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