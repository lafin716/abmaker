package com.lafin.abmaker.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.lafin.abmaker.interceptor.LoginInterceptor;

@Configuration
public class Config implements WebMvcConfigurer{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 앱 전역 공통 변수
	@Value("${app.info.name}")
	public static String APPNAME;
	@Value("${app.info.version}")
	public static String VERSION;
	@Value("${app.info.mode}")
	public static String MODE;
	
	public static final String DEBUG = "debug";
	public static final String PRODUCTION = "production";
	
	
	// 로그인 인터셉터 추가
	@Autowired
	LoginInterceptor loginInterceptor;
	
	// 인터셉터 제외목록
	@Value("${interceptor.except.list}")
	String[] exceptList;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		// 로그인 체크
		registry.addInterceptor(loginInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns(exceptList);
	}
	
	
	
}
