package com.datatrust360.processing;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class AnomalyScoringWorker {

    @RabbitListener(queues = RabbitConfig.ANOMALY_QUEUE)
    public void score(String payload) {
        // TODO: call ML/anomaly scoring service and persist results.
        System.out.println("Scoring payload length=" + payload.length());
    }
}
