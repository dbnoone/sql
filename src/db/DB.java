package db;

import java.sql.*;

public class DB {

    private static final String host = "127.0.0.1";
    private static final String port = "3306";
    private static final String db_name = "minions_villains";
    private static final String user = "root";
    private static final String pass = "";

    private static Connection con;
    private static Statement stmt;
    public static ResultSet rs;

    private static boolean connect() {
        boolean result = false;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + db_name, user, pass);
            stmt = con.createStatement();
            con.setAutoCommit(false);
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void query(String query_string) {
        if (connect()) {
            try {
                rs = stmt.executeQuery(query_string);
                con.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void insert(String query_string) {
        if (connect()) {
            try {
                stmt.executeUpdate(query_string);
                con.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void delete(String query_string) {
        if (connect()) {
            try {
                stmt.executeUpdate(query_string);
                con.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
