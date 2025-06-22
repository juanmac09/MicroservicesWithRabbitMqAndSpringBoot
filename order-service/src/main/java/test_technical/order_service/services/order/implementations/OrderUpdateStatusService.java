package test_technical.order_service.services.order.implementations;

import org.springframework.stereotype.Service;
import test_technical.order_service.entities.Order;
import test_technical.order_service.enums.OrderStatus;
import test_technical.order_service.exceptions.NotFoundException;
import test_technical.order_service.repositories.OrderRepository;
import test_technical.order_service.services.order.OrderUpdateStatusInterface;

import java.util.List;

@Service
public class OrderUpdateStatusService implements OrderUpdateStatusInterface {
    public final OrderRepository orderRepository;

    public OrderUpdateStatusService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void orderUpdateStatus(String orderUuid, String status) {
        List<Order> orders = orderRepository.getOrderByOrderUuid(orderUuid);

        if (orders == null || orders.isEmpty()) {
            throw new NotFoundException("Order with UUID " + orderUuid + " not found");
        }

        Order order = orders.getLast();

        try {
            OrderStatus newStatus = OrderStatus.valueOf(status.toUpperCase());
            order.setStatus(newStatus);
            orderRepository.save(order);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid order status: " + status);
        }
    }
}
