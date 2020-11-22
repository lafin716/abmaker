package com.lafin.abmaker.controller;

import org.springframework.ui.Model;

import com.lafin.abmaker.common.Config;

public class BaseController {
	
	public void init(Model model) {
		model.addAttribute("APPNAME", Config.APPNAME);
		model.addAttribute("VERSION", Config.VERSION);
	}
}
