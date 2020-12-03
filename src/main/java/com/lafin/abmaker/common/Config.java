package com.lafin.abmaker.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.lafin.abmaker.interceptor.LoginInterceptor;

@Configuration
public class Config extends WebMvcConfigurerAdapter{
	
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
	List<String> exceptList;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		// TODO Auto-generated method stub
		registry.addInterceptor(loginInterceptor)
				.addPathPatterns("/*")
				.excludePathPatterns(exceptList);
	}
	
	
	
}
