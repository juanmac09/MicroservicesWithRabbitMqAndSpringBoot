package test_technical.order_service.dtos.order;

import test_technical.order_service.dtos.orderitems.OrderItemResponse;

import java.time.Instant;
import java.util.List;

public class OrderResponse {

    private String orderUuid;
    private String status;
    private Instant createdAt;
    private List<OrderItemResponse> items;

    // Getters and Setters

    public String getOrderUuid() {
        return orderUuid;
    }

    public void setOrderUuid(String orderUuid) {
        this.orderUuid = orderUuid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public List<OrderItemResponse> getItems() {
        return items;
    }

    public void setItems(List<OrderItemResponse> items) {
        this.items = items;
    }
}

