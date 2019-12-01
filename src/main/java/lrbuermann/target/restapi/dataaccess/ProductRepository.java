package lrbuermann.target.restapi.dataaccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lrbuermann.target.restapi.beans.Price;
import lrbuermann.target.restapi.beans.Product;

@Component("productRepo")
public class ProductRepository {
	
	@Autowired
	private RestTemplateBuilder restTemplateBuilder;
	
	@Autowired
	private PriceRepository priceRepo;
	
	public Product findById(Long id) {	
		RestTemplate restTemplate = this.restTemplateBuilder.build();
		String url = "https://redsky.target.com/v2/pdp/tcin/" + id + "?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics";
		Product p = restTemplate.getForObject(url, Product.class);
		
		Price price = this.priceRepo.findById(id);
		p.setPrice(price);
		
		return p;
	}

}
