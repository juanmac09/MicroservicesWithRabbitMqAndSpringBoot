package delivery_service.delivery_service.mappers.delivery;

import delivery_service.delivery_service.dtos.delivery.DeliveryResponse;
import delivery_service.delivery_service.entities.Delivery;
import delivery_service.delivery_service.mappers.Mapper;
import org.springframework.stereotype.Component;

@Component
public class DeliveryResponseMapper implements Mapper<DeliveryResponse, Delivery > {

    @Override
    public DeliveryResponse map(Delivery e) {
        if (e == null) return null;

        DeliveryResponse dto = new DeliveryResponse();
        dto.setId(e.getId());
        dto.setOrderUuid(e.getOrderUuid());
        dto.setDeliveryStatus(e.getDeliveryStatus().toString());
        dto.setAddress(e.getAddress());
        dto.setCreatedAt(e.getCreatedAt());

        return dto;
    }
}
