package controller.itemController;

import javafx.collections.ObservableList;
import model.Item;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ItemService {
    ObservableList<Item> loadDetails(ObservableList<Item> items);

    void addItem(String itemCode, String description, String packSize, double unitPrice, int qtyOnHand);

    void deleteItem(String itemCode);

    void updateItem(String description, String packSize, double unitPrice, int qtyOnHand, String itemCode);

    ResultSet viewItem(String itemCode) throws SQLException;
}
