package delivery_service.delivery_service.messaging.messages;

public class DeliveryCreatedEvent {
    private String orderUuid;

    public DeliveryCreatedEvent() {}

    public DeliveryCreatedEvent(String orderUuid) {
        this.orderUuid = orderUuid;
    }

    // Getter y setter
    public String getOrderUuid() {
        return orderUuid;
    }

    public void setOrderUuid(String orderUuid) {
        this.orderUuid = orderUuid;
    }

}