package com.java.vergilmoney.api;

import com.java.vergilmoney.api.config.property.VergilMoneyApiProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(VergilMoneyApiProperty.class)
public class VergilmoneyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(VergilmoneyApiApplication.class, args);
	}

}
