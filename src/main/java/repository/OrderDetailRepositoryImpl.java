package repository;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailRepositoryImpl implements OrderDetailRepository {

    @Override
    public ResultSet loadDetails() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String SQL = "SELECT * FROM orderdetail;";
        return connection.prepareStatement(SQL).executeQuery();
    }

    @Override
    public ResultSet getDescription(String itemCode) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String SQL = "SELECT Description FROM item WHERE ItemCode = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setObject(1, itemCode);
        return preparedStatement.executeQuery();
    }

    @Override
    public void updateOrderDetails(String orderQty, String discount, String orderId, String itemCode) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String SQL = "UPDATE orderdetail SET OrderQTY = ?, Discount = ? WHERE OrderID = ? AND ItemCode = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1, orderQty);
            preparedStatement.setObject(2, discount);
            preparedStatement.setObject(3, orderId);
            preparedStatement.setObject(4, itemCode);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
