package test_technical.order_service.messaging.messages;

public class DeliveryCreatedEvent {
    private String orderUuid;

    public DeliveryCreatedEvent() {
    }

    public DeliveryCreatedEvent(String orderUuid) {
        this.orderUuid = orderUuid;
    }

    public String getOrderUuid() {
        return orderUuid;
    }

    public void setOrderUuid(String orderUuid) {
        this.orderUuid = orderUuid;
    }
}
