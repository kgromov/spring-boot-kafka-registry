package org.kgromov;

import com.github.javafaker.Faker;
import org.kgromov.schema.events.OrderPlacedEvent;
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
            var person = faker.name();
            String emailAddress = faker.internet().emailAddress();
            orderService.placeOrder(new OrderPlacedEvent(faker.random().nextLong(), emailAddress, person.firstName(), person.lastName()));
        };
    }
}
