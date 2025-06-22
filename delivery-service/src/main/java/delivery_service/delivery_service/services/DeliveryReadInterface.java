package delivery_service.delivery_service.services;

import delivery_service.delivery_service.dtos.delivery.DeliveryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DeliveryReadInterface {

    public Page<DeliveryResponse> deliveryGetAll(Pageable pageable);
    public DeliveryResponse deliveryGetByOrderUuid(String orderUuid);
}
