package controller.itemController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Item;
import service.ItemService;
import service.ItemServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateItemFormController implements Initializable {

    private final Stage stage = new Stage();
    @FXML
    public TextField txtUnitPrice;
    ItemService itemService = new ItemServiceImpl();
    ObservableList<Item> itemInfos = FXCollections.observableArrayList();
    @FXML
    private TextArea txtDescription;
    @FXML
    private TextField txtItemCode;
    @FXML
    private TextField txtPackSize;
    @FXML
    private TextField txtQtyOnHand;
    @FXML
    private TableView<Item> tblItems;
    @FXML
    private TableColumn<?, ?> colDescription;
    @FXML
    private TableColumn<?, ?> colItemCode;
    @FXML
    private TableColumn<?, ?> colPackSize;
    @FXML
    private TableColumn<?, ?> colUnitPrice;
    @FXML
    private TableColumn<?, ?> colQtyOnHand;
    private Stage currentStage;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        itemService.addItem(
                txtItemCode.getText(),
                txtDescription.getText(),
                txtPackSize.getText(),
                Double.parseDouble(txtUnitPrice.getText()),
                Integer.parseInt(txtQtyOnHand.getText()));

        loadItemInfo();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        itemService.deleteItem(txtItemCode.getText());
        loadItemInfo();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        itemService.updateItem(
                txtDescription.getText(),
                txtPackSize.getText(),
                Double.parseDouble(txtUnitPrice.getText()),
                Integer.parseInt(txtQtyOnHand.getText()),
                txtItemCode.getText());

        loadItemInfo();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

        loadItemInfo();

        tblItems.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                txtItemCode.setText(newValue.getItemCode());
                txtDescription.setText(newValue.getDescription());
                txtPackSize.setText(newValue.getPackSize());
                txtUnitPrice.setText(String.valueOf(newValue.getUnitPrice()));
                txtQtyOnHand.setText(String.valueOf(newValue.getQtyOnHand()));
            }
        }));
    }

    private void loadItemInfo() {
        itemInfos.clear();
        tblItems.setItems(itemService.loadDetails(itemInfos));
    }
}
