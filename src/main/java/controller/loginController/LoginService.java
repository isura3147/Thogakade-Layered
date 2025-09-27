package controller.loginController;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface LoginService {
    ResultSet getUsers() throws SQLException;
}
