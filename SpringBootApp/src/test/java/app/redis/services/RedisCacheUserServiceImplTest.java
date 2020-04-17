package app.redis.services;

import app.restmvc.dto.AccountDto;
import app.restmvc.dto.UserDto;
import db.restmvc.UserRepository;
import model.restmvc.Account;
import model.restmvc.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class RedisCacheUserServiceImplTest {

    private static final String USER_NAME = "name";
    private static final String USER_LAST_NAME = "last name";
    private static final String PASSWORD = "PASS";
    private static final long ID = 1L;
    private static final int ACC_NUMBER = 12345678;
    private static final String PROVIDER = "provider";
    private static final String DB_NUMBER = "db number";

    @InjectMocks
    private RedisCacheUserServiceImpl redisCacheUserService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveChangesAndClearCacheNewUser() throws Exception {
        //given
        UserDto newUserDto = new UserDto(null, USER_NAME, USER_LAST_NAME, new HashSet<>());
        User newUser = new User(ID, USER_NAME, USER_LAST_NAME, new HashSet<>(), PASSWORD);

        //when
        when(userRepository.save(any())).thenReturn(newUser);

        redisCacheUserService.saveChangesAndClearCache(newUserDto);

        //then
        verify(userRepository, never()).findById(any());
    }

    @Test(expected = RuntimeException.class)
    public void saveChangesAndClearCacheShouldThrowException() throws Exception {
        //given
        UserDto newUserDto = new UserDto(ID, USER_NAME, USER_LAST_NAME, new HashSet<>());
        User newUser = new User(ID, USER_NAME, USER_LAST_NAME, new HashSet<>(), PASSWORD);

        //when
        when(userRepository.save(any())).thenReturn(newUser);
        when(userRepository.findById(any())).thenReturn(Optional.empty());

        redisCacheUserService.saveChangesAndClearCache(newUserDto);

        //then
        verify(userRepository, never()).findById(any());
        verify(userRepository, never()).save(any());
    }

    @Test
    public void saveChangesAndClearCacheUpdateUser() throws Exception {
        //given
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        AccountDto userAccDto = new AccountDto(ID, PROVIDER, ACC_NUMBER);
        Account userDbAcc = Account.builder().id(ID).number(DB_NUMBER).build();
        UserDto newUserDto = new UserDto(ID, USER_NAME, USER_LAST_NAME, new HashSet<>(Collections.singletonList(userAccDto)));
        String fromDb = "fromDb";
        User userFromDb = new User(ID, USER_NAME + fromDb, USER_LAST_NAME + fromDb, new HashSet<>(Collections.singletonList(userDbAcc)), PASSWORD + fromDb);

        //when
        when(userRepository.save(userArgumentCaptor.capture())).thenReturn(new User());
        when(userRepository.findById(any())).thenReturn(Optional.of(userFromDb));

        redisCacheUserService.saveChangesAndClearCache(newUserDto);

        //then
        verify(userRepository, times(1)).save(any());

        Assert.assertTrue(userArgumentCaptor.getValue().getId() == ID);
        Assert.assertTrue(userArgumentCaptor.getValue().getName().equals(USER_NAME));
        Assert.assertTrue(userArgumentCaptor.getValue().getLastName().equals(USER_LAST_NAME));
        Assert.assertTrue(userArgumentCaptor.getValue().getAccounts().size() == 1);
        Assert.assertTrue(userArgumentCaptor.getValue().getPassword().equals(PASSWORD + fromDb));
        userArgumentCaptor.getValue().getAccounts().forEach(account -> {
            Assert.assertTrue(account.getAccNumber().equals(ACC_NUMBER));
            Assert.assertTrue(account.getProvider().equals(PROVIDER));
            Assert.assertTrue(account.getNumber().equals(DB_NUMBER));
            Assert.assertTrue(account.getUser() != null);
        });
    }

}