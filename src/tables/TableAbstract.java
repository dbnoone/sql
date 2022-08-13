package tables;

import db.DB;
import structures.ColumnRowStructure;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class TableAbstract {


    public ResultSet load(String table_name, ColumnRowStructure[] filters) {
        DB.load(table_name, filters);
        return DB.rs;
    }

    public ResultSet load(String table_name) {
        DB.load(table_name);
        return DB.rs;
    }

    public ResultSet create(String table_name, ColumnRowStructure[] new_data) {
        DB.create(table_name, new_data);
        return DB.rs;
    }

    public Boolean delete(String table_name, ColumnRowStructure[] filters) throws SQLException {
        DB.delete(table_name, filters);
        return DB.rs.getBoolean(1);
    }

    public Boolean delete(String table_name) throws SQLException {
        DB.delete(table_name);
        return DB.rs.getBoolean(1);
    }
}
