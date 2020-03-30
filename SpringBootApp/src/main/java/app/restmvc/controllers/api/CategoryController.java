package app.restmvc.controllers.api;

import app.restmvc.dto.CategoryDto;
import app.restmvc.dto.CategoryListDto;
import app.restmvc.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("all")
    public ResponseEntity<CategoryListDto> getAllCategories() {
        return new ResponseEntity<>(new CategoryListDto(categoryService.getAllCategories()), HttpStatus.OK);
    }

    @GetMapping("{name}")
    public ResponseEntity<CategoryDto> getByName(@PathVariable String name){
        return new ResponseEntity<>(categoryService.getByName(name), HttpStatus.OK);
    }

}
