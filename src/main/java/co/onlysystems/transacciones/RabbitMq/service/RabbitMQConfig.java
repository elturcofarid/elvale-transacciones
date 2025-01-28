
package co.onlysystems.transacciones.RabbitMq.service;

import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "accountQueue";

    @Bean
    public Queue exampleQueue() {
        return new Queue(QUEUE_NAME, true);
    }
}