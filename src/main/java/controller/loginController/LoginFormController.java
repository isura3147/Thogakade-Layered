package controller.loginController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LoginFormController {

    LoginService loginService = new LoginController();
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtUsername;
    private Stage stage = new Stage();

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        boolean loginSuccess = false;

        try {
            ResultSet resultSet = loginService.getUsers();

            while (resultSet.next()) {
                if (txtUsername.getText().equals(resultSet.getString("name")) && txtPassword.getText().equals(resultSet.getString("password"))) {
                    try {
                        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/update_choice_form.fxml"))));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    stage.show();
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.close();
                    loginSuccess = true;
                    break;
                }
            }
            if (!loginSuccess) {
                try {
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/notification_ui.fxml"))));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
