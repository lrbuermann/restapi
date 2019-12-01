package lrbuermann.target.restapi.dataaccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;

import lrbuermann.target.restapi.beans.Price;

@Component("priceRepo")
public class PriceRepository {
	@Autowired
	private AmazonDynamoDB amazonDynamoDB;
	@Autowired
	private DynamoDBMapper dynamoDBMapper;
	
	public void createTable() {
		CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(Price.class);
		tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
		TableUtils.createTableIfNotExists(amazonDynamoDB, tableRequest);
	}
	
	public void save(Price price) {
		dynamoDBMapper.save(price);
	}
	
	public Price findById(Long id) {
		return dynamoDBMapper.load(Price.class, id);
	}
	
}
