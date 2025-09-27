package controller.ordersController;

import javafx.collections.ObservableList;
import model.Orders;

public interface OrdersService {
    ObservableList<Orders> loadDetails(ObservableList<Orders> ordersInfos);

    void updateOrder(String DOB, String orderId, String customerId);

    String getCustomerName(String customerId);

    void deleteOrder(String orderId);
}
