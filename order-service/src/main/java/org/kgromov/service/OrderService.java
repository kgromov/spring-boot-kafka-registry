package org.kgromov.service;

import org.kgromov.schema.events.OrderPlacedEvent;
import org.slf4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final static Logger log = org.slf4j.LoggerFactory.getLogger(OrderService.class);
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public OrderService(KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void placeOrder(OrderPlacedEvent event) {
        log.info("Sending order placed event: {}", event);
        kafkaTemplate.send("order-placed", event);
    }
}
