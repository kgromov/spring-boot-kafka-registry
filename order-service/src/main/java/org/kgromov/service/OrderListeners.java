package org.kgromov.service;

import com.github.javafaker.Faker;
import io.github.springwolf.bindings.kafka.annotations.KafkaAsyncOperationBinding;
import io.github.springwolf.core.asyncapi.annotations.AsyncListener;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
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
    @AsyncListener(operation = @AsyncOperation(
            channelName = "order-accepted",
            description = "Listen for accepted order"
    ))
    @KafkaAsyncOperationBinding
    public void onOrderPlaced(OrderAcceptedEvent event) {
        log.info("Received order placed event: {}", event);
        Faker faker = new Faker();
        var person = faker.name();
        String emailAddress = faker.internet().emailAddress();
        orderService.placeOrder(new OrderPlacedEvent(faker.random().nextLong(), emailAddress, person.firstName(), person.lastName()));
    };
}
