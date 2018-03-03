package com.facebook.app;
/*
 * @Author Harshad
 * 
 * */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.facebook.common.ApplicationContextProvider;

@SpringBootApplication(scanBasePackages={"com.facebook.**"})
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
	 
	 @Bean
	 public FilterRegistrationBean corsFilterRegistration() {
		 FilterRegistrationBean filterRegistrationBean = 
				 new FilterRegistrationBean(new CORSFilter());
		 filterRegistrationBean.setName("CORS FIlter");
		 filterRegistrationBean.addUrlPatterns("/*");
		 filterRegistrationBean.setOrder(1);
		 return filterRegistrationBean;
	 }
}
