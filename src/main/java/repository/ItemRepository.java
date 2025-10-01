package repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ItemRepository {
    ResultSet loadDetails() throws SQLException;

    void addItem(
            String itemCode, String description, String packSize, double unitPrice, int qtyOnHand);

    void deleteItem(String itemCode);

    void updateItem(
            String description, String packSize, double unitPrice, int qtyOnHand, String itemCode);

    ResultSet viewItem(String itemCode) throws SQLException;
}
