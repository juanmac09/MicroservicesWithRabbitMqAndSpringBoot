package test_technical.order_service.mappers.order;

import org.springframework.stereotype.Component;
import test_technical.order_service.dtos.order.OrderResponse;
import test_technical.order_service.entities.Order;
import test_technical.order_service.mappers.Mapper;
import test_technical.order_service.mappers.orderitems.OrderItemResponseMapper;

@Component
public class OrderResponseMapper implements Mapper<OrderResponse, Order> {

    private final OrderItemResponseMapper orderItemResponseMapper;


    public OrderResponseMapper(OrderItemResponseMapper orderItemResponseMapper) {
        this.orderItemResponseMapper = orderItemResponseMapper;
    }

    @Override
    public OrderResponse map(Order e) {
        if (e == null) return null;

        OrderResponse dto = new OrderResponse();
        dto.setOrderUuid(e.getOrderUuid());
        dto.setItems(
                e.getOrderItems().stream()
                        .map(orderItemResponseMapper::map)
                        .toList()
        );
        dto.setStatus(e.getStatus().toString());
        dto.setCreatedAt(e.getCreatedAt());

        return dto;
    }
}
