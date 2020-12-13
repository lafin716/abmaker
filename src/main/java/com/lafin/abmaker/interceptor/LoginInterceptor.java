package com.lafin.abmaker.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.lafin.abmaker.common.Config;
import com.lafin.abmaker.dto.UserDto;
import com.lafin.abmaker.util.TokenUtil;

@Component
public class LoginInterceptor implements HandlerInterceptor{
	
	// 로거 설정
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean result = false;
		HttpSession session = request.getSession();
		UserDto userInfo = (UserDto) session.getAttribute("userInfo");
		
		if(ObjectUtils.isEmpty(userInfo)) {
			logger.info( request.getRequestURL().toString() + " :: " + request.getQueryString() + " :: " + request.getHeader("referer") + " :: " + request.getMethod());
			response.sendRedirect("/member/login");
			result = false;
		}else {
			logger.info(userInfo.getUser_email());
			result = true;
		}
		
		return result;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	
	
}
