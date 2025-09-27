package controller.customerController;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class UpdateCustomerFormController {

    @FXML
    public JFXButton btnViewAllCustomers;
    @FXML
    public TextField txtPostalCode;
    CustomerService customerService = new CustomerController();
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtCity;
    @FXML
    private DatePicker txtDOB;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtProvince;
    @FXML
    private TextField txtSalary;
    @FXML
    private TextField txtTitle;

    private Stage stage = new Stage();
    private Stage currentStage;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        customerService.addCustomer(txtId.getText(), txtTitle.getText(), txtName.getText(), String.valueOf(txtDOB.getValue()),
                Double.parseDouble(txtSalary.getText()), txtAddress.getText(), txtCity.getText(),
                txtProvince.getText(), txtPostalCode.getText());
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        customerService.deleteCustomer(txtId.getText());
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        customerService.updateCustomer(txtId.getText(), txtTitle.getText(), txtName.getText(), String.valueOf(txtDOB.getValue()),
                Double.parseDouble(txtSalary.getText()), txtAddress.getText(), txtCity.getText(),
                txtProvince.getText(), txtPostalCode.getText());
    }

    @FXML
    void btnViewOnAction(ActionEvent event) throws SQLException {

        ResultSet resultSet = customerService.viewCustomer(txtId.getText());

        while (resultSet.next()) {
            txtTitle.setText(resultSet.getString("CustTitle"));
            txtName.setText(resultSet.getString("CustName"));
            txtDOB.setValue(resultSet.getDate("DOB").toLocalDate());
            txtSalary.setText(String.valueOf(resultSet.getDouble("salary")));
            txtAddress.setText(resultSet.getString("CustAddress"));
            txtCity.setText(resultSet.getString("City"));
            txtProvince.setText(resultSet.getString("Province"));
            txtPostalCode.setText(resultSet.getString("PostalCode"));
        }
    }

    @FXML
    void btnBackOnAction(ActionEvent event) {
        nextStage(event, "/view/update_choice_form.fxml");
    }

    @FXML
    public void btnViewAllCustomersOnAction(ActionEvent event) {
        nextStage(event, "/view/all_customers_table.fxml");
    }

    private void nextStage(ActionEvent event, String path) {
        currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(path))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
        currentStage.close();
    }
}
