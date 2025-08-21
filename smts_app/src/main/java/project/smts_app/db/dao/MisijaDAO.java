// project.smts_app.db.dao/MisijaDAO.java
package project.smts_app.db.dao;

import project.smts_app.util.SmtsConnection;
import project.smts_app.db.obj.MisijaPartner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}