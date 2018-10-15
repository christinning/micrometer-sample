package uk.christinning.springsample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class SpringSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSampleApplication.class, args);
    }
}
