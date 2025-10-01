package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import model.OrderDetail;
import repository.OrderDetailRepositoryImpl;

public class OrderDetailServiceImpl implements OrderDetailService {

    OrderDetailRepositoryImpl orderDetailRepositoryImpl = new OrderDetailRepositoryImpl();

    @Override
    public ObservableList<OrderDetail> loadDetails(ObservableList<OrderDetail> orderDetailInfos) {
        try {
            ResultSet resultSet = orderDetailRepositoryImpl.loadDetails();
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
            ResultSet resultSet = orderDetailRepositoryImpl.getDescription(itemCode);
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
        orderDetailRepositoryImpl.updateOrderDetails(orderQty, discount, orderId, itemCode);
    }
}
