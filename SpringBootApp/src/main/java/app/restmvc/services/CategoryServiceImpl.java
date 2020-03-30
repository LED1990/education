package app.restmvc.services;

import app.restmvc.dto.CategoryDto;
import app.restmvc.exceptions.CustomResourceNotFoundException;
import app.restmvc.mappers.CategoryMapper;
import db.restmvc.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::categoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getByName(String name) {
        return categoryRepository.findByName(name).map(categoryMapper::categoryDto)
                .orElseThrow(CustomResourceNotFoundException::new);
    }
}
