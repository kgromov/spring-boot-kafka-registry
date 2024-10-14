## Springwolf

Springwolf is opensource library for generating AsyncAPI documentation.
Supported protocols (integrations):
 - AMQP, 
 - Spring Cloud Stream, 
 - JMS, 
 - Kafka, 
 - SNS, 
 - SQS, 
 - STOMP (WebSocket) 

| Protocol        |Auto-detected annotations  | Example Project                                                                                                                          |
|-----------------|---|------------------------------------------------------------------------------------------------------------------------------------------|
| AMQP (RabbitMQ) | @RabbitListener  | [springwolf-amqp-example](https://github.com/springwolf/springwolf-core/tree/master/springwolf-examples/springwolf-amqp-example)         |
| Cloud Functions |  @Bean (functional interface) | [springwolf-cloud-stream](https://github.com/springwolf/springwolf-core/tree/master/springwolf-examples/springwolf-cloud-stream-example) |
| JMS             | @JmsListener  | [springwolf-jms-example](https://github.com/springwolf/springwolf-core/tree/master/springwolf-examples/springwolf-jms-example)           |
| Kafka           | 	@KafkaListener, @KafkaHandler  | [springwolf-kafka-example](https://github.com/springwolf/springwolf-core/tree/master/springwolf-examples/springwolf-kafka-example)       |
| SNS             |   | [springwolf-sns-example](https://github.com/springwolf/springwolf-core/tree/master/springwolf-examples/springwolf-sns-example)           |
| SQS             |  @SqsListener | [springwolf-sqs-example](https://github.com/springwolf/springwolf-core/tree/master/springwolf-examples/springwolf-sqs-example)           |
| STOMP (WebSocket)     | @MessageMapping, @SendTo, @SendToUser  | [springwolf-stomp-example](https://github.com/springwolf/springwolf-core/tree/master/springwolf-examples/springwolf-stomp-example)       |

In general it's possible to implement any custom protocol by annotating producer (sender) with `@AsyncPublisher` and consumer with `@AsyncListener` respectively.  
Springwolf also provides some libraries to document bindings. Those libraries provide a light alternative (without 3rd party dependencies):   
library in format `springwolf-<protocol>-binding` e.g. `springwolf-kafka-binding`. Automatically detected annotations: `@KafkaAsyncOperationBinding` and `@KafkaAsyncOperation`   

When the Spring Boot application is started, Springwolf uses its scanners to find defined consumers and producers within the application.    
Based on the results, the channels/topics are extracted including payload type and merged together into an AsyncAPI conform document.   

Quickstart requires to add corresponding protocol library (e.g. `springwolf-kafka`) - to generate AsyncAPI documentation in json/yml formats.
In order to generate html-based documentation - [springwolf-ui](https://github.com/springwolf/springwolf-ui) library required.    
Another part is configuration (only minimum required properties specified):
```properties
springwolf.docket.base-package=io.github.springwolf.example.consumers
springwolf.docket.info.title=${spring.application.name}
springwolf.docket.info.version=1.0.0
springwolf.docket.servers.kafka-server.protocol=kafka
springwolf.docket.servers.kafka-server.host=${kafka.bootstrap.servers:localhost:29092}
```

This all in place will generate documentation in the following formats available by uri:

- JSON: <host>:<port>/springwolf/docs
- YAML: <host>:<port>/springwolf/docs.yaml
- HTML: <host>:<port>/springwolf/asyncapi-ui.html

For more sophisticated configuration yml file (aka OpenAPI) is used - it's possible to define servers per env, tags, description etc.

More details can be found on official [documentation](https://www.springwolf.dev/docs)