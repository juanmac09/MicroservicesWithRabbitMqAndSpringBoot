package test_technical.order_service.messaging.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import test_technical.order_service.messaging.consumer.DeliveryEventConsumer;
import test_technical.order_service.messaging.consumer.InventoryEventConsumer;
import inventory_service.inventory_service.messaging.messages.InventoryValidatedEvent;


import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue}")
    private String queueName;

    @Value("${rabbitmq.exchange}")
    private String exchangeName;

    @Value("${rabbitmq.routing-key}")
    private String routingKey;

    @Value("${rabbitmq.inventory.queue}")
    private String inventoryQueue;

    @Value("${rabbitmq.inventory.routing-key}")
    private String inventoryRoutingKey;

    @Value("${rabbitmq.delivery.queue}")
    private String deliveryQueue;

    @Value("${rabbitmq.delivery.routing-key}")
    private String deliveryRoutingKey;

    // Exchange
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    // Queues
    @Bean
    public Queue orderQueue() {
        return new Queue(queueName, true);
    }

    @Bean
    public Queue inventoryValidatedQueue() {
        return new Queue(inventoryQueue, true);
    }

    @Bean
    public Queue deliveryCreatedQueue() {
        return new Queue(deliveryQueue, true);
    }

    // Bindings
    @Bean
    public Binding orderBinding(Queue orderQueue, TopicExchange exchange) {
        return BindingBuilder.bind(orderQueue).to(exchange).with(routingKey);
    }

    @Bean
    public Binding inventoryBinding(Queue inventoryValidatedQueue, TopicExchange exchange) {
        return BindingBuilder.bind(inventoryValidatedQueue).to(exchange).with(inventoryRoutingKey);
    }

    @Bean
    public Binding deliveryBinding(Queue deliveryCreatedQueue, TopicExchange exchange) {
        return BindingBuilder.bind(deliveryCreatedQueue).to(exchange).with(deliveryRoutingKey);
    }

    // Message Converter
    @Bean
    public MessageConverter jsonMessageConverter() {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();

        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
        typeMapper.setTrustedPackages("*"); // Permite cualquier paquete

        // Puedes mapear explícitamente el tipo si prefieres:
        Map<String, Class<?>> idClassMapping = new HashMap<>();
        idClassMapping.put("delivery_service.delivery_service.messaging.messages.DeliveryCreatedEvent",
                test_technical.order_service.messaging.messages.DeliveryCreatedEvent.class);

        typeMapper.setIdClassMapping(idClassMapping);
        converter.setJavaTypeMapper(typeMapper);

        return converter;
    }

    // RabbitTemplate con JSON
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }

    // Listeners para eventos de INVENTARIO
    @Bean
    public MessageListenerAdapter inventoryListenerAdapter(InventoryEventConsumer consumer,
                                                           MessageConverter messageConverter) {
        MessageListenerAdapter adapter = new MessageListenerAdapter(consumer, "handleInventoryValidated");
        adapter.setMessageConverter(messageConverter);
        return adapter;
    }

    @Bean
    public SimpleMessageListenerContainer inventoryContainer(ConnectionFactory connectionFactory,
                                                             MessageListenerAdapter inventoryListenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(inventoryQueue);
        container.setMessageListener(inventoryListenerAdapter);
        return container;
    }

    // Listeners para eventos de DELIVERY
    @Bean
    public MessageListenerAdapter deliveryListenerAdapter(DeliveryEventConsumer consumer,
                                                          MessageConverter messageConverter) {
        MessageListenerAdapter adapter = new MessageListenerAdapter(consumer, "handleDeliveryCreated");
        adapter.setMessageConverter(messageConverter);
        return adapter;
    }

    @Bean
    public SimpleMessageListenerContainer deliveryContainer(ConnectionFactory connectionFactory,
                                                            MessageListenerAdapter deliveryListenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(deliveryQueue);
        container.setMessageListener(deliveryListenerAdapter);
        return container;
    }



}
