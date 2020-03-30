package app.bootstrap;

import db.restmvc.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import model.restmvc.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Slf4j
@Component
public class CategoryDataInit implements CommandLineRunner {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryDataInit(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    @Transactional
    public void run(String... args) throws Exception {
        log.info("Bootstrapping categories data");

        Category categoryDto = new Category("Best film", 10, "BF");
        Category categoryDto2 = new Category("Best music", 5, "BM");
        Category categoryDto3 = new Category("Best actor", 1, "BA");

        categoryRepository.saveAll(Arrays.asList(categoryDto, categoryDto2, categoryDto3));
    }
}
