package repository;

import db.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRepository {
    public ResultSet getUsers() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String SQL = "SELECT * FROM admin_info;";
        return connection.prepareStatement(SQL).executeQuery();
    }
}
