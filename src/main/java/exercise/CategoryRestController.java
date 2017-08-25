package exercise;

import com.commerce.category.GenericCategoryRepository;
import config.MessageConstants;
import io.swagger.annotations.Api;
import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(basePath = "/categories", value = "Category", description = "CRUD on categories", produces = "application/json")
@RestController
public class CategoryRestController {

    GenericCategoryRepository genericCategoryRepository;

    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable(value = "id") final String id) {

        Category category = genericCategoryRepository.getCategoryById(id);
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Category>(category, HttpStatus.OK);

    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = genericCategoryRepository.getAllCategories();
        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
    }

    @PostMapping("categories")
    public ResponseEntity<Map> postCategory(@RequestBody @Valid Category category) {

        String response = genericCategoryRepository.saveCategory(category);
        if (MessageConstants.BAD_PAYLOAD.equals(response)) {
            return new ResponseEntity<Map>(new HashMap<>(),HttpStatus.BAD_REQUEST);
        }
        Map res = new HashMap<String, String>();
        res.put("id",response);
        return new ResponseEntity<Map>(res, HttpStatus.CREATED);
    }

    @PutMapping("categories/{id}")
    public ResponseEntity<Map> updateCategory(@PathVariable(value = "id") final String id, @RequestBody @Valid Category category) {
        String response = genericCategoryRepository.updateCategory(id, category);
        if (MessageConstants.BAD_PAYLOAD.equals(response)) {
            return new ResponseEntity<Map>(new HashMap<>(),HttpStatus.BAD_REQUEST);
        }
        Map res = new HashMap<String, String>();
        res.put("id",response);
        return new ResponseEntity<Map>(res, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("categories/{id}")
    public ResponseEntity<Map> deleteCategoryById(@PathVariable(value = "id") final String id) {
        String response = genericCategoryRepository.deleteCategoryById(id);
        if (MessageConstants.CATEGORY_NOT_PRESENT.equals(response)) {
            return new ResponseEntity<Map>(new HashMap<>(),HttpStatus.NOT_FOUND);
        }
        Map res = new HashMap<String, String>();
        res.put("id",response);
        return new ResponseEntity<Map>(res, HttpStatus.NO_CONTENT);
    }

    @Autowired
    public void setGenericCategoryRepository(GenericCategoryRepository categoryRepository) {
        this.genericCategoryRepository = categoryRepository;
    }
}
