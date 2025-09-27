package controller.orderdetailController;

import javafx.collections.ObservableList;
import model.OrderDetail;

public interface OrderDetailService {
    ObservableList<OrderDetail> loadDetails(ObservableList<OrderDetail> orderDetailInfos);

    String getDescription(String itemCode);

    void updateOrderDetail(String orderQty, String discount, String orderId, String itemCode);
}
