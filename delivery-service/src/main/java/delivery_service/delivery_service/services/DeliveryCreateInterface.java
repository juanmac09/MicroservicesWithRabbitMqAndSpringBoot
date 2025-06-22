package delivery_service.delivery_service.services;

import delivery_service.delivery_service.dtos.delivery.DeliveryRequestDto;
import delivery_service.delivery_service.dtos.delivery.DeliveryResponse;

public interface DeliveryCreateInterface {

    public DeliveryResponse createDelivery(DeliveryRequestDto deliveryRequestDto);
}
