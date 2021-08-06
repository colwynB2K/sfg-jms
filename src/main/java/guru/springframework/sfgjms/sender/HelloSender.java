package guru.springframework.sfgjms.sender;

import guru.springframework.sfgjms.config.JmsConfig;
import guru.springframework.sfgjms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
@Slf4j
public class HelloSender {

    private final JmsTemplate jmsTemplate;

    @Scheduled(fixedRate = 2000)            // Schedule execution of this method every 2000 ms
    public void sendMessage() {
        log.info("I'm sending a message");

        HelloWorldMessage message = HelloWorldMessage.builder()
                                                    .id(UUID.randomUUID())
                                                    .message("Hello World!")
                                                    .build();

        jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE_NAME, message);

        log.info("Message sent");
    }
}
