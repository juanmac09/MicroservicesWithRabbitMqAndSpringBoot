package test_technical.order_service.services.order.implementations;

import org.springframework.stereotype.Service;
import test_technical.order_service.dtos.order.OrderResponse;
import test_technical.order_service.entities.Order;
import test_technical.order_service.exceptions.NotFoundException;
import test_technical.order_service.mappers.order.OrderResponseMapper;
import test_technical.order_service.repositories.OrderRepository;
import test_technical.order_service.services.order.OrderReadInterface;

import java.util.Optional;

@Service
public class OrderReadService implements OrderReadInterface {

    private final OrderResponseMapper orderResponseMapper;
    private final OrderRepository orderRepository;

    public OrderReadService(OrderResponseMapper orderResponseMapper, OrderRepository orderRepository) {
        this.orderResponseMapper = orderResponseMapper;
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderResponse getItemByUuid(String uuid) {
        Optional<Order> optionalOrder = orderRepository.findByOrderUuidTrimmed(uuid.trim());

        Order order = optionalOrder.orElseThrow(() ->
                new NotFoundException("Order with UUID " + uuid + " not found"));

        return orderResponseMapper.map(order);
    }
}
