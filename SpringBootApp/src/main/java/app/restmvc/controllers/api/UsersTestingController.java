package app.restmvc.controllers.api;

import app.restmvc.dto.UserDto;
import app.restmvc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/test/users")
public class UsersTestingController {

    private UserService userService;

    @Autowired
    public UsersTestingController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/N1problem")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getAllUsers(@RequestParam String name) {
        return userService.getUserByFirstName(name);
    }

    @GetMapping("/N1problem/jpql")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getAllUsersWithJpql(@RequestParam String name) {
        return userService.getUserByWithJpql(name);
    }

    @GetMapping("/N1problem/graph")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getAllUsersWithGraph(@RequestParam String lastName) {
        return userService.getUserByWithGraph(lastName);
    }
}
