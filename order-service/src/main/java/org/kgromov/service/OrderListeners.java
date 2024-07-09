package org.kgromov.service;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kgromov.schema.events.OrderAcceptedEvent;
import org.kgromov.schema.events.OrderPlacedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderListeners {
    private final OrderService orderService;

    @KafkaListener(topics = "order-accepted", groupId = "food-service")
    public void onOrderPlaced(OrderAcceptedEvent event) {
        log.info("Received order placed event: {}", event);
        Faker faker = new Faker();
        var person = faker.name();
        String emailAddress = faker.internet().emailAddress();
        orderService.placeOrder(new OrderPlacedEvent(faker.random().nextLong(), emailAddress, person.firstName(), person.lastName()));
    };
}
