package com.miredo.user.model.service;


import org.springframework.security.core.userdetails.UserDetailsService;

import com.miredo.user.model.dto.UserDTO;

public interface UserMainService extends UserDetailsService {
	
	//회원 아이디로 조회
	UserDTO findUserById(String name);
	
	// 아이디 찾기
	String findIdByName(String name, String email);
	
	// 아이디 중복 체크
	int checkId(UserDTO user);
	
	//비밀번호 찾기
	UserDTO findPwd(UserDTO user);

	//비밀번호 변경
	int updatePwd(UserDTO user);
	
	//현재 비밀번호 확인
	String findPwdById(String id);
	
	//회원 등록
	int insertUser(UserDTO user);
	
	//회원 정보 수정
	int updateUser(UserDTO user);
	
	//회원 삭제
	int deleteUser(String id, String active);
	

}
