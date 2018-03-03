package com.facebook.common;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler{

	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
			//request.getSession().invalidate();
			String jsonStringUser = "{\"logout\":\"Success\"}";
			response.setContentType("application/json");
			response.getWriter().write(jsonStringUser);
			/*response.setHeader("X-Powered-By", "");
			response.setHeader("X-Frame-Options", "SAMEORIGIN");
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP  1.1.
			response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
*/		/*	response.setDateHeader("Expires", 0); // Proxies.
*/		
		
	}

}
