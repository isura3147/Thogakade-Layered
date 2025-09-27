package service;

import db.DBConnection;
import javafx.collections.ObservableList;
import model.Item;
import repository.ItemRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemServiceImpl implements ItemService {

    ItemRepository itemRepository = new ItemRepository();
    @Override
    public ObservableList<Item> loadDetails(ObservableList<Item> itemInfos) {
        try {
            ResultSet resultSet = itemRepository.loadDetails();

            while (resultSet.next()) {
                Item itemInfo = new Item(
                        resultSet.getString("ItemCode"),
                        resultSet.getString("Description"),
                        resultSet.getString("PackSize"),
                        resultSet.getDouble("UnitPrice"),
                        resultSet.getInt("QtyOnHand")
                );
                itemInfos.add(itemInfo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itemInfos;
    }

    @Override
    public void addItem(String itemCode, String description, String packSize, double unitPrice, int qtyOnHand) {
        itemRepository.addItem(itemCode, description, packSize, unitPrice, qtyOnHand);
    }

    @Override
    public void deleteItem(String itemCode) {
        itemRepository.deleteItem(itemCode);
    }

    @Override
    public void updateItem(String description, String packSize, double unitPrice, int qtyOnHand, String itemCode) {
        itemRepository.updateItem(description, packSize, unitPrice, qtyOnHand, itemCode);
    }

    @Override
    public ResultSet viewItem(String itemCode) throws SQLException {
        return itemRepository.viewItem(itemCode);
    }
}
