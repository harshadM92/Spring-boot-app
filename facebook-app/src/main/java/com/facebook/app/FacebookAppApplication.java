package com.facebook.app;
/*
 * @Author Harshad
 * 
 * */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.facebook.DAO"})
@EnableJpaRepositories(basePackages = {"com.facebook.repository"})
public class FacebookAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FacebookAppApplication.class, args);
	}
	
	 @Bean
	    public ApplicationContextProvider applicationContextProvider() {
	        return new ApplicationContextProvider();
	  }
}
