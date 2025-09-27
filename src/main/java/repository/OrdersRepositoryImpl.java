package repository;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdersRepositoryImpl implements OrdersRepository{

    @Override
    public ResultSet loadDetails() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String SQL = "SELECT * FROM orders;";
        return connection.prepareStatement(SQL).executeQuery();
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
