package lrbuermann.target.restapi.dataaccess.config;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

@Configuration
@EnableDynamoDBRepositories("com.lbuermann.target.restapi.dataaccess")
public class DynamoDBConfig {
    @Value("${amazon.dynamodb.endpoint}")
    private String dynamoDbEndpoint;

    @Value("${amazon.aws.accessKey}")
    private String awsAccessKey;

    @Value("${amazon.aws.secretKey}")
    private String awsSecretKey;

    @Value("${amazon.aws.region}")
    private String awsRegion;
    
    @Bean
    public AmazonDynamoDB amazonDynamoDB() {

        AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration(
        		new AwsClientBuilder.EndpointConfiguration(dynamoDbEndpoint, awsRegion))
        		.withCredentials(new AWSStaticCredentialsProvider(this.amazonAWSCredentials()))
        		.build(); 

        return amazonDynamoDB;
    }

    private AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(awsAccessKey, awsSecretKey);
    }
    @Bean
    public DynamoDBMapper dynamoDBMapper() {
    	return new DynamoDBMapper(this.amazonDynamoDB());
    }
}
