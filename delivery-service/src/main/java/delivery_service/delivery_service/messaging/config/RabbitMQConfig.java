package delivery_service.delivery_service.messaging.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "main.exchange";
    public static final String INVENTORY_QUEUE = "inventory.delivery.queue";
    public static final String INVENTORY_ROUTING_KEY = "inventory.validated";
    public static final String DELIVERY_ROUTING_KEY = "delivery.created";

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue inventoryValidatedQueue() {
        return new Queue(INVENTORY_QUEUE);
    }


    @Bean
    public Binding inventoryBinding(Queue inventoryValidatedQueue, TopicExchange exchange) {
        return BindingBuilder.bind(inventoryValidatedQueue)
                .to(exchange)
                .with(INVENTORY_ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         Jackson2JsonMessageConverter messageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);
        return template;
    }
}
