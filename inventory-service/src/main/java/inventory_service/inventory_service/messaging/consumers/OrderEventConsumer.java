package inventory_service.inventory_service.messaging.consumers;

import inventory_service.inventory_service.messaging.messages.InventoryValidatedEvent;
import inventory_service.inventory_service.messaging.messages.OrderCreatedEvent;
import inventory_service.inventory_service.messaging.publisher.InventoryEventPublisher;
import inventory_service.inventory_service.services.businesslogic.ValidateInventory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OrderEventConsumer {

    private final ValidateInventory validateInventory;
    private final InventoryEventPublisher inventoryEventPublisher;

    public OrderEventConsumer(ValidateInventory validateInventory,
                              InventoryEventPublisher inventoryEventPublisher) {
        this.validateInventory = validateInventory;
        this.inventoryEventPublisher = inventoryEventPublisher;
    }

    @RabbitListener(queues = "${rabbitmq.order.queue}")
    public void handleOrderCreated(OrderCreatedEvent event) {

        System.out.println(event.getOrderUuid());
        Map<String, Integer> items = new HashMap<>();
        for (OrderCreatedEvent.OrderItem item : event.getItems()) {
            items.put(item.getItemSku(), item.getQuantity());
        }

        var result = validateInventory.validateStock(items);

        InventoryValidatedEvent response = new InventoryValidatedEvent(
                event.getOrderUuid(),
                result.isSuccess(),
                result.isSuccess() ? "Inventory available" : "Some items out of stock",
                result.getOutOfStockItems()
        );

        inventoryEventPublisher.sendInventoryValidatedEvent(response);
    }
}
