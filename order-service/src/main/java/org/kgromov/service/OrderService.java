package org.kgromov.service;

import io.github.springwolf.bindings.kafka.annotations.KafkaAsyncOperationBinding;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.annotations.AsyncPublisher;
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

    @AsyncPublisher(operation = @AsyncOperation(
            channelName = "order-created",
            description = "Send order created event"
    ))
    @KafkaAsyncOperationBinding
    public void createOrder(OrderCreatedEvent event) {
        log.info("Sending order created event: {}", event);
        kafkaTemplate2.send("order-created", event);
    }

    @AsyncPublisher(operation = @AsyncOperation(
            channelName = "order-placed",
            description = "Send order placed event"
    ))
    @KafkaAsyncOperationBinding
    public void placeOrder(OrderPlacedEvent event) {
        log.info("Sending order placed event: {}", event);
        kafkaTemplate.send("order-placed", event);
    }
}
