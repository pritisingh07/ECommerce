package exercise;

import com.commerce.product.GenericProductRepository;
import config.MessageConstants;
import io.swagger.annotations.Api;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(basePath = "/products", value = "Product", description = "CRUD on products", produces = "application/json")
@RestController
public class ProductRestController {

    GenericProductRepository genericProductRepository;

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductByProductId(@PathVariable(value = "id") final String id ) {

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

    @GetMapping("/products/byCategories")
    public ResponseEntity<List<Product>> getProductsByCategoryId(@RequestParam(value = "categoryId") final String categoryId) {

        List<Product> products = genericProductRepository.getProductsByCategoryId(categoryId);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);

    }

    @PostMapping("/products")
    public ResponseEntity<Map> postProduct(@RequestBody @Valid Product product) {

        String response = genericProductRepository.saveProduct(product);
        if (MessageConstants.BAD_PAYLOAD.equals(response)) {
            return new ResponseEntity<Map>(new HashMap<>(),HttpStatus.BAD_REQUEST);
        }
        Map res = new HashMap<String, String>();
        res.put("id",response);
        return new ResponseEntity<Map>(res, HttpStatus.CREATED);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Map> updateProuct(@PathVariable(value = "id") final String id, @RequestBody @Valid Product product) {

        String response = genericProductRepository.updateProduct(id, product);
        if (MessageConstants.BAD_PAYLOAD.equals(response)) {
            return new ResponseEntity<Map>(new HashMap<>(),HttpStatus.BAD_REQUEST);
        }
        Map res = new HashMap<String, String>();
        res.put("id",response);
        return new ResponseEntity<Map>(res, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Map> deleteProductById(@PathVariable(value = "id") final String id) {

        String response = genericProductRepository.deleteProductById(id);
        if (MessageConstants.PRODUCT_NOT_PRESENT.equals(response)) {
            return new ResponseEntity<Map>(new HashMap<>(),HttpStatus.NOT_FOUND);
        }
        Map res = new HashMap<String, String>();
        res.put("id",response);
        return new ResponseEntity<Map>(res, HttpStatus.NO_CONTENT);

    }

    @Autowired
    public void setGenericProductRepository(GenericProductRepository genericProductRepository) {
        this.genericProductRepository = genericProductRepository;
    }

}
