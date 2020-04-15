package app.restmvc.controllers;

import app.restmvc.controllers.api.UsersController;
import app.restmvc.dto.UserDto;
import app.restmvc.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.HashSet;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UsersControllerTest {

    private static final String NAME = "name";
    private static final String LAST_NAME = "last name";
    private static final long ID = 1L;

    @InjectMocks
    private UsersController usersController;

    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(usersController).build();
    }


    @Test
    public void getAllUsers() throws Exception {
        //when
        when(userService.getAllUsers()).thenReturn(Arrays.asList(new UserDto(), new UserDto(), new UserDto()));

        //then
        mockMvc.perform(get("/api/v1/users/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.users", hasSize(3)));
    }

    @Test
    public void getByFirstName() throws Exception {
        //when
        when(userService.getUserByFirstName(NAME)).thenReturn(new UserDto(ID, NAME, LAST_NAME, new HashSet<>()));

        //then
        mockMvc.perform(get("/api/v1/users/" + NAME).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName", equalTo(NAME)))
                .andExpect(jsonPath("$.userLastName", equalTo(LAST_NAME)));
    }

}