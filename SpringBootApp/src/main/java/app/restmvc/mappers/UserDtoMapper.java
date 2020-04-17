package app.restmvc.mappers;

import app.restmvc.dto.UserDto;
import model.restmvc.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserDtoMapper {

    UserDtoMapper INSTANCE = Mappers.getMapper(UserDtoMapper.class);

    @Mappings({
            @Mapping(source = "userName", target = "name"),
            @Mapping(source = "userLastName", target = "lastName")
    })
    User userDtoToUser(UserDto userDto);
}
