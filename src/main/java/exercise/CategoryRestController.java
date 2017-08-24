package exercise;

import config.MessageConstants;
import com.commerce.category.GenericCategoryRepository;
import io.swagger.annotations.Api;
import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(basePath = "/categories", value = "Category", description = "Operations with categories", produces = "application/json")
@RestController
public class CategoryRestController {
	
GenericCategoryRepository genericCategoryRepository;
	
	@GetMapping("/categories/{id}")
	public ResponseEntity<Category> getCategoryById(@RequestParam(value = "id") final String id) {
		
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
	public ResponseEntity<String> postCategory(@RequestBody @Valid Category category) {
		
		String response = genericCategoryRepository.saveCategory(category);
		
		if (MessageConstants.BAD_PAYLOAD.equals(response)) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<String>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("categories/{id}")
	public ResponseEntity<String> updateCategory(@RequestParam(value = "id") final String id, @RequestBody @Valid  Category category) {
		String response = genericCategoryRepository.updateCategory(id, category);
		
		if (MessageConstants.BAD_PAYLOAD.equals(response)) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("categories/{id}")
	public ResponseEntity<String> deleteCategoryById(@RequestParam(value = "id") final String id) {
		String response = genericCategoryRepository.deleteCategoryById(id);
		
		if(MessageConstants.CATEGORY_NOT_PRESENT.equals(response)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@Autowired
	public void setGenericCategoryRepository(GenericCategoryRepository categoryRepository) {
		this.genericCategoryRepository = categoryRepository;
	}
}
