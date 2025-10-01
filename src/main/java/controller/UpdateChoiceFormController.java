package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UpdateChoiceFormController {

    private final Stage stage = new Stage();
    private Stage currentStage;

    @FXML
    void btnBackOnAction(ActionEvent event) {
        nextStage(event, "/view/login_form.fxml");
    }

    @FXML
    void btnUpdateCustomerOnAction(ActionEvent event) {
        nextStage(event, "/view/update_customer_form.fxml");
    }

    @FXML
    void btnUpdateItemOnAction(ActionEvent event) {
        nextStage(event, "/view/update_item_form.fxml");
    }

    @FXML
    void btnUpdateOrderDetailOnAction(ActionEvent event) {
        nextStage(event, "/view/order_detail_form.fxml");
    }

    @FXML
    void btnUpdateOrdersOnAction(ActionEvent event) {
        nextStage(event, "/view/orders_form.fxml");
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
