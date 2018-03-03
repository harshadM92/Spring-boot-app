package com.facebook.app;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.facebook.DAO.UserDetail;
import com.facebook.repository.UserDetailRepository;

@Component
public class SpringAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	@Autowired
	private UserDetailRepository userDetailRepository;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		userDetailRepository=ApplicationContextProvider.getApplicationContext().getBean(UserDetailRepository.class);
		
		String userLogin=request.getParameter(FacebookConstants.SPRING_USERNAME);
		
		UserDetail userDetail=userDetailRepository.findUserDetailByUserName(userLogin);
		
		if(userDetail!=null){
			String encryptedUserDetailId=DesEncrypter.encrypt(FacebookConstants.ENCRYPTION_KEY,FacebookConstants.ENCRYPTION_INIT_VECTOR, userDetail.getUserDetailId());
			response.setHeader("userId", encryptedUserDetailId);
		}
		response.setContentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE);
		response.getWriter().write("{\"status\":\"Success\"}");		
		response.setHeader("X-Powered-By", "");
		response.setHeader("X-Frame-Options", "SAMEORIGIN");
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		
	}

}