package guru.springframework.sfgjms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
public class JmsConfig {

    public static final String MY_QUEUE_NAME = "my-hello-world";

    @Bean
    public MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter(); // Spring will provide an objectMapper
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");                                           // In this property Spring for Apache ActiveMQ will specify the class name as what Spring needs to deserialize the (JSON) message payload

        return converter;
    }
}
