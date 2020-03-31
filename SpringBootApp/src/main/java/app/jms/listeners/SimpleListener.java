package app.jms.listeners;

import app.jms.config.JmsConfig;
import db.jms.SimpleJmsMessageRepository;
import lombok.extern.slf4j.Slf4j;
import model.jms.SimpleJmsMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SimpleListener {

    private SimpleJmsMessageRepository simpleJmsMessageRepository;

    @Autowired
    public SimpleListener(SimpleJmsMessageRepository simpleJmsMessageRepository) {
        this.simpleJmsMessageRepository = simpleJmsMessageRepository;
    }

    @JmsListener(destination = JmsConfig.SIMPLE_QUEUE)
    public void listenQueue(@Payload SimpleJmsMessage simpleJmsMessage,
                       @Headers MessageHeaders headers,
                       Message message) {
        log.info("Receiving message from queue: " + message);
        simpleJmsMessageRepository.save(simpleJmsMessage);
    }

    @JmsListener(destination = JmsConfig.SIMPLE_TOPIC, containerFactory = "jmsTopicListenerContainerFactory")
    public void listenTopic(@Payload SimpleJmsMessage simpleJmsMessage,
                       @Headers MessageHeaders headers,
                       Message message) {
        log.info("Receiving message from topic (listener 1): " + message);
        simpleJmsMessageRepository.save(simpleJmsMessage);
    }

    @JmsListener(destination = JmsConfig.SIMPLE_TOPIC, containerFactory = "jmsTopicListenerContainerFactory")
    public void listenTopic2(@Payload SimpleJmsMessage simpleJmsMessage,
                            @Headers MessageHeaders headers,
                            Message message) {
        log.info("Receiving message from topic (listener 2): " + message);
        simpleJmsMessageRepository.save(simpleJmsMessage);
    }
}
