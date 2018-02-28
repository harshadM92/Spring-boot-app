package com.facebook.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsService userDetailsService;

	@Autowired
	@Qualifier("tokenRepository")
	private TokenRepository persistentTokenRepository;
	
	  @Override
	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		  auth.authenticationProvider(authenticationProvider());
	  }
	  @Bean
	  public DaoAuthenticationProvider authenticationProvider() {
	      DaoAuthenticationProvider authProvider
	        = new DaoAuthenticationProvider();
	      authProvider.setUserDetailsService(userDetailsService);
	      return authProvider;
	  }
	   
	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http.authorizeRequests().antMatchers("/FirstController/hello/**").permitAll().anyRequest().authenticated()
	    .and()
	    .formLogin().
	    loginPage("/index.jsp").
        loginProcessingUrl("/login").
        usernameParameter("app_username").
        passwordParameter("app_password")
        .and().rememberMe().rememberMeParameter("remember-me")
        .tokenRepository(persistentTokenRepository)
        .userDetailsService(userDetailsService)
	    .and()
	    .csrf().disable();
	    //.rememberMe().rememberMeServices(rememberMeServices());
	  }

/*	  @Bean
	    public AbstractRememberMeServices rememberMeServices() {
	        PersistentTokenBasedRememberMeServices rememberMeServices =
	                new PersistentTokenBasedRememberMeServices("testKeyDRMRS", userDetailsService,tokenRepository);
	        rememberMeServices.setAlwaysRemember(true);
	        rememberMeServices.setCookieName("remember-me");
	        rememberMeServices.setTokenValiditySeconds(1209600);
	        return rememberMeServices;
	    }*/
	}