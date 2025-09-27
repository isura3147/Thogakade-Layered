package controller.ordersController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Orders;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OrdersFormController implements Initializable {

    ObservableList<Orders> ordersInfos = FXCollections.observableArrayList();
    OrdersService ordersService = new OrdersController();
    @FXML
    private TableColumn<?, ?> colCustomerId;
    @FXML
    private TableColumn<?, ?> colOrderDate;
    @FXML
    private TableColumn<?, ?> colOrderId;
    @FXML
    private TableView<Orders> tblOrders;
    @FXML
    private TextField txtCustomerId;
    @FXML
    private DatePicker txtDOB;
    @FXML
    private TextField txtOrderId;
    @FXML
    private TextField txtCustomerName;
    private Stage stage = new Stage();
    private Stage currentStage;


    @FXML
    void btnBackOnAction(ActionEvent event) {
        currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/update_choice_form.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
        currentStage.close();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtOrderId.setText("");
        txtDOB.setValue(null);
        txtCustomerId.setText("");
        txtCustomerName.setText("");
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        ordersService.updateOrder(String.valueOf(txtDOB.getValue()), txtOrderId.getText(), txtCustomerId.getText());
        loadOrdersInfo();
    }

    @FXML
    public void btnDeleteOnAction(ActionEvent event) {
        ordersService.deleteOrder(txtOrderId.getText());
        loadOrdersInfo();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));

        loadOrdersInfo();

        tblOrders.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                txtOrderId.setText(newValue.getOrderId());
                txtDOB.setValue(newValue.getDob());
                txtCustomerId.setText(newValue.getCustomerId());
                txtCustomerName.setText(getCustomerName(txtCustomerId.getText()));
            }
        }));
    }

    private String getCustomerName(String customerId) {
        return ordersService.getCustomerName(customerId);
    }

    private void loadOrdersInfo() {
        ordersInfos.clear();
        tblOrders.setItems(ordersService.loadDetails(ordersInfos));
    }

}
