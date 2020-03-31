package app.jms.services;

import db.jms.SimpleJmsMessageRepository;
import model.jms.SimpleJmsMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SimpleJmsMessageServiceImpl implements SimpleJmsMessageService{

    private SimpleJmsMessageRepository simpleJmsMessageRepository;

    @Autowired
    public SimpleJmsMessageServiceImpl(SimpleJmsMessageRepository simpleJmsMessageRepository) {
        this.simpleJmsMessageRepository = simpleJmsMessageRepository;
    }

    @Override
    public Optional<List<SimpleJmsMessage>> getAllMessages() {
        return Optional.of(simpleJmsMessageRepository.findAll());
    }
}
