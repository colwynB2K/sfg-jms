package guru.springframework.sfgjms.listener;

import guru.springframework.sfgjms.config.JmsConfig;
import guru.springframework.sfgjms.model.HelloWorldMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.Message;                                                   // Be careful to import the correct Message class!!!

@Component
@Slf4j
public class HelloMessageListener {

    @JmsListener(destination = JmsConfig.MY_QUEUE_NAME)                     // Listen to "my-hello-world" queue for messages
    public void listen(@Payload HelloWorldMessage helloWorldMessage,        // @Payload will tell Spring Framework to expect a message with payload of type HelloWorldMessage so it can deserialize it. This actually the only method parameter we really need. The rest is for demonstration that you can capture the message headers and message properties!
                       @Headers MessageHeaders headers, Message message) {   // @Headers will tell Spring Framework to get the message headers
        log.info("I received a message!!!");
        log.info("helloWorldMessage = " + helloWorldMessage);

        //throw new RuntimeException("KABOOM!");
    }
}
