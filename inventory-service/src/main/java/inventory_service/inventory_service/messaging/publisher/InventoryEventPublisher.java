package inventory_service.inventory_service.messaging.publisher;

import inventory_service.inventory_service.messaging.messages.InventoryValidatedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class InventoryEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.inventory.routing-key}")
    private String routingKey;

    public InventoryEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendInventoryValidatedEvent(InventoryValidatedEvent event) {
        rabbitTemplate.convertAndSend(exchange, routingKey, event);
    }
}