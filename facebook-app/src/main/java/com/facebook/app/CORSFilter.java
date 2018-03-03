package com.facebook.app;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

@Component
public class CORSFilter extends GenericFilterBean implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletResponse httpServletResponse =(HttpServletResponse) response;
		httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
		httpServletResponse.setHeader("Access-Control-Allow-Methods", "*");
		
		httpServletResponse.setHeader("Access-Control-Allow-Headers", "*");
		httpServletResponse.setHeader("Access-Control-Allow-Credentials", "false");
		
		httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
		
		System.out.println("*************** CORS completed *************");
		
		chain.doFilter(request, response);
		
	}

}
