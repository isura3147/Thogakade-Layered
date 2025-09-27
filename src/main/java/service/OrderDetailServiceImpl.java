package service;

import db.DBConnection;
import javafx.collections.ObservableList;
import model.OrderDetail;
import repository.OrderDetailRepository;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailServiceImpl implements OrderDetailService {

    OrderDetailRepository orderDetailRepository = new OrderDetailRepository();

    @Override
    public ObservableList<OrderDetail> loadDetails(ObservableList<OrderDetail> orderDetailInfos) {
        try {
            ResultSet resultSet = orderDetailRepository.loadDetails();
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
            ResultSet resultSet = orderDetailRepository.getDescription(itemCode);
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
        orderDetailRepository.updateOrderDetails(orderQty, discount, orderId, itemCode);
    }
}
