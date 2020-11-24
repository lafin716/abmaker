package com.lafin.abmaker.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/form/*")
public class FormController extends BaseController{
	
	
	@RequestMapping
	public void index(HttpServletResponse response) throws IOException {
		response.sendRedirect("/form/list");
	}
	
	@RequestMapping(value = "/list")
	public String list(Model model) {
		super.init(model);
		
		return "content/form/list";
	}

}
