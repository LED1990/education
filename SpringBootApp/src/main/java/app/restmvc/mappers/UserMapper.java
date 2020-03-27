package app.restmvc.mappers;

import app.restmvc.dto.UserDto;
import model.restmvc.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({
            @Mapping(source = "name", target = "userName"),
            @Mapping(source = "lastName", target = "userLastName")
    })
    UserDto userDto(User user);
}
