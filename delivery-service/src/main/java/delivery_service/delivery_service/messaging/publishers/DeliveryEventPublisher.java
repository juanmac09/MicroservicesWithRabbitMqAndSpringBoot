package delivery_service.delivery_service.messaging.publishers;

import delivery_service.delivery_service.messaging.messages.DeliveryCreatedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DeliveryEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.delivery.routing-key}")
    private String routingKey;

    public DeliveryEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishDeliveryCreated(String orderUuid) {
        DeliveryCreatedEvent event = new DeliveryCreatedEvent(orderUuid);
        rabbitTemplate.convertAndSend(exchange, routingKey, event);
    }
}
