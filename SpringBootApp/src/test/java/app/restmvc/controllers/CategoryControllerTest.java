package app.restmvc.controllers;

import app.restmvc.controllers.api.CategoryController;
import app.restmvc.dto.CategoryDto;
import app.restmvc.exceptions.CustomResourceNotFoundException;
import app.restmvc.services.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CategoryControllerTest {

    private static final String CAT2NAME = "Best music";

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryService categoryService;

    private MockMvc mockMvc;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController)
                .setControllerAdvice(RestResponseEntityExceptionHandler.class)
                .build();
    }

    @Test
    public void getAllCategories() throws Exception {
        //when
        when(categoryService.getAllCategories()).thenReturn(mockDtos());

        //then
        mockMvc.perform(get("/api/v1/categories/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories", hasSize(3)));
    }

    @Test
    public void getByName() throws Exception {
        //when
        when(categoryService.getByName(CAT2NAME)).thenReturn(mockDtos().get(1));

        //then
        mockMvc.perform(get("/api/v1/categories/" + CAT2NAME).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(CAT2NAME)));
    }

    @Test
    public void getByNameNotFound() throws Exception {
        //when
        when(categoryService.getByName(CAT2NAME)).thenThrow(CustomResourceNotFoundException.class);

        //then
        mockMvc.perform(get("/api/v1/categories/" + CAT2NAME).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    //given
    private List<CategoryDto> mockDtos(){
        CategoryDto categoryDto = new CategoryDto("Best film", 10, "BF");
        CategoryDto categoryDto2 = new CategoryDto(CAT2NAME, 10, "BM");
        CategoryDto categoryDto3 = new CategoryDto("Best actor", 10, "BA");

        return Arrays.asList(categoryDto, categoryDto2, categoryDto3);
    }

}