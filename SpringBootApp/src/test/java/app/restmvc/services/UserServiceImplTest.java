package app.restmvc.services;

import app.restmvc.dto.UserDto;
import app.restmvc.mappers.UserMapper;
import db.restmvc.UserRepository;
import model.restmvc.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    public static final String NAME = "name";
    public static final String LAST_NAME = "last name";
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(userRepository, UserMapper.INSTANCE);
    }

    @Test
    public void getAllUsers() throws Exception {
        //given
        List<User> users = new ArrayList<>();
        users.add(new User("name", "last name", null));
        users.add(new User("name", "last name", null));

        //when
        when(userRepository.findAll()).thenReturn(users);
        List<UserDto> userDtos = userService.getAllUsers();

        //then
        Assert.assertEquals(userDtos.size(), users.size());

    }

    @Test
    public void getUserByFirstName() throws Exception {
        //given
        User user = new User(NAME, LAST_NAME, null);

        //when
        when(userRepository.findByName(NAME)).thenReturn(Optional.of(user));
        UserDto userDto = userService.getUserByFirstName(NAME);

        //then
        Assert.assertEquals(user.getName(), userDto.getUserName());
        Assert.assertEquals(user.getLastName(), userDto.getUserLastName());
    }

}