package app.jms.services;

import model.jms.SimpleJmsMessage;

import java.util.List;
import java.util.Optional;

public interface SimpleJmsMessageService {
    Optional<List<SimpleJmsMessage>> getAllMessages();
}
