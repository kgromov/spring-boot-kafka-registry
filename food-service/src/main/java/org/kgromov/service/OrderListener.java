package org.kgromov.service;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kgromov.schema.events.OrderPlacedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderListener {
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    @KafkaListener(topics = "order-placed", groupId = "order-service")
    public void onOrderPlaced(Message<OrderPlacedEvent> message) {
        var event = message.getPayload();
        log.info("Received order placed event: {}", event);

        var dish = Faker.instance().food();
        var orderPlacedEvent = new OrderPlacedEvent(event.getOrderNumber(), dish.dish(), event.getFirstName(), event.getLastName());
        log.info("Sending order placed event: {}", orderPlacedEvent);
        kafkaTemplate.send("order-placed", orderPlacedEvent);
    }
}
