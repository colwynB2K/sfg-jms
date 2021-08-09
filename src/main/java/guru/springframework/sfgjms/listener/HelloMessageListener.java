package guru.springframework.sfgjms.listener;

import guru.springframework.sfgjms.config.JmsConfig;
import guru.springframework.sfgjms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;                                                   // Be careful to import the correct Message class!!!
import java.util.UUID;

@RequiredArgsConstructor
@Component
@Slf4j
public class HelloMessageListener {

    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.MY_QUEUE_NAME)                     // Listen to "my-hello-world" queue for messages
    public void listen(@Payload HelloWorldMessage helloWorldMessage,        // @Payload will tell Spring Framework to expect a message with payload of type HelloWorldMessage so it can deserialize it. This actually the only method parameter we really need. The rest is for demonstration that you can capture the message headers and message properties!
                       @Headers MessageHeaders headers, Message message) {   // @Headers will tell Spring Framework to get the message headers
/*
        log.info("I received a message!!!");
        log.info("helloWorldMessage = " + helloWorldMessage);
*/

        //throw new RuntimeException("KABOOM!");
    }

    @JmsListener(destination = JmsConfig.MY_SEND_RECEIVE_QUEUE_NAME)
    public void listenForHello(@Payload HelloWorldMessage helloWorldMessage,
                       @Headers MessageHeaders headers, Message message) throws JMSException {

        HelloWorldMessage worldMessage = HelloWorldMessage.builder()
                .id(UUID.randomUUID())
                .message("World!")
                .build();

        jmsTemplate.convertAndSend(message.getJMSReplyTo(), worldMessage); // getJMSReplyTo() will make JMS generate an 'internal' temporary queue for you and set a reference to it in the replyTo property of the reply message. You listen for an original hello message and in response you reply with a World message to that temporary queue. This reply is automagically captured when sending the original message and you can assign it to a Message variable 'receivedMessage'.
    }
}
