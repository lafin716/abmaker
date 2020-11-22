package com.lafin.abmaker.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lafin.abmaker.service.MainService;

@Controller
public class HomeController extends BaseController {

	// 로거 설정
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 서비스 연결
	private MainService mainService;
	
	// autowired가 아닌 생성자 주입 방식
	public HomeController(MainService mainService) {
		this.mainService = mainService;
	}

	@RequestMapping(value="/")
	public String home(Model model) {
		super.init(model);
		
		System.out.println("Main Start");
		
		List list = mainService.getFormList();
		
		model.addAttribute("list", list);
		model.addAttribute("test", "pjw");
		
		return "index";
	}
}
