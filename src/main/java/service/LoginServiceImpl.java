package service;

import repository.LoginRepository;
import repository.LoginRepositoryImpl;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginServiceImpl implements LoginService {

    LoginRepository loginRepository = new LoginRepositoryImpl();

    @Override
    public ResultSet getUsers() throws SQLException {
        return loginRepository.getUsers();
    }
}
