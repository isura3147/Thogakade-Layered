package repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface OrderDetailRepository {

    ResultSet loadDetails() throws SQLException;

    ResultSet getDescription(String itemCode) throws SQLException;

    void updateOrderDetails(String orderQty, String discount, String orderId, String itemCode);
}
