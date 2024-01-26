package com.miredo.service;

import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.miredo.model.dto.UserDTO;
import com.miredo.model.dto.UserImpl;
import com.miredo.model.mapper.UserMainMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserMainServiceImpl implements UserMainService {
	
	private UserMainMapper userMainMapper;
	
	
	@Autowired
	public UserMainServiceImpl(UserMainMapper userMainMapper) {
			this.userMainMapper = userMainMapper;
	}
	
	/* 로그인 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDTO user= new UserDTO();
		user.setId(username);
		user = userMainMapper.findUserById(user);
		if(user != null) {
			List<GrantedAuthority> autorities = new ArrayList();
			return new User(user.getId(), user.getPassword(), authorities);
			}
		
			return null;
		}
		
//		UserDTO user = userMainMapper.findUserById(username);
//		/* null 값이 없게 하기 위해 조회 된 값이 없을 시 ß빈 객체 */
//		if(user == null) user = new UserDTO();
//		
//		log.info("로그인 유저 : {}", user);
//		
//		/* 권한 리스트 */
//		List<GrantedAuthority> authorities = new ArrayList<>();
//		
//		
//		UserImpl member = new UserImpl(user.getId(), user.getPassword(), authorities);
//		member.setDetails(user);
//		
//		return member;
		
//	}
	
//	/* 아이디 찾기 */
//	@Override
//	public String findUserById(String name) {
//		return userMainMapper.findU(name, email);
//	}
	

	/* 아이디 찾기 */
	@Override
	public String findIdByName(String name, String email) {
		return userMainMapper.findIdByName(name, email);
	}
	
	
	/* 아이디 중복 체크 */
	@Override
	public int checkId(UserDTO user) {
		return userMainMapper.checkId(user);
	}

	
	/* 비밀번호 찾기 - 가입된 회원 조회 */
	@Override
	public UserDTO findPwd(UserDTO user) {
		return userMainMapper.findPwd(user);
	}

	/* 비밀번호 변경 */
	@Override
	public int updatePwd(UserDTO user) {
		return userMainMapper.updatePwd(user);
	}

	/* 현재 비밀번호 확인 */
	@Override
	public String findPwdById(String id) {
		return userMainMapper.findPwdById(id);
	};
	
	/* 회원가입 */
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.SERIALIZABLE, rollbackFor = {Exception.class})
	@Override
	public int insertUser(UserDTO user){
		
		/* 회원 등록 */
		int userResult = userMainMapper.insertUser(user);
		
		int result = 0;
		
		if(userResult > 0) {
			result = 1;
		}

		return result;
		
	}
	
	/* 회원 정보 수정 */
	@Override
	public int updateUser(UserDTO user) {
		return userMainMapper.updateUser(user);
	}
	
	/* 회원 삭제 */
	@Override
	public int deleteUser(String id, String active) {
		return userMainMapper.deleteUser(id, active);
	}
	

}
