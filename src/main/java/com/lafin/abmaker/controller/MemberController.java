package com.lafin.abmaker.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lafin.abmaker.dto.UserDto;

@Controller
@RequestMapping(value="/member/*")
public class MemberController extends BaseController{
	
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
	public void login_process(Model model, @RequestParam UserDto param) throws IOException {
		super.init(model);
		
		logger.info("Login POST?!");
		
		logger.info("user_id : " + param.toString());
		
		// 양식 페이지로 이동한다.
		response.sendRedirect("/");
	}
	
	
	@GetMapping(value="join")
	public String join(Model model) {
		super.init(model);
		
		logger.info("Join In");
		
		return folder + "join";
	}
	
}
