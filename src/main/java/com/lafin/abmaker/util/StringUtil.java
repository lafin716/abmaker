package com.lafin.abmaker.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class StringUtil {
	
	// Integer 변환 
	public static Integer getInt(Object target, int defaultValue) {
		if(ObjectUtils.isEmpty(target)) {
			return defaultValue;
		}else {
			try {
				return Integer.parseInt(target.toString());
			}catch (NumberFormatException ne) {
				return defaultValue;
			}			
		}	
	}
	
	// String 변환
	public static String str(Object target, String defaultValue) {
		if(ObjectUtils.isEmpty(target)) {
			return defaultValue;
		}else {
			return target.toString();
		}	
	}
	
	// Array 내 String 문자열 포함 여부
	public static <T> boolean inArray(T target, List<T> list) {		
		for(int i=0; i<list.size(); i++) {			
			T tmpStr = (T) list.get(i);
			if(tmpStr.equals(target)) {
				return true;
			}
		}
		
		return false;
	}
	
	// 치환문자 
	public static String replace(String code) {
		String result = "";
		Map valueList = new HashMap();
		valueList.put("minus", "지출");
		valueList.put("plus", "수입"); 
		valueList.put("view", "보기");
		
		return valueList.get(code).toString();		
	}
}
