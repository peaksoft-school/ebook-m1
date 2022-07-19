package kg.peaksoft.ebookm1.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {

    public static final AWSCredentials awsCredentials = new BasicAWSCredentials(
            "AKIA6NUGKXQBOKNDLUXK",
            "8ldmGdiIw/XtifjuOmI04vOTJJhpGtAy2t5WX6ql"
    );

    @Bean
    AmazonS3 generateS3Client() {
        return AmazonS3ClientBuilder.standard().withCredentials(
                        new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(Regions.EU_CENTRAL_1).build();
    }
}
