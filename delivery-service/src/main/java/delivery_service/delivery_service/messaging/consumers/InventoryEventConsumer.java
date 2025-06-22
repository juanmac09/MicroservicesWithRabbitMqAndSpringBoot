package delivery_service.delivery_service.messaging.consumers;

import delivery_service.delivery_service.dtos.delivery.DeliveryRequestDto;
import delivery_service.delivery_service.messaging.messages.InventoryValidatedEvent;
import delivery_service.delivery_service.messaging.publishers.DeliveryEventPublisher;
import delivery_service.delivery_service.services.DeliveryCreateInterface;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class InventoryEventConsumer {

    private final DeliveryCreateInterface deliveryCreateInterface;
    private final DeliveryEventPublisher deliveryEventPublisher;

    public InventoryEventConsumer(DeliveryCreateInterface deliveryCreateInterface,
                                  DeliveryEventPublisher deliveryEventPublisher) {
        this.deliveryCreateInterface = deliveryCreateInterface;
        this.deliveryEventPublisher = deliveryEventPublisher;
    }

    @RabbitListener(queues = "${rabbitmq.inventory.queue}")
    public void handleInventoryValidatedEvent(InventoryValidatedEvent event) {

        if (event.isSuccess()) {
            DeliveryRequestDto deliveryRequestDto = new DeliveryRequestDto();
            deliveryRequestDto.setOrderUuid(event.getOrderUuid());
            deliveryRequestDto.setAddress("example");
            deliveryRequestDto.setDeliveryStatus("PENDING");

            deliveryCreateInterface.createDelivery(deliveryRequestDto);

            deliveryEventPublisher.publishDeliveryCreated(event.getOrderUuid());
        } else {
            System.out.println("Inventario fallido para orden " + event.getOrderUuid());
        }
    }
}
