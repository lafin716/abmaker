package com.lafin.abmaker.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsUtil {
	
	public static final String PARENT = "parent";
	public static final String OPENER = "opener";
	public static final String SELF = "self";
	public static final String BLANK = "blank";
	public static final String TOP = "top";
	
	public static String js(String script) {
		
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("<script>");
		buffer.append(script);
		buffer.append("</script>");		
		
		return buffer.toString();
	}
	
	public static String alert(String msg) {
		
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("alert('" + msg + "');");
		
		return js(buffer.toString());
	}
	
	public static String alert(String msg, String _target) {
		
		if(_target == null || _target.trim().equals("")){
			_target = JsUtil.SELF;
		}
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(_target + ".alert('" + msg + "');");
		
		return js(buffer.toString());
	}
	
	public static String redirect(String url) {
		
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("location.href = '" + url + "';");
		
		return js(buffer.toString());
	}
	
	public static String redirect(String url, String _target) {
		
		if(_target == null || _target.trim().equals("")){
			_target = JsUtil.SELF;
		}
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(_target + ".location.href = '" + url + "';");
		
		return js(buffer.toString());
	}
	
	public static String openDialog(String title, String msg, String _target) {
		
		if(_target == null || _target.trim().equals("")){
			_target = JsUtil.SELF;
		}
		
		StringBuffer buffer = new StringBuffer();
		
		buffer.append(_target + ".openDialog('" + title + "', '" + msg + "', null, null);");
		
		return js(buffer.toString());
	}
	
	public static String openDialog(String title, String msg, String successCallback, String _target) {
		
		if(_target == null || _target.trim().equals("")){
			_target = JsUtil.SELF;
		}
		
		StringBuffer buffer = new StringBuffer();
		
		buffer.append(_target + ".openDialog('" + title + "', '" + msg + "', " + checkKeyword(successCallback) + ", null);");
		
		return js(buffer.toString());
	}
	
	public static String openDialog(String title, String msg, String successCallback, String closeCallback, String _target) {
		
		if(_target == null || _target.trim().equals("")){
			_target = JsUtil.SELF;
		}
		
		StringBuffer buffer = new StringBuffer();
		
		buffer.append(_target + ".openDialog('" + title + "', '" + msg + "', " + checkKeyword(successCallback) + ", " + checkKeyword(closeCallback) + ");");
		
		return js(buffer.toString());
	}
	
	public static String openAlert(String msg, String _target) {
		
		if(_target == null || _target.trim().equals("")){
			_target = JsUtil.SELF;
		}
		
		StringBuffer buffer = new StringBuffer();
		
		buffer.append(_target + ".openAlert('" + msg + "', null);");
		
		return js(buffer.toString());
	}
	
	public static String openAlert(String msg, String callback, String _target) {
		
		if(_target == null || _target.trim().equals("")){
			_target = JsUtil.SELF;
		}
		
		StringBuffer buffer = new StringBuffer();
		
		buffer.append(_target + ".openAlert('" + msg + "', " + checkKeyword(callback) + ");");
		
		return js(buffer.toString());
	}
	
	private static String checkKeyword(String callback) {
		List keywordList = new ArrayList(Arrays.asList("submit", "reload", "submit"));
		String result = "";
		
		if(result == null) {
			result = "null";			
		}else if(StringUtil.inArray(callback, keywordList)) {
			result = "'"+callback+"'";
		}else {
			result = "function(){" + callback + "}";
		}
		
		return result;
	}
	
}
