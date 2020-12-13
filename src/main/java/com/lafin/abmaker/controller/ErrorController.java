package com.lafin.abmaker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lafin.abmaker.util.JsUtil;

@Controller
public class ErrorController {

	@GetMapping(value="/error")
	public void errorpage() {
		
	}
	
	@RequestMapping(value = "/empty")
	public @ResponseBody String empty(Model model, Integer code) {
		String body = "";
		// code 별로 메세지를 띄우고 링크 핸들링
		switch(code) {
			case 100:
				body = JsUtil.alert("양식을 먼저 등록해주세요.", "/form/list", JsUtil.SELF);
				break;
			case 101:
				body = JsUtil.alert("기본 양식이 없습니다. 기본양식을 등록해주세요.", "/form/list", JsUtil.SELF);
				break;
			case 201:
				body = JsUtil.alert("자산이 없습니다. 자산을 등록해주세요.", "/asset/list", JsUtil.SELF);
				break;
		}
		
		return body;
	}
}
