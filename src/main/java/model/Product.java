package model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Document(collection="allproducts")
public class Product {

	@Id
	private String id;

    @NotNull(message = "ProductId can't be null")
	private String productId;

	@NotNull(message = "Name can't be null")
	private Map<String, String> name;
	private Map<String, String> description;

	private Map<String, Object> details;
	
	private String categoryId;

	@NotNull(message = "Price can't be null")
	private Map<String, Object> price;
	
	public Product() {
		
	}
	
	public Product(String id) {
		this.id = id;
	}

	public Map<String, String> getName() {
		return name;
	}

	public void setName(Map<String, String> name) {
		this.name = name;
	}

	public Map<String, String> getDescription() {
		return description;
	}

	public void setDescription(Map<String, String> description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, Object> getDetails() {
		return details;
	}

	public void setDetails(Map<String, Object> details) {
		this.details = details;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	public Map<String, Object> getPrice() {
		return price;
	}

	public void setPrice(Map<String, Object> price) {
		this.price = price;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	@Override
	public String toString() {
		return "id = " + id + "\n" +
				"name = " + name + "\n" +
				"description = " + description + "\n" +
				"detailsMap = " + details.toString() + "\n" +
				"categoryId = " + categoryId + "\n" + 
				"price = " + price;
	}

}
