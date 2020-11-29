package com.lafin.abmaker.util;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class StringUtil {
	
	public String str(Object target, String defaultValue) {
		if(ObjectUtils.isEmpty(target)) {
			return defaultValue;
		}else {
			return target.toString();
		}	
	}
}
