package com.datatrust360.processing;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String ANOMALY_QUEUE = "dt360.anomaly.scoring";

    @Bean
    public Queue anomalyQueue() {
        return new Queue(ANOMALY_QUEUE, true);
    }
}
