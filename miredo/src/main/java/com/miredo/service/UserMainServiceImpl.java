package com.miredo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miredo.model.dto.UserDTO;
import com.miredo.model.mapper.UserMainMapper;

@Service
public class UserMainServiceImpl implements UserMainService {
	
	@Autowired
	UserMainMapper userMainMapper;
	
	@Autowired
	public List<Map<String, Object>> SelectAllList() throws Exception {
		
		return userMainMapper.SelectAllList();
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
