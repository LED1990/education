package app.cache.services;

import app.restmvc.dto.UserDto;
import app.restmvc.exceptions.CustomResourceNotFoundException;
import db.restmvc.UserRepository;
import model.restmvc.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserCacheServiceImplTest {

    private static final long ID = 1L;
    private static final String NAME = "name";
    @InjectMocks
    private UserCacheServiceImpl userCacheService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getUserByIdCacheable() throws Exception {
        //given
        User user = new User();
        user.setId(ID);
        user.setName(NAME);

        //when
        when(userRepository.findById(ID)).thenReturn(Optional.of(user));

        UserDto userDto = userCacheService.getUserByIdCacheable(ID);

        //then
        Assert.assertTrue(userDto.getId() == ID);
        Assert.assertTrue(userDto.getUserName().equals(NAME));
    }

    @Test(expected = CustomResourceNotFoundException.class)
    public void getUserByIdCacheableShouldThrowNotFound() {
        //when
        when(userRepository.findById(ID)).thenReturn(Optional.empty());

        userCacheService.getUserByIdCacheable(ID);

    }

    @Test
    public void getUserByIdCachePut() throws Exception {
        //given
        User user = new User();
        user.setId(ID);
        user.setName(NAME);

        //when
        when(userRepository.findById(ID)).thenReturn(Optional.of(user));

        UserDto userDto = userCacheService.getUserByIdCachePut(ID);

        //then
        Assert.assertTrue(userDto.getId() == ID);
        Assert.assertTrue(userDto.getUserName().equals(NAME));
    }

    @Test(expected = CustomResourceNotFoundException.class)
    public void getUserByIdCachePutShouldThrowNotFound() throws Exception {
        //when
        when(userRepository.findById(ID)).thenReturn(Optional.empty());

        userCacheService.getUserByIdCachePut(ID);
    }

    @Test
    public void saveUserAndEvictCache() throws Exception {
        //given
        UserDto userDto = new UserDto();
        userDto.setUserName(NAME);
        userDto.setId(ID);

        //when
        when(userRepository.save(any())).thenReturn(new User());

        long id = userCacheService.saveUserAndEvictCache(userDto);

        //then
        Assert.assertTrue(id == ID);
    }

}