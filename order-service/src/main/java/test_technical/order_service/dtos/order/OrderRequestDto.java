package test_technical.order_service.dtos.order;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import test_technical.order_service.dtos.orderitems.OrderItemRequest;

import java.util.List;

public class OrderRequestDto {

    @NotEmpty(message = "Order must have at least one item")
    private @Valid List<OrderItemRequest> items;

    // Getters and Setters

    public List<OrderItemRequest> getItems() {
        return items;
    }

    public void setItems(List<OrderItemRequest> items) {
        this.items = items;
    }
}
