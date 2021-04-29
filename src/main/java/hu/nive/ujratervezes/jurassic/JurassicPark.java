package hu.nive.ujratervezes.jurassic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JurassicPark {

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public JurassicPark(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public Connection getConnection() {
        Connection connect = null;
        try {
            connect = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connect;
    }

    public List<String> checkOverpopulation(){

        List<String> dinosResult = new ArrayList<>();
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT breed " +
                            "FROM dinosaur " +
                            "WHERE actual > expected " +
                            "ORDER BY breed ASC");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                dinosResult.add(rs.getString("breed"));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return dinosResult;
    }
}
