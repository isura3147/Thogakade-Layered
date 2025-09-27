package repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface LoginRepository {

    ResultSet getUsers() throws SQLException;
}
