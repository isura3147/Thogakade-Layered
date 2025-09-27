package controller.orderdetailController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.OrderDetail;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OrderDetailFormController implements Initializable {

    ObservableList<OrderDetail> orderDetailInfos = FXCollections.observableArrayList();
    OrderDetailService orderDetailService = new OrderDetailController();
    @FXML
    private TableColumn<?, ?> colDiscount;
    @FXML
    private TableColumn<?, ?> colItemCode;
    @FXML
    private TableColumn<?, ?> colOrderId;
    @FXML
    private TableColumn<?, ?> colOrderQty;
    @FXML
    private TableView<OrderDetail> tblOrderDetail;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtDiscount;
    @FXML
    private TextField txtItemCode;
    @FXML
    private TextField txtOrderId;
    @FXML
    private TextField txtOrderQty;
    private Stage stage = new Stage();
    private Stage currentStage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colOrderQty.setCellValueFactory(new PropertyValueFactory<>("orderQty"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));

        loadOrderDetailInfo();

        tblOrderDetail.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                txtOrderId.setText(newValue.getOrderId());
                txtItemCode.setText(newValue.getItemCode());
                txtDescription.setText(getDescription(txtItemCode.getText()));
                txtOrderQty.setText(String.valueOf(newValue.getOrderQty()));
                txtDiscount.setText(String.valueOf(newValue.getDiscount()));
            }
        }));
    }

    private String getDescription(String itemCode) {
        return orderDetailService.getDescription(itemCode);
    }

    private void loadOrderDetailInfo() {
        orderDetailInfos.clear();
        tblOrderDetail.setItems(orderDetailService.loadDetails(orderDetailInfos));
    }

    @FXML
    public void btnClearOnAction(ActionEvent event) {
        txtItemCode.setText("");
        txtDescription.setText("");
        txtDiscount.setText("");
        txtOrderId.setText("");
        txtOrderQty.setText("");
    }

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
    void btnUpdateOnAction(ActionEvent event) {
        orderDetailService.updateOrderDetail(txtOrderQty.getText(), txtDiscount.getText(), txtOrderId.getText(), txtItemCode.getText());
        loadOrderDetailInfo();
    }

}
