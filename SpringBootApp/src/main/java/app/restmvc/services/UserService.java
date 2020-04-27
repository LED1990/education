package app.restmvc.services;

import app.restmvc.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();
    UserDto getUserByFirstName(String firstName);
    UserDto getUserByWithJpql(String firstName);
    UserDto getUserByWithGraph(String firstName);
}
