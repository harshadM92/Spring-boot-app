package com.facebook.app;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
 
    @Override
    public void handle(HttpServletRequest request,HttpServletResponse response, AccessDeniedException exc) 
    		throws IOException, ServletException {
         
        Authentication auth 
          = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
        	System.out.println("User: " + auth.getName() 
            + " attempted to access the protected URL: "
            + request.getRequestURI());
        }
		
		String jsonStringUser = "{\"status\":\"unauthorized\"}";
		response.setContentType("application/json");
		response.getWriter().write(jsonStringUser);
		response.setHeader("X-Powered-By", "");
		response.setHeader("X-Frame-Options", "SAMEORIGIN");
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP  1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.sendRedirect(request.getContextPath() + "/accessDenied");
    }
}