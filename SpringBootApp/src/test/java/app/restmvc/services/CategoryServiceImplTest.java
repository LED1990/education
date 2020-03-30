package app.restmvc.services;

import app.restmvc.dto.CategoryDto;
import app.restmvc.mappers.CategoryMapper;
import db.restmvc.CategoryRepository;
import model.restmvc.Category;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CategoryServiceImplTest {

    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(categoryRepository, CategoryMapper.INSTANCE);
    }

    @Test
    public void getAllCategories() throws Exception {
        //given
        List<Category> categories = Arrays.asList(new Category(1L, "name1", 1, "code1"),
                new Category(2L, "name3", 2, "code2"),
                new Category(3L, "name3", 3, "code3"));
        when(categoryRepository.findAll()).thenReturn(categories);

        //when
        List<CategoryDto> result = categoryService.getAllCategories();

        //then
        assertEquals(result.size(), categories.size());
    }

    @Test
    public void getByName() throws Exception {
        //given
        Category category = new Category(2L, "name3", 2, "code2");
        when(categoryRepository.findByName(any())).thenReturn(Optional.of(category));

        //when
        CategoryDto result = categoryService.getByName(any());

        //then
        assertEquals(result.getName(), category.getName());
        assertEquals(result.getCode(), category.getCategoryCode());
        assertEquals(result.getNumberOfRewords(), category.getNumberOfRewords());

    }

}