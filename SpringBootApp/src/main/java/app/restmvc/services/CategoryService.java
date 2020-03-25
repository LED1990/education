package app.restmvc.services;

import app.restmvc.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getAllCategories();

    CategoryDto getByName(String name);
}
