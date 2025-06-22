package delivery_service.delivery_service.services.implementaciones;

import delivery_service.delivery_service.dtos.delivery.DeliveryRequestDto;
import delivery_service.delivery_service.dtos.delivery.DeliveryResponse;
import delivery_service.delivery_service.entities.Delivery;
import delivery_service.delivery_service.mappers.delivery.DeliveryResponseMapper;
import delivery_service.delivery_service.repositories.delivery.DeliveryRepository;
import delivery_service.delivery_service.services.DeliveryCreateInterface;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class DeliveryCreateService implements DeliveryCreateInterface {

    private final DeliveryResponseMapper deliveryResponseMapper;
    private final DeliveryRepository deliveryRepository;

    public DeliveryCreateService(DeliveryResponseMapper deliveryResponseMapper, DeliveryRepository deliveryRepository) {
        this.deliveryResponseMapper = deliveryResponseMapper;
        this.deliveryRepository = deliveryRepository;
    }

    @Transactional
    @Override
    public DeliveryResponse createDelivery(DeliveryRequestDto deliveryRequestDto) {
        Delivery newDelivery = new Delivery();
        newDelivery.setOrderUuid(deliveryRequestDto.getOrderUuid());
        newDelivery.setAddress(deliveryRequestDto.getAddress());
        this.deliveryRepository.save(newDelivery);
        return this.deliveryResponseMapper.map(newDelivery);
    }
}
