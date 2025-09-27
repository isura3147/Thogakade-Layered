package repository;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemRepositoryImpl implements ItemRepository {

    @Override
    public ResultSet loadDetails() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String SQL = "SELECT * FROM item;";
        return connection.prepareStatement(SQL).executeQuery();
    }

    @Override
    public void addItem(String itemCode, String description, String packSize, double unitPrice, int qtyOnHand) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String SQL = "INSERT INTO item VALUES(?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1, itemCode);
            preparedStatement.setObject(2, description);
            preparedStatement.setObject(3, packSize);
            preparedStatement.setObject(4, unitPrice);
            preparedStatement.setObject(5, qtyOnHand);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteItem(String itemCode) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String SQL = "DELETE FROM item WHERE ItemCode = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1, itemCode);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateItem(String description, String packSize, double unitPrice, int qtyOnHand, String itemCode) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String SQL = "UPDATE item SET Description = ?, PackSize = ?, UnitPrice = ?, QtyOnHand = ? WHERE ItemCode = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1, description);
            preparedStatement.setObject(2, packSize);
            preparedStatement.setObject(3, unitPrice);
            preparedStatement.setObject(4, qtyOnHand);
            preparedStatement.setObject(5, itemCode);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultSet viewItem(String itemCode) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String SQL = "SELECT * FROM item WHERE ItemCode = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setObject(1, itemCode);
        return preparedStatement.executeQuery();
    }
}
