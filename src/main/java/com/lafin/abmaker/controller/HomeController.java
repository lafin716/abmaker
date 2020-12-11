package com.lafin.abmaker.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lafin.abmaker.service.FormService;
import com.lafin.abmaker.util.PagingUtil;

@Controller
public class HomeController extends BaseController {

	// 서비스 연결
	@Autowired
	private FormService formService;
	
	// autowired가 아닌 생성자 주입 방식
	public HomeController(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@RequestMapping(value="/")
	public String home(Model model) {
		super.init(model);
		
		PagingUtil pg = new PagingUtil();
		
		pg.createPaging(0, 1);
		
		logger.info(pg.toString());
		
//		List formList = formService.getFormList();
//		
//		model.addAttribute("formList", formList);
//		model.addAttribute("pageTitle", "양식목록");
		
		return "index";
	}
}
