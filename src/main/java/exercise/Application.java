package exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.commerce.category.CategoryRepository;
import com.commerce.product.ProductRepository;

@EnableMongoRepositories(basePackageClasses={ProductRepository.class, CategoryRepository.class})
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
