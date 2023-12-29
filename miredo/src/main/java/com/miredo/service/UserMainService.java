package com.miredo.service;

import java.util.List;
import java.util.Map;

import com.miredo.model.dto.UserDTO;

public interface UserMainService {
	
	int checkId(UserDTO user);
	
	public List<Map<String, Object>> SelectAllList() throws Exception;
	
	int deleteUser(String id, String active);
	
	String findIdByName(String name, String email);

	UserDTO findPwd(UserDTO user);

	int updatePwd(UserDTO user);

	String findPwdById(String id);
	
	

}
