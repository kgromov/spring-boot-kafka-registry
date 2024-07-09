package org.kgromov.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kgromov.schema.events.OrderCreatedEvent;
import org.kgromov.schema.events.OrderPlacedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;
    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate2;

    public void createOrder(OrderCreatedEvent event) {
        log.info("Sending order created event: {}", event);
        kafkaTemplate2.send("order-created", event);
    }

    public void placeOrder(OrderPlacedEvent event) {
        log.info("Sending order placed event: {}", event);
        kafkaTemplate.send("order-placed", event);
    }
}
