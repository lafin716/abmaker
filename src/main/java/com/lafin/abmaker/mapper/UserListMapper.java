package com.lafin.abmaker.mapper;

import org.apache.ibatis.annotations.Param;

import com.lafin.abmaker.dto.UserDto;

public interface UserListMapper {
	
	public UserDto getMember(@Param("user_email") String user_email, @Param("user_pw") String user_pw);
	public Integer getMemberCountByEmail(@Param("user_email") String user_email);
	public Integer setMember(UserDto userDto);
}
