package test_technical.order_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import test_technical.order_service.entities.Order;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE TRIM(o.orderUuid) = TRIM(:orderUuid)")
    Optional<Order> findByOrderUuidTrimmed(@Param("orderUuid") String orderUuid);

}
