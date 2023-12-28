/**
 * 
 */
package com.miredo.model.mapper;

import java.util.*;

import com.miredo.model.dto.UserDTO;
import com.miredo.security.CustomUserDetails;

import org.apache.ibatis.annotations.Mapper;
/**
 * @author 김수현
 * @since 2023.12.01
 *
 */

@Mapper
public interface UserMainMapper {
	
	//select * from Miredo_Table
	public List<Map<String, Object>> SelectAllList() throws Exception;
	
	public CustomUserDetails loginID(String ID);
	
	// 아이디 찾기
	String findIdByName(String name, String email);
	
	//비밀번호 찾기
	UserDTO findPwd(UserDTO user);

	//비밀번호 변경
	int updatePwd(UserDTO user);
	
	//현재 비밀번호 확인
	String findPwdById(String id);

}
