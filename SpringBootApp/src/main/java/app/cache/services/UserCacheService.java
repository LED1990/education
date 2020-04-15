package app.cache.services;

import app.restmvc.dto.UserDto;

public interface UserCacheService {
    UserDto getUserByIdCacheable(long id);
    UserDto getUserByIdCachePut(long id);
    long saveUserAndEvictCache(UserDto userDto);
}
