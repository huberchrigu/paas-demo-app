package ch.silberruecken.paasdemo;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author christoph.huber
 * @since 10.01.2018
 */
@Configuration
public class RabbitMqConfig {
    static final String QUEUE_NAME = "test";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, false);
    }
}
