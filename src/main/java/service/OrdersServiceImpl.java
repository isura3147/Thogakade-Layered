package service;

import javafx.collections.ObservableList;
import model.Orders;
import repository.OrdersRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdersServiceImpl implements OrdersService {

    OrdersRepository ordersRepository = new OrdersRepository();

    @Override
    public ObservableList<Orders> loadDetails(ObservableList<Orders> ordersInfos) {
        try {
            ResultSet resultSet = ordersRepository.loadDetails();

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
        ordersRepository.updateOrder(DOB, orderId, customerId);
    }

    @Override
    public String getCustomerName(String customerId) {
        return ordersRepository.getCustomerName(customerId);
    }

    @Override
    public void deleteOrder(String orderId) {
        ordersRepository.deleteOrder(orderId);
    }
}
