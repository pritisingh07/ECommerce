package com.commerce.product;

import config.MessageConstants;
import com.commerce.price.CurrencyConverter;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GenericProductRepository {

	@Autowired
	ProductRepository repository;
	
	CurrencyConverter currencyConverter;

	public Product getProductByProductId(String id) {
		return repository.findByProductId(id);

	}
	
	public List<Product> getAllProducts() {
		return repository.findAll();
	}
	
	public List<Product> getProductsByCategoryId(String categoryId) {
		return repository.findByCategoryId(categoryId);
	}

	public String saveProduct(Product product) {

		if (product == null) {
			return MessageConstants.BAD_PAYLOAD;
		}
		
		Map<String, Double> amountInSupportedCurrencies =
				currencyConverter.calculatePriceInOtherCurrencies(product.getPrice());
		
		product.getPrice().put("amountInSupportedCurrencies", (Object) amountInSupportedCurrencies);
		
		Product savedProduct = repository.save(product);
		return savedProduct.getProductId();

	}

	public String updateProduct(String id, Product product) {

		if (product == null) {
			return MessageConstants.BAD_PAYLOAD;
		}
		
			Product productInDb = repository.findByProductId(product.getProductId());
			product.setId(productInDb.getId());

			Product productSaved = repository.save(product);

			return productSaved.getProductId();
		
	}

	public String deleteProductById(String id) {

		Product product = repository.findByProductId(id);
		if (product != null) {
			repository.delete(product);

			return MessageConstants.PRODUCT_DELETED;
		} else {
			return MessageConstants.PRODUCT_NOT_PRESENT;
		}
	}

	@Autowired
	public void setCurrencyConverter(CurrencyConverter currencyConverter) {
		this.currencyConverter = currencyConverter;
	}
	
	
}
