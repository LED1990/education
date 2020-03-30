package app.restmvc.controllers.api;

import app.restmvc.dto.CategoryDto;
import app.restmvc.dto.CategoryListDto;
import app.restmvc.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("all")
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDto getAllCategories() {
        return new CategoryListDto(categoryService.getAllCategories());
    }

    @GetMapping("{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto getByName(@PathVariable String name){
        return categoryService.getByName(name);
    }

}
