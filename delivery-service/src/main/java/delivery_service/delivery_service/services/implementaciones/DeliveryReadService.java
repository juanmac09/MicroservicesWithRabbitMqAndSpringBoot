package delivery_service.delivery_service.services.implementaciones;

import delivery_service.delivery_service.dtos.delivery.DeliveryResponse;
import delivery_service.delivery_service.entities.Delivery;
import delivery_service.delivery_service.exceptions.NotFoundException;
import delivery_service.delivery_service.mappers.delivery.DeliveryResponseMapper;
import delivery_service.delivery_service.repositories.delivery.DeliveryRepository;
import delivery_service.delivery_service.services.DeliveryReadInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryReadService implements DeliveryReadInterface {

    public final DeliveryRepository deliveryRepository;
    public final DeliveryResponseMapper deliveryResponseMapper;

    public DeliveryReadService(DeliveryRepository deliveryRepository, DeliveryResponseMapper deliveryResponseMapper) {
        this.deliveryRepository = deliveryRepository;
        this.deliveryResponseMapper = deliveryResponseMapper;
    }

    @Override
    public Page<DeliveryResponse> deliveryGetAll(Pageable pageable) {
        Page<Delivery> deliveries = this.deliveryRepository.findAll(pageable);
        return deliveries.map(this.deliveryResponseMapper::map);
    }

    @Override
    public DeliveryResponse deliveryGetByOrderUuid(String orderUuid) {
        List<Delivery> deliveries = this.deliveryRepository.findDeliveryByOrderUuid(orderUuid);

        if (deliveries.isEmpty()) {
            throw new NotFoundException("Delivery with order uuid " + orderUuid + " not found");
        }

        return this.deliveryResponseMapper.map(deliveries.getLast());
    }
}
