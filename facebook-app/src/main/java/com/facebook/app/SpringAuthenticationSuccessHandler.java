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
	private UserDetailRepository loginUserDetailRepository;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		loginUserDetailRepository=ApplicationContextProvider.getApplicationContext().getBean(UserDetailRepository.class);
		
		String userLogin=request.getParameter(FacebookConstants.SPRING_USERNAME);
		
		UserDetail loginUserDetail=loginUserDetailRepository.findLoginUserDetailByUserName(userLogin);
		
		/*if(loginUserDetail!=null){
			String encryptedUserDetailId=DesEncrypter.encrypt(LoginAppConstants.ENCRYPTION_KEY,LoginAppConstants.ENCRYPTION_INIT_VECTOR, loginUserDetail.getUserId());
			response.setHeader(LoginAppConstants.USER_DETAIL_ID, encryptedUserDetailId);
		}*/
		if(loginUserDetail!=null){
			response.setHeader("userId", loginUserDetail.getUserDetailId());
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