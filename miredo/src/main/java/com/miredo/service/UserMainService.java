package com.miredo.service;

import java.util.List;
import java.util.Map;

public interface UserMainService {
	
	public List<Map<String, Object>> SelectAllList() throws Exception;
	
	String findIdByName(String name, String email);

    MemberDTO findPwd(UserDTO user);

	int updatePassword(UserDTO user);

	String findPwdById(String id);

}
