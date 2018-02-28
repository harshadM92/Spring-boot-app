package com.facebook.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.facebook.DAO.UserDetail;
import com.facebook.repository.UserDetailRepository;

@Service("userDetailsService")
public class UserTokenDetailServiceImpl implements UserDetailsService{

	@Autowired
	private UserDetailRepository loginUserDetailRepository;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserDetail userDetail=null;
		
		//loginUserDetailRepository=ApplicationContextProvider.getApplicationContext().getBean(UserDetailRepository.class);
		
		userDetail=loginUserDetailRepository.findLoginUserDetailByUserName(userName);
		
	
		if(userDetail ==null){
			return null;
		}
		
		return new User(userDetail.getUserName(), userDetail.getPassword(), true, true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList(userDetail.getRole().getRoleName()));
	}

}
