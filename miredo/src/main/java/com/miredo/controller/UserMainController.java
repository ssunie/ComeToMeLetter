package com.miredo.controller;

import java.util.List;
import java.util.Map;

//import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.miredo.service.UserMainService;

@RestController
public class UserMainController {
	
	//@Resource
	private UserMainService usermainService;
	
	@RequestMapping(value="memMain")
	public ModelAndView AllListView(Map<String, Object> map) throws Exception{
		ModelAndView mav = new ModelAndView();
		
		List<Map<String, Object>> AllList = usermainService.SelectAllList();
		        
        mav.addObject("Alllist", AllList);
        mav.setViewName("memMain");
        return mav;
		
	}

}
