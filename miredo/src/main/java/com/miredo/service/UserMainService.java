package com.miredo.service;

import java.util.List;
import java.util.Map;

import com.miredo.model.dto.UserDTO;

public interface UserMainService {
	
	public List<Map<String, Object>> SelectAllList() throws Exception;
	
	String findIdByName(String name, String email);

	UserDTO findPwd(UserDTO user);

	int updatePwd(UserDTO user);

	String findPwdById(String id);
	
	

}
