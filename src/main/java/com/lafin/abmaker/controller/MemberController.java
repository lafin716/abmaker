package com.lafin.abmaker.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/login/*")
public class LoginController extends BaseController{
	
	// 로거 설정
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping(value="form")
	public String login(Model model) {
		super.init(model);
		
		logger.info("LoginController in !!! ");
		
		return "content/login/form";
	}
	
	@PostMapping(value="form")
	public void login_process(Model model, HttpServletResponse response) throws IOException {
		super.init(model);
		
		logger.info("Login POST?!");
		
		// 양식 페이지로 이동한다.
		response.sendRedirect("/");
	}
	
}
