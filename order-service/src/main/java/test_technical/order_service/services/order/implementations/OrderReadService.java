package test_technical.order_service.services.order.implementations;

import org.springframework.stereotype.Service;
import test_technical.order_service.dtos.order.OrderResponse;
import test_technical.order_service.entities.Order;
import test_technical.order_service.exceptions.NotFoundException;
import test_technical.order_service.mappers.order.OrderResponseMapper;
import test_technical.order_service.repositories.OrderRepository;
import test_technical.order_service.services.order.OrderReadInterface;

import java.util.List;

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
        List<Order> orders = orderRepository.getOrderByOrderUuid(uuid);
        if (orders == null || orders.isEmpty()) {
            throw new NotFoundException("Order with UUID " + uuid + " not found");
        }

        Order lastOrder = orders.getLast();
        return orderResponseMapper.map(lastOrder);
    }
}
