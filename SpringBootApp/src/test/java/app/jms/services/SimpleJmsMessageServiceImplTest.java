package app.jms.services;

import db.jms.SimpleJmsMessageRepository;
import model.jms.SimpleJmsMessage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class SimpleJmsMessageServiceImplTest {

    @InjectMocks
    private SimpleJmsMessageServiceImpl simpleJmsMessageService;

    @Mock
    private SimpleJmsMessageRepository simpleJmsMessageRepository;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllMessages() throws Exception {
        //when
        when(simpleJmsMessageRepository.findAll()).thenReturn(Arrays.asList(new SimpleJmsMessage(), new SimpleJmsMessage()));
        Optional<List<SimpleJmsMessage>> result = simpleJmsMessageService.getAllMessages();

        //then
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(result.get().size(), 2);
    }

}