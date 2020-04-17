package app.redis.services;

import app.restmvc.dto.UserDto;
import model.restmvc.User;

public interface RedisCacheUserService {
    UserDto cacheUserWithCachable(long id);
    UserDto cacheUserWithCachePut(long id);
    User saveChangesAndClearCache(UserDto userDto);
}
