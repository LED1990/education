package app.restmvc.mappers;

import app.restmvc.dto.UserDto;
import model.restmvc.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;

public class UserMapperTest {

    private static final String FIRST_NAME = "first name";
    private static final String LAST_NAME = "last name";

    private UserMapper userMapper = UserMapper.INSTANCE;

    @Test
    public void userDto() throws Exception {
        //given
        User user = new User(FIRST_NAME, LAST_NAME, new HashSet<>());

        //when
        UserDto userDto = userMapper.userDto(user);

        //then
        Assert.assertEquals(userDto.getUserName(), FIRST_NAME);
        Assert.assertEquals(userDto.getUserLastName(), LAST_NAME);
        Assert.assertEquals(userDto.getAccounts(), Collections.emptySet());
    }

}