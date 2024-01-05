package com.miredo.model.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserImpl extends User {
	
	private String id; //회원아이디
	private String password; //회원비밀번호
	private String name; //회원명
	private String email; //회원이메일주소
	private String active; //회원상태
	
	public UserImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public void setDetails(UserDTO user) {
		this.id = user.getId();
		this.password = user.getPassword();
		this.name = user.getName();
		this.email = user.getEmail();
		this.active = user.getActive();
	}
	

}
