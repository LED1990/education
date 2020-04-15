package app.restmvc.controllers.api;

import app.cache.services.UserCacheService;
import app.restmvc.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UsersCacheableController {

    private UserCacheService userCacheService;

    @Autowired
    public UsersCacheableController(UserCacheService userCacheService) {
        this.userCacheService = userCacheService;
    }

    @GetMapping("/cacheable/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getWithCacheable(@PathVariable long id) {
        return userCacheService.getUserByIdCacheable(id);
    }

    @GetMapping("/cacheput/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getWithCachePut(@PathVariable long id) {
        return userCacheService.getUserByIdCachePut(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public Long saveOrUpdateUser(@RequestBody UserDto userDto) {
        return userCacheService.saveUserAndEvictCache(userDto);
    }

}
