package app.redis.services;

import app.restmvc.dto.UserDto;
import app.restmvc.exceptions.CustomResourceNotFoundException;
import app.restmvc.mappers.UserDtoMapper;
import app.restmvc.mappers.UserMapper;
import db.restmvc.UserRepository;
import lombok.extern.slf4j.Slf4j;
import model.restmvc.Account;
import model.restmvc.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Slf4j
@Service
public class RedisCacheUserServiceImpl implements RedisCacheUserService {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private UserDtoMapper userDtoMapper;


    public RedisCacheUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        userMapper = UserMapper.INSTANCE;
        userDtoMapper = UserDtoMapper.INSTANCE;
    }

    @Override
    @Cacheable(cacheNames = "users", key = "#id")
    public UserDto cacheUserWithCachable(long id) {
        log.info("Caching user with redis @Cacheable, id: {}", id);
        return userRepository.findById(id).map(userMapper::userDto).orElseThrow(CustomResourceNotFoundException::new);
    }

    //@CachePut vs @Cacheable - first one always execute method and updates cache, second execute method only if cache doesn't have key
    @Override
    @CachePut(cacheNames = "users", key = "#id")
    public UserDto cacheUserWithCachePut(long id) {
        log.info("Caching user with redis @CachePut, id: {}", id);
        return userRepository.findById(id).map(userMapper::userDto).orElseThrow(CustomResourceNotFoundException::new);
    }

    @Override
    @CacheEvict(cacheNames = "users", key = "#userDto.id", condition = "#userDto.id != null ")
    public User saveChangesAndClearCache(UserDto userDto) {
        log.info("Updating user with redis and clearing cache, id: {}", userDto.getId());
        User user = userDtoMapper.userDtoToUser(userDto);
        if (user.getId() == null) {//new user
            return userRepository.save(user);
        }

        if (userDto.getAccounts() == null || userDto.getAccounts().isEmpty()) {
            user.setAccounts(new HashSet<>());
        }
        //need to fill missing data (dto doesn't have all data that are present in User class
        Optional<User> userFromDb = userRepository.findById(user.getId());
        if (userFromDb.isPresent()) {
            user.setPassword(userFromDb.get().getPassword());
            Optional<Account> accountFromDb;
            for (Account acc : user.getAccounts()
                    ) {
                acc.setUser(user);
                if (userFromDb.get().getAccounts() != null) {
                    accountFromDb = userFromDb.get().getAccounts().stream().filter(dbAccount -> dbAccount.getId().equals(acc.getId())).findFirst();
                    accountFromDb.ifPresent(dbAccount -> acc.setNumber(dbAccount.getNumber()));
                    accountFromDb = Optional.empty();
                }
            }
        } else {
            throw new RuntimeException("User must be in database");//todo exception handling!
        }

        return userRepository.save(user);
    }
}
