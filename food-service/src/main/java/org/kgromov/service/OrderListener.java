package org.kgromov.service;

import com.github.javafaker.Faker;
import org.kgromov.schema.events.OrderPlacedEvent;
import org.slf4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderListener {
    private final static Logger log = org.slf4j.LoggerFactory.getLogger(OrderListener.class);
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public OrderListener(KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "order-placed", groupId = "order-service")
    public void onOrderPlaced(OrderPlacedEvent event) {
        log.info("Received order placed event: {}", event);

        var dish = Faker.instance().food();
        var orderPlacedEvent = new OrderPlacedEvent(event.getOrderNumber(), dish.dish(), event.getFirstName(), event.getLastName());
        log.info("Sending order placed event: {}", orderPlacedEvent);
        kafkaTemplate.send("order-placed", orderPlacedEvent);
    }
}
