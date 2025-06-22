package test_technical.order_service.services.order;

import test_technical.order_service.dtos.order.OrderRequestDto;
import test_technical.order_service.dtos.order.OrderResponse;

public interface OrderWriteInterface {

    public OrderResponse createOrder(OrderRequestDto orderRequestDto);
}
