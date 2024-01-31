package com.miredo.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {
	
//	USER_ID VARCHAR2(100),
//	USER_PASSWORD VARCHAR2(500),
//	USER_NAME VARCHAR2(50),
//	EMAIL VARCHAR2(500),
//	ACTIVE_YN VARCHAR2(10)
	
	private String id; //회원아이디
	private String password; //회원비밀번호
	private String name; //회원명
	private String email; //회원이메일주소
	private String active; //회원상태

}
