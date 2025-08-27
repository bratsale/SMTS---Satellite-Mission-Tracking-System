// project.smts_app.db.dao/SatelitDAO.java
package project.smts_app.db.dao;

import project.smts_app.db.obj.SatelitDetalji;
import project.smts_app.util.SmtsConnection;
import project.smts_app.db.obj.Satelit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SatelitDAO {

    public List<Satelit.Tip> dohvatiSveTipoveSatelita() throws SQLException {
        List<Satelit.Tip> listaTipova = new ArrayList<>();
        String query = "SELECT tip_id, naziv_tipa FROM Tip_Satelita";
        try (Connection conn = SmtsConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int tipId = rs.getInt("tip_id");
                String naziv = rs.getString("naziv_tipa");
                listaTipova.add(new Satelit.Tip(tipId, naziv));
            }
        }
        return listaTipova;
    }

    public SatelitDetalji dohvatiDetaljeSatelitaPoLansiranjuId(int lansiranjeId) throws SQLException {
        String query = "SELECT s.satelit_id, s.naziv, s.zemlja_proizvodnje, s.masa_kg, m.naziv AS misija_naziv, ts.naziv_tipa AS tip_naziv " +
                "FROM Satelit s " +
                "JOIN Lansiranje l ON s.satelit_id = l.satelit_id " +
                "JOIN Misija m ON s.misija_id = m.misija_id " +
                "JOIN Tip_Satelita ts ON s.tip_id = ts.tip_id " +
                "WHERE l.lansiranje_id = ?";

        try (Connection conn = SmtsConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, lansiranjeId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int satelitId = rs.getInt("satelit_id");
                    String naziv = rs.getString("naziv");
                    String zemlja = rs.getString("zemlja_proizvodnje");
                    double masa = rs.getDouble("masa_kg");
                    String nazivMisije = rs.getString("misija_naziv");
                    String nazivTipa = rs.getString("tip_naziv");
                    return new SatelitDetalji(satelitId, naziv, zemlja, masa, nazivMisije, nazivTipa);
                }
            }
        }
        return null;
    }
}