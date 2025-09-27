package repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface OrdersRepository {

    ResultSet loadDetails() throws SQLException;

    void updateOrder(String DOB, String orderId, String customerId);

    String getCustomerName(String customerId);

    void deleteOrder(String orderId);
}
