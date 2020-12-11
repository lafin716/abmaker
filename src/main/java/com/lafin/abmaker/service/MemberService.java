package com.lafin.abmaker.service;

import java.util.Map;

import com.lafin.abmaker.dto.UserDto;

public interface MemberService {
	
	public Map login(String user_email, String user_pw);
	public Map join(UserDto param);
	public Map findID(UserDto param);
	public Map findPW(UserDto param);
	public Map checkDuplicateByEmail(String user_email);
}
