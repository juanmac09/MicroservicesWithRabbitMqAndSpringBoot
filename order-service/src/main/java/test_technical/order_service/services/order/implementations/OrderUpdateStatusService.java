package test_technical.order_service.services.order.implementations;

import org.springframework.stereotype.Service;
import test_technical.order_service.entities.Order;
import test_technical.order_service.enums.OrderStatus;
import test_technical.order_service.exceptions.NotFoundException;
import test_technical.order_service.repositories.OrderRepository;
import test_technical.order_service.services.order.OrderUpdateStatusInterface;

import java.util.Optional;

@Service
public class OrderUpdateStatusService implements OrderUpdateStatusInterface {

    private final OrderRepository orderRepository;

    public OrderUpdateStatusService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void orderUpdateStatus(String orderUuid, String status) {
        Optional<Order> optionalOrder = orderRepository.findByOrderUuidTrimmed(orderUuid.trim());

        if (optionalOrder.isEmpty()) {
            System.out.println("Orden no encontrada para UUID: " + orderUuid);
            return;
        }

        Order order = optionalOrder.get();

        try {
            OrderStatus newStatus = OrderStatus.valueOf(status.toUpperCase());
            order.setStatus(newStatus);
            orderRepository.save(order);
        } catch (IllegalArgumentException e) {
            System.out.println("Estado inválido para la orden " + orderUuid + ": " + status);
        }
    }
}
