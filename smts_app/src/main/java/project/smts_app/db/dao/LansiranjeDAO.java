package project.smts_app.db.dao;

import project.smts_app.db.obj.*;
import project.smts_app.util.SmtsConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LansiranjeDAO {

    public void kreirajNovoLansiranje(String nazivSatelita, String zemljaProizvodnje, double masaKg, int misijaId, int tipId, LocalDateTime vrijemeLansiranja, int raketaId, int mjestoId) throws SQLException {

        String sql = "{CALL KreirajNovoLansiranje(?, ?, ?, ?, ?, ?, ?, ?)}";

        try (Connection conn = SmtsConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setString(1, nazivSatelita);
            cstmt.setString(2, zemljaProizvodnje);
            cstmt.setDouble(3, masaKg);
            cstmt.setInt(4, misijaId);
            cstmt.setInt(5, tipId);

            cstmt.setObject(6, vrijemeLansiranja);
            cstmt.setInt(7, raketaId);
            cstmt.setInt(8, mjestoId);

            cstmt.executeUpdate();

            System.out.println("Novo lansiranje i satelit uspješno dodani kroz DAO.");

        } catch (SQLException e) {
            System.err.println("Greška prilikom kreiranja lansiranja: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public List<DetaljiLansiranja> dohvatiSveDetaljeLansiranja() {
        List<DetaljiLansiranja> listaLansiranja = new ArrayList<>();
        String query = "SELECT l.lansiranje_id, " +  // Dodaj ID lansiranja
                "s.naziv AS satelit_naziv, " +
                "l.vrijeme_lansiranja, " +
                "r.naziv AS raketa_naziv, " +
                "m.naziv AS misija_naziv, " +
                "ml.naziv AS mjesto_naziv " +
                "FROM Lansiranje l " +
                "JOIN Satelit s ON l.satelit_id = s.satelit_id " +
                "JOIN Raketa_Nosac r ON l.raketa_id = r.raketa_id " +
                "JOIN Misija m ON l.misija_id = m.misija_id " +
                "JOIN Mjesto_Lansiranja ml ON l.mjesto_id = ml.mjesto_id";

        try (Connection conn = SmtsConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int lansiranjeId = rs.getInt("lansiranje_id"); // Dohvati ID
                String satelit = rs.getString("satelit_naziv");
                LocalDateTime vrijemeLansiranja = rs.getTimestamp("vrijeme_lansiranja").toLocalDateTime();
                String raketaNosac = rs.getString("raketa_naziv");
                String misija = rs.getString("misija_naziv");
                String mjestoLansiranja = rs.getString("mjesto_naziv");

                DetaljiLansiranja detalji = new DetaljiLansiranja(satelit, vrijemeLansiranja, raketaNosac, misija, mjestoLansiranja, lansiranjeId);
                listaLansiranja.add(detalji);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaLansiranja;
    }

    /**
     * Brise lansiranje iz baze podataka na osnovu ID-ja.
     * Takodje brise i povezani satelit.
     * @param lansiranjeId ID lansiranja koje treba obrisati
     */
    public void obrisiLansiranje(int lansiranjeId) throws SQLException {
        String getSatelitIdQuery = "SELECT satelit_id FROM Lansiranje WHERE lansiranje_id = ?";
        String deleteLansiranjeQuery = "DELETE FROM Lansiranje WHERE lansiranje_id = ?";
        String deleteSatelitQuery = "DELETE FROM Satelit WHERE satelit_id = ?";

        int satelitId = -1;

        try (Connection conn = SmtsConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement ps = conn.prepareStatement(getSatelitIdQuery)) {
                ps.setInt(1, lansiranjeId);
                var rs = ps.executeQuery();
                if (rs.next()) {
                    satelitId = rs.getInt("satelit_id");
                }
            }

            if (satelitId != -1) {
                try (PreparedStatement ps = conn.prepareStatement(deleteLansiranjeQuery)) {
                    ps.setInt(1, lansiranjeId);
                    ps.executeUpdate();
                }

                try (PreparedStatement ps = conn.prepareStatement(deleteSatelitQuery)) {
                    ps.setInt(1, satelitId);
                    ps.executeUpdate();
                }

                // Ako je sve uspjesno, commit-uj transakciju
                conn.commit();
                System.out.println("Lansiranje i povezani satelit su uspješno obrisani.");
            } else {
                conn.rollback();
                System.out.println("Lansiranje sa zadanim ID-jem nije pronađeno.");
            }

        } catch (SQLException e) {
            System.err.println("Greška prilikom brisanja lansiranja: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
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
                LocalDateTime vrijemeLansiranja = rs.getTimestamp("vrijeme_lansiranja").toLocalDateTime();
                String raketaNosac = rs.getString("raketa_nosac");
                String mjestoLansiranja = rs.getString("mjesto_lansiranja");

                SatelitDetalji satelit = new SatelitDetalji(satelitId, nazivSatelita, zemljaProizvodnje, masaKg, tipSatelita, nazivMisije, vrijemeLansiranja, raketaNosac, mjestoLansiranja);
                listaSatelita.add(satelit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaSatelita;
    }

    public List<LansiranjeProizvodjac> dohvatiLansiranjaPoProizvodjacu() {
        List<LansiranjeProizvodjac> listaLansiranja = new ArrayList<>();
        String query = "SELECT * FROM Lansiranja_Po_Proizvodjacu";

        try (Connection conn = SmtsConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String proizvodjac = rs.getString("proizvodjac");
                String raketaNosac = rs.getString("raketa_nosac");
                int brojLansiranja = rs.getInt("broj_lansiranja");

                LansiranjeProizvodjac lansiranje = new LansiranjeProizvodjac(proizvodjac, raketaNosac, brojLansiranja);
                listaLansiranja.add(lansiranje);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaLansiranja;
    }
}