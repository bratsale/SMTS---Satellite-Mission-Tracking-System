package project.smts_app.db.dao;

import project.smts_app.util.SmtsConnection;
import project.smts_app.db.obj.ZemaljskaStanica;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ZemaljskaStanicaDAO {

    public List<ZemaljskaStanica> dohvatiSveStanice() throws SQLException {
        List<ZemaljskaStanica> stanice = new ArrayList<>();
        String query = "SELECT * FROM Zemaljska_Stanica";

        try (Connection conn = SmtsConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                ZemaljskaStanica stanica = new ZemaljskaStanica(
                        rs.getInt("stanica_id"),
                        rs.getString("naziv"),
                        rs.getString("lokacija"),
                        rs.getString("zemlja_stanice")
                );
                stanice.add(stanica);
            }
        }
        return stanice;
    }
}