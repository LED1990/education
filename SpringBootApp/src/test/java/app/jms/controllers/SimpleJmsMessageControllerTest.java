package app.jms.controllers;

import app.jms.services.SimpleJmsMessageService;
import model.jms.SimpleJmsMessage;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class SimpleJmsMessageControllerTest {

    @InjectMocks
    private SimpleJmsMessageController simpleJmsMessageController;

    @Mock
    private SimpleJmsMessageService simpleJmsMessageService;

    private MockMvc mockMvc;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(simpleJmsMessageController).build();
    }

    @Test
    public void getAllMessages() throws Exception {
        //when
        when(simpleJmsMessageService.getAllMessages()).thenReturn(Optional.of(Arrays.asList(new SimpleJmsMessage(), new SimpleJmsMessage())));

        //then
        mockMvc.perform(get("/api/v1/jms/all"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("msgs"))
                .andExpect(view().name("jms/jms_view"));
    }

}