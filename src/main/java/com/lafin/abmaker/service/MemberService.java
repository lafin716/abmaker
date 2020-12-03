package com.lafin.abmaker.service;

import java.util.Map;

import com.lafin.abmaker.dto.UserDto;

public interface MemberService {
	
	public Map login(UserDto param);
	public Map join(UserDto param);
	public Map findID(UserDto param);
	public Map findPW(UserDto param);
	public Map checkDuplicate(String user_id);
	public Map checkDuplicateByEmail(String user_email);
}
