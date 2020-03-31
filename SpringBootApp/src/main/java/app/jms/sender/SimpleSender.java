package app.jms.sender;

import app.jms.config.JmsConfig;
import lombok.extern.slf4j.Slf4j;
import model.jms.SimpleJmsMessage;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class SimpleSender {

    private final JmsTemplate producer;
    private final JmsTemplate publisher;

    @Autowired
    public SimpleSender(@Qualifier("queueJmsTemplate") JmsTemplate producer, @Qualifier("topicJmsTemplate") JmsTemplate publisher) {
        this.producer = producer;
        this.publisher = publisher;
    }

    @Scheduled(fixedRate = 10000)
    public void sendMessage() {
        log.info("sending message to jms queue");
        SimpleJmsMessage simpleJmsMessage = SimpleJmsMessage.builder()
                .id(UUID.randomUUID())
                .message("Simple message for queue")
                .build();

        producer.convertAndSend(JmsConfig.SIMPLE_QUEUE, simpleJmsMessage);
    }

    @Scheduled(fixedRate = 10000)
    public void sendMessageToTopic() {
        log.info("sending message to jms topic");
        SimpleJmsMessage simpleJmsMessage = SimpleJmsMessage.builder()
                .id(UUID.randomUUID())
                .message("Simple message for topic")
                .build();

        publisher.convertAndSend(new ActiveMQTopic(JmsConfig.SIMPLE_TOPIC), simpleJmsMessage);
    }


}
