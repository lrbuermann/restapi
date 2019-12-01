package lrbuermann.target.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lrbuermann.target.restapi.beans.Product;
import lrbuermann.target.restapi.dataaccess.PriceRepository;
import lrbuermann.target.restapi.dataaccess.ProductRepository;

@RestController
public class ProductController {
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private PriceRepository priceRepo;
	
	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
	public Product findById(@PathVariable Long id) {
		Product p = this.productRepo.findById(id);
		return p;
	}
	
	@RequestMapping("/products/price/createTable")
	public void createTable() {
		this.priceRepo.createTable();
	}
	
	@RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Product> setCurrentPrice(@PathVariable long id, @RequestBody Product product) {
		this.priceRepo.save(product.getPrice());
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
}
