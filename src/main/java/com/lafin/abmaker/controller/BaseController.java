package com.lafin.abmaker.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.lafin.abmaker.common.Config;

public class BaseController {
	
	HttpServletRequest request;
	HttpServletResponse response;
	
	public BaseController(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;		
	}
	
	public void init(Model model) {
		model.addAttribute("APPNAME", Config.APPNAME);
		model.addAttribute("VERSION", Config.VERSION);
	}
}
