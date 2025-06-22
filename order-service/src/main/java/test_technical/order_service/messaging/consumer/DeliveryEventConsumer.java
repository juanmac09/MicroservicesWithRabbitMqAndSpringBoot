package test_technical.order_service.messaging.consumer;

import org.springframework.stereotype.Component;
import test_technical.order_service.enums.OrderStatus;
import test_technical.order_service.messaging.messages.DeliveryCreatedEvent;
import test_technical.order_service.services.order.OrderUpdateStatusInterface;

@Component
public class DeliveryEventConsumer {

    private final OrderUpdateStatusInterface orderUpdateStatusService;

    public DeliveryEventConsumer(OrderUpdateStatusInterface orderUpdateStatusService) {
        this.orderUpdateStatusService = orderUpdateStatusService;
    }

    public void handleDeliveryCreated(DeliveryCreatedEvent event) {
        orderUpdateStatusService.orderUpdateStatus(event.getOrderUuid(), OrderStatus.DELIVERY_CREATED.toString());
    }
}