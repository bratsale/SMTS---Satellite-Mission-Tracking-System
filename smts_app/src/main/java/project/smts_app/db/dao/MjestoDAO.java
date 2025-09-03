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

public class MjestoDAO {

    public List<MjestoLansiranja> dohvatiSvaMjestaLansiranja() throws SQLException {
        List<MjestoLansiranja> listaMjesta = new ArrayList<>();
        String query = "SELECT mjesto_id, naziv FROM Mjesto_Lansiranja";
        try (Connection conn = SmtsConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int mjestoId = rs.getInt("mjesto_id");
                String naziv = rs.getString("naziv");
                listaMjesta.add(new MjestoLansiranja(mjestoId, naziv));
            }
        }
        return listaMjesta;
    }
}