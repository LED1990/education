package app.redis.controllers;

import app.redis.services.RedisCacheUserService;
import app.restmvc.dto.UserDto;
import model.restmvc.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/redis/users")
public class UsersRedisController {

    private RedisCacheUserService redisCacheUserService;

    @Autowired
    public UsersRedisController(RedisCacheUserService redisCacheUserService) {
        this.redisCacheUserService = redisCacheUserService;
    }

    @GetMapping("/cacheable/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getWithCacheable(@PathVariable long id) {
        return redisCacheUserService.cacheUserWithCachable(id);
    }

    @GetMapping("/cacheput/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getWithCachePut(@PathVariable long id) {
        return redisCacheUserService.cacheUserWithCachePut(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public User getWithCachePut(@RequestBody UserDto userDto) {
        return redisCacheUserService.saveChangesAndClearCache(userDto);
    }
}
