package delivery_service.delivery_service.repositories.delivery;

import delivery_service.delivery_service.entities.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {


    List<Delivery> findDeliveryByOrderUuid(String orderUuid);
}
