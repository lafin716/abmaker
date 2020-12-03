package com.lafin.abmaker.mapper;

import com.lafin.abmaker.dto.UserDto;

public interface UserListMapper {
	
	public UserDto getMember(String user_id, String user_pw);
	public Integer getMemberCountById(String user_id);
	public Integer getMemberCountByEmail(String user_email);
	public Integer setMember(UserDto userDto);
	
}
