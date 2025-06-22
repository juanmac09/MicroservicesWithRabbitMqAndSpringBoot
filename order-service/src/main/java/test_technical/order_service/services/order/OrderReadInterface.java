package test_technical.order_service.services.order;

import test_technical.order_service.dtos.order.OrderResponse;
import test_technical.order_service.dtos.orderitems.OrderItemRequest;
import test_technical.order_service.dtos.orderitems.OrderItemResponse;

public interface OrderReadInterface {

    public OrderResponse getItemByUuid(String uuid);
}
