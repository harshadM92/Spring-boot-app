package com.facebook.app;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsService userDetailsService;

	@Autowired
	@Qualifier("tokenRepositoryService")
	private PersistentTokenRepository persistentTokenRepository;
	
	@Autowired
	private SpringAuthenticationSuccessHandler springAuthenticationSuccessHandler;
	
	@Autowired
	private SpringAuthenticationFailureHandler springAuthenticationFailureHandler;
	
	@Autowired
	private CustomLogoutSuccessHandler customLogoutSuccessHandler;
	
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
	    http.authorizeRequests().antMatchers("/login**").permitAll()
	    .and()
	    .formLogin().loginPage("/index.jsp")
        .loginProcessingUrl("/login").permitAll()
        .usernameParameter(FacebookConstants.SPRING_USERNAME)
        .passwordParameter(FacebookConstants.SPRING_PASSOWRD)
        .successHandler(springAuthenticationSuccessHandler)
        .failureHandler(springAuthenticationFailureHandler)
        .and()
        .logout().logoutSuccessHandler(customLogoutSuccessHandler)
        .and()
        .authorizeRequests()
	    .anyRequest().authenticated()
        .and()
        .rememberMe().rememberMeServices(rememberMeServices())
	    .and()
	    .csrf().disable();
	  }

	  @Bean
	    public AbstractRememberMeServices rememberMeServices() {
	        PersistentTokenBasedRememberMeServices rememberMeServices =
	                new PersistentTokenBasedRememberMeServices("testKeyDRMRS", userDetailsService,persistentTokenRepository);
	        rememberMeServices.setAlwaysRemember(true);
	        rememberMeServices.setCookieName("remember-me");
	        rememberMeServices.setTokenValiditySeconds(1209600);
	        return rememberMeServices;
	    }
	}