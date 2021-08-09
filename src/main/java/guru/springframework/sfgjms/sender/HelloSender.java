package guru.springframework.sfgjms.sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.sfgjms.config.JmsConfig;
import guru.springframework.sfgjms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.UUID;

@RequiredArgsConstructor
@Component
@Slf4j
public class HelloSender {

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

    @Scheduled(fixedRate = 2000)            // Schedule execution of this method every 2000 ms
    public void sendMessage() {
        HelloWorldMessage message = HelloWorldMessage.builder()
                                                    .id(UUID.randomUUID())
                                                    .message("Hello World!")
                                                    .build();

        jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE_NAME, message);

    }

    @Scheduled(fixedRate = 2000)            // Schedule execution of this method every 2000 ms
    public void sendAndReceiveMessage() throws JMSException {

        HelloWorldMessage message = HelloWorldMessage.builder()
                .id(UUID.randomUUID())
                .message("Hello")
                .build();

        Message receivedMessage = jmsTemplate.sendAndReceive(JmsConfig.MY_SEND_RECEIVE_QUEUE_NAME, new MessageCreator() { // Bit like convert and send, but we are creaing the message manually because the messageConverter Bean mentioned in JmsConfig is only wired into the convertAndSend method
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message helloMessage = null;
                try {
                    helloMessage = session.createTextMessage(objectMapper.writeValueAsString(message));
                    helloMessage.setStringProperty("_type", "guru.springframework.sfgjms.model.HelloWorldMessage");

                    log.info("Sending hello...");

                    return helloMessage;
                } catch (JsonProcessingException e) {
                    throw new JMSException("KABOOM");
                }
            }
        });

        log.info("receivedMessage = " + receivedMessage.getBody(String.class));
    }
}
