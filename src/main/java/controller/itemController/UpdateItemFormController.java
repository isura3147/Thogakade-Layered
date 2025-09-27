package controller.itemController;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class UpdateItemFormController {

    @FXML
    public JFXButton btnViewAllItems;
    @FXML
    public TextField txtUnitPrice;
    ItemService itemService = new ItemController();
    @FXML
    private TextArea txtDescription;
    @FXML
    private TextField txtItemCode;
    @FXML
    private TextField txtPackSize;
    @FXML
    private TextField txtQtyOnHand;
    private Stage stage = new Stage();
    private Stage currentStage;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        itemService.addItem(txtItemCode.getText(), txtDescription.getText(), txtPackSize.getText(),
                Double.parseDouble(txtUnitPrice.getText()), Integer.parseInt(txtQtyOnHand.getText()));
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        itemService.deleteItem(txtItemCode.getText());
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        itemService.updateItem(txtDescription.getText(), txtPackSize.getText(), Double.parseDouble(txtUnitPrice.getText()),
                Integer.parseInt(txtQtyOnHand.getText()), txtItemCode.getText());
    }

    @FXML
    void btnViewOnAction(ActionEvent event) throws SQLException {
        ResultSet resultSet = itemService.viewItem(txtItemCode.getText());
        try {
            while (resultSet.next()) {
                txtDescription.setText(resultSet.getString("Description"));
                txtPackSize.setText(resultSet.getString("PackSize"));
                txtUnitPrice.setText(String.valueOf(resultSet.getDouble("UnitPrice")));
                txtQtyOnHand.setText(String.valueOf(resultSet.getInt("QtyOnHand")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnBackOnAction(ActionEvent event) {
        nextStage(event, "/view/update_choice_form.fxml");
    }

    @FXML
    public void btnViewAllItemsOnAction(ActionEvent event) {
        nextStage(event, "/view/all_items_table.fxml");
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
