package test_technical.order_service.mappers.orderitems;

import org.springframework.stereotype.Component;
import test_technical.order_service.dtos.order.OrderResponse;
import test_technical.order_service.dtos.orderitems.OrderItemResponse;
import test_technical.order_service.entities.OrderItems;
import test_technical.order_service.mappers.Mapper;

@Component
public class OrderItemResponseMapper implements Mapper<OrderItemResponse, OrderItems> {
    @Override
    public OrderItemResponse map(OrderItems e)  {
        if (e == null) return null;

        OrderItemResponse dto = new OrderItemResponse();
        dto.setItemSku(e.getItemSku());
        dto.setQuantity(e.getQuantity());
        return dto;
    }


}
