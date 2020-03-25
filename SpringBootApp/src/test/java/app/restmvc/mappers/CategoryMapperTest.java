package app.restmvc.mappers;

import app.restmvc.dto.CategoryDto;
import model.restmvc.Category;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CategoryMapperTest {

    private static final String CATEGORY_NAME = "best film";
    private static final Integer NUMBER_OF_REWORDS = 10;
    private static final String SYMBOL = "BF";

    private CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    public void categoryDto() throws Exception {
        Category category = new Category(1L, CATEGORY_NAME, NUMBER_OF_REWORDS, SYMBOL);

        CategoryDto categoryDto = categoryMapper.categoryDto(category);
        assertEquals(CATEGORY_NAME, categoryDto.getName());
        assertEquals(SYMBOL, categoryDto.getCode());
        assertEquals(NUMBER_OF_REWORDS, categoryDto.getNumberOfRewords());
    }

}