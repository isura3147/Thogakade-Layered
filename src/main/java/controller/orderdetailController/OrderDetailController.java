package controller.orderdetailController;

import db.DBConnection;
import javafx.collections.ObservableList;
import model.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailController implements OrderDetailService {
    @Override
    public ObservableList<OrderDetail> loadDetails(ObservableList<OrderDetail> orderDetailInfos) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String SQL = "SELECT * FROM orderdetail;";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                OrderDetail orderDetail = new OrderDetail(
                        resultSet.getString("OrderId"),
                        resultSet.getString("ItemCode"),
                        resultSet.getInt("OrderQTY"),
                        resultSet.getInt("Discount")
                );
                orderDetailInfos.add(orderDetail);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderDetailInfos;
    }

    @Override
    public String getDescription(String itemCode) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String SQL = "SELECT Description FROM item WHERE ItemCode = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1, itemCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("Description");
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateOrderDetail(String orderQty, String discount, String orderId, String itemCode) {
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
