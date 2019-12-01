package lrbuermann.target.restapi.beans;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "CurrentPrice")
public class Price {
	private Long productId;
	private double value;
	private String currencyCode;
	
	public Price() {
		super();
	}

	public Price(Long productId, double value, String currencyCode) {
		super();
		this.productId = productId;
		this.value = value;
		this.currencyCode = currencyCode;
	}
	
	@DynamoDBHashKey(attributeName="productId")
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	@DynamoDBAttribute
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	@DynamoDBAttribute
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
	
}
