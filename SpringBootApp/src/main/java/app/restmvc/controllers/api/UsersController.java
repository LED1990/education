package app.restmvc.controllers.api;

import app.restmvc.dto.UserDto;
import app.restmvc.dto.UsersListDto;
import app.restmvc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("all")
    @ResponseStatus(HttpStatus.OK)
    public UsersListDto getAllUsers() {
        return new UsersListDto(userService.getAllUsers());
    }

    @GetMapping("{firstName}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getByFirstName(@PathVariable String firstName) {
        return userService.getUserByFirstName(firstName);
    }
}
