package exercise;

import config.MessageConstants;
import com.commerce.product.GenericProductRepository;
import io.swagger.annotations.Api;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(basePath = "/products", value = "Product", description = "Operations with products", produces = "application/json")
@RestController
public class ProductRestController {

	GenericProductRepository genericProductRepository;

	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProductByProductId(@RequestParam(value = "id") final String id) {
		
		Product product = genericProductRepository.getProductByProductId(id);
		
		if (product == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = genericProductRepository.getAllProducts();
		
		if (products.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@GetMapping("/products/{categoryId}")
	public ResponseEntity<List<Product>> getProductsByCategoryId(@RequestParam(value = "categoryId") final String categoryId) {
		
		List<Product> products = genericProductRepository.getProductsByCategoryId(categoryId);
		
		if (products.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		
	}

	@PostMapping("/products")
	public ResponseEntity<String> postProduct(@RequestBody @Valid Product product) {

		String response = genericProductRepository.saveProduct(product);
		
		if (MessageConstants.BAD_PAYLOAD.equals(response)) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<String>(response, HttpStatus.CREATED);
	}

	@PutMapping("/products/{id}")
	public ResponseEntity<String> updateProuct(@RequestParam(value = "id") final String id, @RequestBody @Valid Product product) {

		String response = genericProductRepository.updateProduct(id, product);
		
		if (MessageConstants.BAD_PAYLOAD.equals(response)) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<String> deleteProductById(@RequestParam(value = "id") final String id) {

		String response = genericProductRepository.deleteProductById(id);
		
		if (MessageConstants.PRODUCT_NOT_PRESENT.equals(response)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<String>(response, HttpStatus.OK);

	}

	@Autowired
	public void setGenericProductRepository(GenericProductRepository genericProductRepository) {
		this.genericProductRepository = genericProductRepository;
	}

}
