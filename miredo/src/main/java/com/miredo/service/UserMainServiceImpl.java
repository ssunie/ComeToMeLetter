package com.miredo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.miredo.model.dto.UserDTO;
import com.miredo.model.mapper.UserMainMapper;

@Service
public class UserMainServiceImpl implements UserMainService {
	
	private UserMainMapper userMainMapper;
	
	@Autowired
	public List<Map<String, Object>> SelectAllList() throws Exception {
		
		return userMainMapper.SelectAllList();
	}
	
	@Autowired
	public UserServiceImpl(UserMainMapper userMainMapper) {
		this.userMainMapper = userMainMapper;
	}
	
	/* 로그인 */
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		
		UserDTO user = UserMainMapper.findUserById(name);
		/* null 값이 없게 하기 위해 조회 된 값이 없을 시 빈 객체 */
		if(user == null) user = new UserDTO();
		
		log.info("로그인 유저 : {}", user);
		

		/* User 객체에 담기지 않는 추가 정보를 User 객체를 extends한 UserImpl에 담아서 리턴한다. */
		UserImpl user = new UserImpl(user.getId(), user.getPassword());
		user.setDetails(user);
		
		return user;
		
		
	}
	
	@Override
	public String findIdByName(String name, String email) {
		return userMainMapper.findIdByName(name, email);
	}
	
	/* 비밀번호 찾기 - 가입된 회원 조회*/
	@Override
	public UserDTO findPwd(UserDTO user) {
		return userMainMapper.findPwd(user);
	}

	@Override
	public int updatePwd(UserDTO user) {
		return userMainMapper.updatePwd(user);
	}

	@Override
	public String findPwdById(String id) {
		return userMainMapper.findPwdById(id);
	};
	
	/* 회원 삭제 */
	@Override
	public int deleteUser(String id, String active) {
		return userMainMapper.deleteUser(id, active);
	}
	

}
