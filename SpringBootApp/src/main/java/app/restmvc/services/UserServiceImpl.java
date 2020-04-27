package app.restmvc.services;

import app.restmvc.dto.UserDto;
import app.restmvc.exceptions.CustomResourceNotFoundException;
import app.restmvc.mappers.UserMapper;
import db.restmvc.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;
    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::userDto).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserByFirstName(String firstName) {
        return userRepository.findByName(firstName).map(userMapper::userDto).orElseThrow(CustomResourceNotFoundException::new);
    }

    @Override
    public UserDto getUserByWithJpql(String firstName) {
        return userRepository.findUserByNameJpql(firstName).map(userMapper::userDto).orElseThrow(CustomResourceNotFoundException::new);
    }

    @Override
    public UserDto getUserByWithGraph(String lastName) {
        return userRepository.findByLastName(lastName).map(userMapper::userDto).orElseThrow(CustomResourceNotFoundException::new);
    }
}
