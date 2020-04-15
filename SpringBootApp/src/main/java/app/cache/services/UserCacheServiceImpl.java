package app.cache.services;

import app.restmvc.dto.UserDto;
import app.restmvc.exceptions.CustomResourceNotFoundException;
import app.restmvc.mappers.UserDtoMapper;
import app.restmvc.mappers.UserMapper;
import db.restmvc.UserRepository;
import lombok.extern.slf4j.Slf4j;
import model.restmvc.Account;
import model.restmvc.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserCacheServiceImpl implements UserCacheService {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private UserDtoMapper userDtoMapper;

    @Autowired
    public UserCacheServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        userMapper = UserMapper.INSTANCE;
        userDtoMapper = UserDtoMapper.INSTANCE;
    }

    /**
     * Method with @Cacheable will be executed only if there is no value cached!
     * cache configured in ehcache.xml
     *
     * @param id user db id
     * @return user dto
     */
    @Override
    @Cacheable(value = "usersCache", key = "#id")
    public UserDto getUserByIdCacheable(long id) {
        log.info("Executing method annotated by @Cacheable, key {}", id);
        return userRepository.findById(id).map(userMapper::userDto).orElseThrow(CustomResourceNotFoundException::new);
    }

    /**
     * Method with @CachePut is always executed!!! and cache is updated
     * cache configured in ehcache.xml
     *
     * @param id user db id
     * @return user dto
     */
    @Override
    @CachePut(value = "usersCache", key = "#id")
    public UserDto getUserByIdCachePut(long id) {
        log.info("Executing method annotated by @CachePut, key {}", id);
        return userRepository.findById(id).map(userMapper::userDto).orElseThrow(CustomResourceNotFoundException::new);
    }

    @Override
    @CacheEvict(value = "usersCache", key = "#userDto.id", condition = "#userDto.id != null")
    public long saveUserAndEvictCache(UserDto userDto) {
        log.info("Executing method annotated by @CacheEvict, key {}", userDto.getId());
        User user = userDtoMapper.userDtoToUser(userDto);
        if (user.getAccounts() != null && !user.getAccounts().isEmpty()) {
            for (Account acc : user.getAccounts()
                    ) {
                acc.setUser(user);
            }
        }
        userRepository.save(user);
        return user.getId();
    }

}
