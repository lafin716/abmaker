package com.lafin.abmaker.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import com.lafin.abmaker.common.Config;
import com.lafin.abmaker.dto.UserDto;

public abstract class BaseController {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	public HttpServletRequest request;
	public HttpServletResponse response;
	public HttpSession session;
	public UserDto userInfo;
	public Map cookie;
	
	public BaseController(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	
	public void init(Model model) {
		this.session = request.getSession();
		
		// 회원정보가 있는 경우 회원정보를 기본정보로 세팅
		if(session.getAttribute("userInfo") != null) {
			userInfo = (UserDto) session.getAttribute("userInfo");
			model.addAttribute("USERNAME", userInfo.getUser_name());
		}
		
		// 앱 이름과 버전을 기본정보로 세팅
		model.addAttribute("APPNAME", Config.APPNAME);
		model.addAttribute("VERSION", Config.VERSION);
		
		// 현재 페이지주소를 기본정보로 세팅
		String requestUri = request.getRequestURI();
		String[] uriSegment = requestUri.split("/");
		
		for(int i=0; i<uriSegment.length; i++) {
			if(!uriSegment[i].equals("")) {
				logger.info("segment"+i + " : " + uriSegment[i]);
				model.addAttribute("segment"+i, uriSegment[i]);
			}
		}
		
		// 쿠키 세팅
		cookie = getAllCookies();
		
	}
	
	public Map getAllCookies() {
		Map result = new HashMap();
		Cookie[] cookies = request.getCookies();

	    for(int i = 0; i < cookies.length; i++) {
	    	result.put(cookies[i].getName(), cookies[i].getValue());
	    }
	    
	    return result;
	}
	
	public void setCookie(String cookieName, String cookieValue) {
		Cookie cookie = new Cookie(cookieName, cookieValue);
		
		// 쿠키는 일주일 간 지속
		cookie.setMaxAge(60 * 60 * 24 * 7);
		cookie.setPath("/");
		
		// 쿠키를 세팅
		response.addCookie(cookie);
	}
	
	public void deleteCookie(String cookieName) {
		Cookie cookie = new Cookie(cookieName, null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		
		// 쿠키를 세팅
		response.addCookie(cookie);
	}
	
	public void clearCookies() {
		Cookie[] cookies = request.getCookies();

	    for(int i = 0; i < cookies.length; i++) {
	    	Cookie cookie = new Cookie(cookies[i].getName(), null);
	    	cookie.setMaxAge(0);
	    	cookie.setPath("/");
	    	response.addCookie(cookie);
	    }
	}
}
