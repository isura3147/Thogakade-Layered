package service;

import repository.LoginRepository;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginServiceImpl implements LoginService {

    LoginRepository loginRepository = new LoginRepository();

    @Override
    public ResultSet getUsers() throws SQLException {
        return loginRepository.getUsers();
    }
}
