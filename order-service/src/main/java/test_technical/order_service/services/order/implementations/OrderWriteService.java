package test_technical.order_service.services.order.implementations;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import test_technical.order_service.dtos.order.OrderRequestDto;
import test_technical.order_service.dtos.order.OrderResponse;
import test_technical.order_service.entities.Order;
import test_technical.order_service.entities.OrderItems;
import test_technical.order_service.mappers.order.OrderResponseMapper;
import test_technical.order_service.messaging.messages.OrderCreatedEvent;
import test_technical.order_service.messaging.publisher.OrderEventPublisher;
import test_technical.order_service.repositories.OrderRepository;
import test_technical.order_service.services.order.OrderWriteInterface;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderWriteService implements OrderWriteInterface {

    private final OrderResponseMapper orderResponseMapper;
    private final OrderRepository orderRepository;
    private final OrderEventPublisher orderEventPublisher;

    public OrderWriteService(OrderResponseMapper orderResponseMapper, OrderRepository orderRepository,OrderEventPublisher orderEventPublisher) {
        this.orderResponseMapper = orderResponseMapper;
        this.orderRepository = orderRepository;
        this.orderEventPublisher = orderEventPublisher;
    }

    @Transactional
    @Override
    public OrderResponse createOrder(OrderRequestDto orderRequestDto) {
        Order newOrder = new Order();
        newOrder.setOrderUuid(UUID.randomUUID().toString());

        List<OrderItems> orderItems = orderRequestDto.getItems().stream()
                .map(item -> {
                    OrderItems orderItem = new OrderItems();
                    orderItem.setOrder(newOrder);
                    orderItem.setItemSku(item.getItemSku());
                    orderItem.setQuantity(item.getQuantity());
                    return orderItem;
                })
                .collect(Collectors.toList());

        newOrder.setOrderItems(orderItems);
        orderRepository.save(newOrder);

        List<OrderCreatedEvent.OrderItem> eventItems = orderItems.stream()
                .map(item -> new OrderCreatedEvent.OrderItem(item.getItemSku(), item.getQuantity()))
                .collect(Collectors.toList());


        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                OrderCreatedEvent event = new OrderCreatedEvent(newOrder.getOrderUuid(), eventItems);
                orderEventPublisher.publish(event);
            }
        });

        return orderResponseMapper.map(newOrder);
    }
}
