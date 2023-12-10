package com.miredo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.miredo.model.mapper.UserMainMapper;

public class CustomerUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserMainMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		CustomUserDetails user = mapper.loginID(username);
		if(username==null) {
			throw new UsernameNotFoundException(username);
		}
		
		System.out.println("CustomUserDetailsService 들어옴~~~~~");
		
		return user;
		
	}
	

}
