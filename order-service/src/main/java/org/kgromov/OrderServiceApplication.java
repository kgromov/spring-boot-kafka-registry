package org.kgromov;

import com.github.javafaker.Faker;
import com.github.javafaker.Food;
import org.kgromov.schema.events.OrderCreatedEvent;
import org.kgromov.service.OrderService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner(OrderService orderService) {
        return args -> {
            Faker faker = new Faker();
            Food food = faker.food();
            var orderCreatedEvent = new OrderCreatedEvent(faker.random().nextLong(), food.dish(), faker.random().nextInt(0, 10));
            orderService.createOrder(orderCreatedEvent);
        };
    }
}
