package com.miredo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.miredo.service.UserMainService;

import lombok.extern.slf4j.Slf4j;

@Slf4j //로그관
@Configuration
@RequestMapping("/user")
public class UserMainController {
	
	private UserMainService usermainService;
	
	//@RequestMapping(value="memMain")
	public ModelAndView AllListView(Map<String, Object> map) throws Exception{
		ModelAndView mav = new ModelAndView();
		
		List<Map<String, Object>> AllList = usermainService.SelectAllList();
		        
        mav.addObject("Alllist", AllList);
        mav.setViewName("memMain");
        return mav;
		
	}
	
	@GetMapping("/login")
	public String userLogin() {
		return "/user/login";
	}
	
	@PostMapping("/login")
	public void loginForm(@RequestParam(required=false) String errorMessage, Model model) {
		model.addAttribute("errorMessage", errorMessage);
	}

}
