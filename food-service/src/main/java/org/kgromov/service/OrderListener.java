package org.kgromov.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kgromov.schema.events.OrderAcceptedEvent;
import org.kgromov.schema.events.OrderCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderListener {
    private final KafkaTemplate<String, OrderAcceptedEvent> kafkaTemplate;

    @KafkaListener(topics = "order-created", groupId = "order-service")
    public void onOrderPlaced(Message<OrderCreatedEvent> message) {
        var event = message.getPayload();
        log.info("Received order placed event: {}", event);

        var orderPlacedEvent = new OrderAcceptedEvent(event.getOrderNumber());
        log.info("Sending order accepted event: {}", orderPlacedEvent);
        kafkaTemplate.send("order-accepted", orderPlacedEvent);
    }
}
