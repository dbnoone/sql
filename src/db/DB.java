package db;

import structures.ColumnRowStructure;

import java.sql.*;

public class DB {

    private static final String host = "127.0.0.1";
    private static final String port = "3306";
    private static final String db_name = "test";
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
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void load(String table, ColumnRowStructure[] filters) {
        if (connect()) {
            try {
                StringBuilder query_string = new StringBuilder("SELECT * FROM " + table + " WHERE true");
                if (filters.length != 0) {
                    for (ColumnRowStructure filter : filters) {
                        query_string.append(" AND ").append(filter.get_key()).append(" = ");
                        query_string.append("'").append(filter.get_value()).append("'");
                    }
                }
                rs = stmt.executeQuery(query_string.toString());
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void load(String table) {
        if (connect()) {
            try {
                rs = stmt.executeQuery("SELECT * FROM " + table);
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void create(String table, ColumnRowStructure[] new_data) {
        if (connect()) {
            try {
                StringBuilder query_string = new StringBuilder("INSERT INTO " + table + " (");
                for (ColumnRowStructure data_piece : new_data) {
                    query_string.append(data_piece.get_key()).append(",");
                }
                query_string.deleteCharAt(-1);
                query_string.append(") VALUES (");
                for (ColumnRowStructure data_piece : new_data) {
                    query_string.append("'").append(data_piece.get_value()).append("'").append(",");
                }
                query_string.deleteCharAt(-1);
                query_string.append(")");
                rs = stmt.executeQuery(query_string.toString());
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void delete(String table, ColumnRowStructure[] filters) {
        if (connect()) {
            try {
                StringBuilder query_string = new StringBuilder("DELETE FROM " + table + " WHERE true");
                for (ColumnRowStructure filter : filters) {
                    query_string.append(" AND ").append(filter.get_key()).append(" = ").append("'").append(filter.get_value()).append("'");
                }
                rs = stmt.executeQuery(query_string.toString());
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void delete(String table) {
        if (connect()) {
            try {
                rs = stmt.executeQuery("DELETE FROM " + table);
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
