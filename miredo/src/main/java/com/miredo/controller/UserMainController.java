package com.miredo.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.miredo.model.dto.UserDTO;
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
	
	//아이디 중복 체크
		@ResponseBody
		@PostMapping("/checkId")
		public int checkId(UserDTO user) {
			
			int result = userMainService.checkId(user);

			return result;
		}
	
	//비밀번호찾기
	@GetMapping("/forgotPwd")
	public String forgetPwd() {
		return "/member/forgotPwd";
	}
	
	//새로운비밀번호
	@GetMapping("/newPwd")
	public String newPwdForm() {
		return "/member/newPwd";
	}
	
	//비밀번호찾기후변경
	@ResponseBody
	@PostMapping("/newPwd")
	public int newPwd(UserDTO user) {
		
		String encodePwd = pwd_encoder(user.getPassword());
		user.setPassword(encodePwd);
		
		int result = userMainService.updatePwd(user);
		
		return result;
	}
	
	//비밀번호 변경
	@ResponseBody
	@PostMapping("/updatePwd")
	public int updatePwd(String pwd, String pwd2, String id) {

		int result = 0;
		
		String beforePwd = userMainService.findPwdById(id);
		
		if(passwordEncoder.matches(pwd, beforePwd)){
			
			UserDTO member = new UserDTO();
			String encodePwd = pwd_encoder(pwd2);
			member.setId(id);
			member.setPassword(encodePwd);
			
			log.info("변경한 비밀번호 확인 : {} ", encodePwd);
			
			result = userMainService.updatePwd(member);
			
		} 
		
		return result;
	}
	
	
	// 암호화 메소드
	public static String pwd_encoder(String pwd) {
		
		String encoderPwd = passwordEncoder.encode(pwd);
		
		return encoderPwd;
	}
	
	//비밀번호 찾기 이메일 전송 메소드
	@ResponseBody 
	@PostMapping("/authPwd")
	public int findPsssword(@RequestParam("name") String name, 
							@RequestParam("email") String email, 
							@RequestParam("id") String id) throws UnsupportedEncodingException, MessagingException {
		
		int result = 0;
		
		UserDTO user = new UserDTO();
		user.setId(id);
		user.setName(name);
		user.setEmail(email);
		
		UserDTO findMember = userMainService.findPwd(user);
		log.info("findMember 확인 : {}" , findMember);
		
		Random r = new Random();
		int num = r.nextInt(999999); // 랜덤난수설정
		
		if(findMember != null) 
			
			result = sendMail(findMember.getEmail(), num); // 이메일 발송 메소드 호출 
		
		return result;
	}
	
	public int sendMail(String memberEmail, int num) throws UnsupportedEncodingException, MessagingException {
		int result = 0;
				
		String from ="sooandsooand@gmail.com";
		String fromName = "Miredo";
		String to = memberEmail;
		
		String host = "smtp.gmail.com";
		int port = 587;
		
		String smtp_username = "sooandsooand@gmail.com";
		String smtp_password = "miredotest1234";
		 
		String title = "[MIREDO] 비밀번호변경 인증 이메일 입니다";
		String body = String.join(
		        System.getProperty("line.separator"),
		        "<h1>[MIREDO] 비밀번호 찾기(변경)</h1>",
		        System.getProperty("line.separator"),
		        "<h3>인증번호는 " + num + "입니다.</h3>",
		        "<p>비밀번호 찾기 페이지에서 인증번호를 입력해주세요.</p>."
		    );
		
		 Properties props = System.getProperties();
	        props.put("mail.transport.protocol", "smtp");
	        props.put("mail.smtp.port", port); 
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.auth", "true");
	        Session session = Session.getDefaultInstance(props);
	        MimeMessage msg = new MimeMessage(session);
	        
			msg.setFrom(new InternetAddress(from, fromName));
	        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
	        msg.setSubject(title);
	        msg.setContent(body, "text/html;charset=euc-kr");
	        
	        Transport transport = session.getTransport();
	        
	        try {
	            log.info("Sending...");
	            
	            transport.connect(host, smtp_username, smtp_password);
	            transport.sendMessage(msg, msg.getAllRecipients());
	            
	            log.info("Sending success!");
	            result = num; // 결과로 인증 번호를 반환
	            
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            result = -1; //이메일 전송 실패시 결과 -1 반환
	            
	        } finally {
	            transport.close();
	        }
			return result;
	}
	
	//계정 삭제 
	@ResponseBody
	@PostMapping("/deleteUser")
	public Map<String, Object> deleteUser(@RequestParam("id") String id, @RequestParam("active") String active){
		
		int result = userMainService.deleteUser(id, active);
		
		Map<String, Object> map = new HashMap<>();
		
		if(result > 0) {
			map.put("message", "삭제 처리했습니다.");
			
		} else {
			map.put("message", "삭제 실패하였습니다.");
		}
		return map;
	}
	
}
