package project.smts_app.db.dao;

import project.smts_app.db.obj.SatelitDetalji;
import project.smts_app.db.obj.SatelitOrbita;
import project.smts_app.util.SmtsConnection;
import project.smts_app.db.obj.Satelit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SatelitDAO {

    /**
     * Dohvaća sve satelite iz baze podataka.
     * @return Lista svih satelita.
     * @throws SQLException ako dođe do greške s bazom podataka.
     */
    public List<Satelit> dohvatiSveSatelite() throws SQLException {
        List<Satelit> sateliti = new ArrayList<>();
        String query = "SELECT satelit_id, naziv, zemlja_proizvodnje, masa_kg, misija_id, tip_id FROM Satelit";

        try (Connection conn = SmtsConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Satelit satelit = new Satelit(
                        rs.getInt("satelit_id"),
                        rs.getString("naziv"),
                        rs.getString("zemlja_proizvodnje"),
                        rs.getDouble("masa_kg"),
                        rs.getInt("misija_id"),
                        rs.getInt("tip_id")
                );
                sateliti.add(satelit);
            }
        }
        return sateliti;
    }

    public Map<String, Long> dohvatiBrojSatelitaPoTipu() throws SQLException {
        String query = "SELECT ts.naziv_tipa, COUNT(s.satelit_id) as broj FROM Satelit s JOIN Tip_Satelita ts ON s.tip_id = ts.tip_id GROUP BY ts.naziv_tipa";
        try (Connection conn = SmtsConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            Map<String, Long> rezultat = new java.util.HashMap<>();
            while (rs.next()) {
                rezultat.put(rs.getString("naziv_tipa"), rs.getLong("broj"));
            }
            return rezultat;
        }
    }

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
        String query = "SELECT s.satelit_id, s.naziv, s.zemlja_proizvodnje, s.masa_kg, " +
                "m.naziv AS misija_naziv, ts.naziv_tipa AS tip_naziv, " +
                "o.visina_km, o.inklinacija_stepeni " +
                "FROM Satelit s " +
                "JOIN Lansiranje l ON s.satelit_id = l.satelit_id " +
                "JOIN Misija m ON s.misija_id = m.misija_id " +
                "JOIN Tip_Satelita ts ON s.tip_id = ts.tip_id " +
                "LEFT JOIN Orbita o ON s.satelit_id = o.satelit_id " +
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
                    double visina = rs.getDouble("visina_km");
                    double inklinacija = rs.getDouble("inklinacija_stepeni");

                    return new SatelitDetalji(satelitId, naziv, zemlja, masa, nazivTipa, nazivMisije, visina, inklinacija);
                }
            }
        }
        return null;
    }

    public List<SatelitDetalji> dohvatiSveSateliteDetalje() {
        List<SatelitDetalji> listaSatelita = new ArrayList<>();
        String query = "SELECT * FROM Svi_Sateliti_Detalji";

        try (Connection conn = SmtsConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int satelitId = rs.getInt("satelit_id");
                String nazivSatelita = rs.getString("naziv_satelita");
                String zemljaProizvodnje = rs.getString("zemlja_proizvodnje");
                double masaKg = rs.getDouble("masa_kg");
                String tipSatelita = rs.getString("tip_satelita");
                String nazivMisije = rs.getString("naziv_misije");

                SatelitDetalji satelit = new SatelitDetalji(satelitId, nazivSatelita, zemljaProizvodnje, masaKg, tipSatelita, nazivMisije);
                listaSatelita.add(satelit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaSatelita;
    }

    public List<SatelitOrbita> dohvatiSveSateliteIOrbite() {
        List<SatelitOrbita> listaSatelita = new ArrayList<>();
        String query = "SELECT * FROM Sateliti_i_Orbite";

        try (Connection conn = SmtsConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String nazivSatelita = rs.getString("naziv_satelita");
                String tipSatelita = rs.getString("tip_satelita");
                String nazivMisije = rs.getString("naziv_misije");
                double visinaOrbiteKm = rs.getDouble("visina_orbite_km");
                double inklinacijaOrbite = rs.getDouble("inklinacija_orbite");

                SatelitOrbita satelit = new SatelitOrbita(nazivSatelita, tipSatelita, nazivMisije, visinaOrbiteKm, inklinacijaOrbite);
                listaSatelita.add(satelit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaSatelita;
    }
}
