package com.miredo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.miredo.service.UserMainService;

import lombok.extern.slf4j.Slf4j;

@Slf4j //로그관리
@Configuration
@RequestMapping("/user")
public class UserMainController {
	
	private UserMainService userMainService;
	private static BCryptPasswordEncoder passwordEncoder;
	
	//@RequestMapping(value="memMain")
	public ModelAndView AllListView(Map<String, Object> map) throws Exception{
		ModelAndView mav = new ModelAndView();
		
		List<Map<String, Object>> AllList = userMainService.SelectAllList();
		        
        mav.addObject("Alllist", AllList);
        mav.setViewName("memMain");
        return mav;
		
	}
	
	@Autowired
	public UserMainController(UserMainService userMainService, BCryptPasswordEncoder passwordEncoder) {
		this.userMainService = userMainService;
		UserMainController.passwordEncoder = passwordEncoder;
	}
	
	//로그인
	@GetMapping("/login")
	public String userLogin() {
		return "/user/login";
	}
	
	//로그인실패
	@PostMapping("/login")
	public void loginForm(@RequestParam(required=false) String errorMessage, Model model) {
		model.addAttribute("errorMessage", errorMessage);
	}
	
	//아이디찾기
	@GetMapping("/forgotId")
	public String forgetId() {
		return "/member/forgotId";
	}
	
	//아이디찾기_이름
	@ResponseBody
	@PostMapping("/forgotId")
	public String findIdByName(@RequestParam("name") String name, @RequestParam("email") String email) {
		
		String findId = userMainService.findIdByName(name, email);
		
		String resultId = "";
		
		if(findId !=null) {
			
			int id_length = findId.length(); // 아이디의 총 길이
			
			resultId = findId.substring(0, 3);
			
			String answer = "";
			for (int j = 3; j < id_length; j++){
				answer += "*"; 
			}
			
			resultId += answer;
		}
		
		return resultId;
	}
	
	//비밀번호찾기
	@GetMapping("/forgotPwd")
	public String forgetPwd() {
		return "/member/forgotPwd";
	}
}
