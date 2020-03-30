package app.restmvc.mappers;

import app.restmvc.dto.CategoryDto;
import model.restmvc.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    //use if target value and source has different names
    @Mapping(source = "categoryCode", target = "code")
    CategoryDto categoryDto(Category category);
}
