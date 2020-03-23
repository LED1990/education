package db.evaluation.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = {"db", "model"})
@EnableJpaRepositories(basePackages = {"db"})
@EntityScan(basePackages = {"model"})
public class DbTestConfig {

}
