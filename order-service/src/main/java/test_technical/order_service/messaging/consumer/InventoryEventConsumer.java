package test_technical.order_service.messaging.consumer;

import org.springframework.stereotype.Component;
import inventory_service.inventory_service.messaging.messages.InventoryValidatedEvent;
import test_technical.order_service.services.order.OrderUpdateStatusInterface;

@Component
public class InventoryEventConsumer {

    private final OrderUpdateStatusInterface orderUpdateStatusService;

    public InventoryEventConsumer(OrderUpdateStatusInterface orderUpdateStatusService) {
        this.orderUpdateStatusService = orderUpdateStatusService;
    }


    public void handleInventoryValidated(InventoryValidatedEvent event) {

        System.out.println("Received InventoryValidatedEvent for order: " + event.getOrderUuid());
        if (event.isSuccess()) {
            orderUpdateStatusService.orderUpdateStatus(event.getOrderUuid(), "INVENTORY_VALID");
        } else {
            orderUpdateStatusService.orderUpdateStatus(event.getOrderUuid(), "INVENTORY_FAILED");
        }
    }
}
