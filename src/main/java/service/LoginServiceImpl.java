package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import repository.LoginRepository;
import repository.LoginRepositoryImpl;

public class LoginServiceImpl implements LoginService {

  LoginRepository loginRepository = new LoginRepositoryImpl();

  @Override
  public ResultSet getUsers() throws SQLException {
    return loginRepository.getUsers();
  }
}
