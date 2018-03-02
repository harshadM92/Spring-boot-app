package com.facebook.app;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class SpringAuthenticationFailureHandler implements AuthenticationFailureHandler{

	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		String jsonStringUser = "{\"status\":\"Error\"}";
		response.setContentType("application/json");
		response.getWriter().write(jsonStringUser);
		response.setHeader("X-Powered-By", "");
		response.setHeader("X-Frame-Options", "SAMEORIGIN");
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP  1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
	}

}