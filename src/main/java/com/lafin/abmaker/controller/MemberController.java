package com.lafin.abmaker.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lafin.abmaker.dto.UserDto;
import com.lafin.abmaker.service.MemberService;

@Controller
@RequestMapping(value="/member/*")
public class MemberController extends BaseController {
	
	// 회원관련 서비스
	@Autowired
	private MemberService memberService;
	
	// 공통 폴더
	private final String folder = "content/member/";
	
	public MemberController(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	// 로거 설정
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping(value="login")
	public String login(Model model) {
		super.init(model);
		
		logger.info("LoginController in !!! ");
		
		return folder + "login";
	}
	
	@PostMapping(value="login")
	public void login_process(Model model, UserDto userDto) throws IOException{
		super.init(model);
		
		logger.info("Login POST?!");
		
		logger.info("user_id : " + userDto.toString());
		
		// 양식 페이지로 이동한다.
		response.sendRedirect("/");
	}
	
	
	@GetMapping(value="join")
	public String join(Model model) {
		super.init(model);
		
		logger.info("Join In");
		
		return folder + "join";
	}
	
	@PostMapping(value="join")
	public void join_process(Model model, UserDto userDto) throws IOException{
		super.init(model);
		
		logger.info("Join In");
		Map result = memberService.join(userDto);
		
		logger.info("join result :: " + result.get("code").toString());
		logger.info("join msg :: " + result.get("msg").toString());
		
		response.sendRedirect("/");
	}
	
	@GetMapping(value="findid")
	public String findID(Model model) {
		return folder + "findId";
	}
	
	
	@PostMapping(value="check-id-count")
	public @ResponseBody Map<String, Object> checkIdCount(Model model, String user_id) {
		logger.info("Check In");
		Map result = memberService.checkDuplicate(user_id);
		return result;
	}
	
}
