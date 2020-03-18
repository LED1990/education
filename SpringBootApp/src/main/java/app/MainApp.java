package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @EntityScan needed because entities are in other modules (not in sub packages)
 * @EnableJpaRepositories needed because repo classes are in other module (not subpackage)
 */
@SpringBootApplication (scanBasePackages = {"springData","evaluations", "app"})
@EnableJpaRepositories (basePackages = {"springData"})
@EntityScan(basePackages = {"evaluations"})
public class MainApp {
    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }
}
