package lrbuermann.target.restapi.beans;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Product {
	private Long id;
	private String name;
	private Price currentPrice;
	
	public Product(Long id, String name, Price price) {
		super();
		this.id = id;
		this.name = name;
		this.currentPrice = price;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Price getPrice() {
		return currentPrice;
	}
	public void setPrice(Price price) {
		this.currentPrice = price;
	}
	
	@JsonProperty("product")
	private void unpackJSON(Map<String, Object> product) {
		Map<String, Object> item = (Map<String, Object>) product.get("item");
		Map<String, Object> productDescription = (Map<String, Object>) item.get("product_description");
		this.setId(Long.parseLong((String) item.get("tcin")));
		this.setName((String) productDescription.get("title"));
	}
}
