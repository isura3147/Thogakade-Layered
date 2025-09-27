package controller.ordersController;

import db.DBConnection;
import javafx.collections.ObservableList;
import model.Orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdersController implements OrdersService {
    @Override
    public ObservableList<Orders> loadDetails(ObservableList<Orders> ordersInfos) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String SQL = "SELECT * FROM orders;";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Orders orders = new Orders(
                        resultSet.getString("OrderID"),
                        resultSet.getDate("OrderDate").toLocalDate(),
                        resultSet.getString("CustID")
                );
                ordersInfos.add(orders);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ordersInfos;
    }

    @Override
    public void updateOrder(String DOB, String orderId, String customerId) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String SQL = "UPDATE orders SET OrderDate = ? WHERE OrderID = ? AND CustID = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1, DOB);
            preparedStatement.setObject(2, orderId);
            preparedStatement.setObject(3, customerId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getCustomerName(String customerId) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String SQL = "SELECT CustName FROM customer WHERE CustID = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("CustName");
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteOrder(String orderId) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String SQL = "DELETE FROM orders WHERE OrderID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1, orderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
