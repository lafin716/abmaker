package com.lafin.abmaker.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDto {
	
	private Integer user_seq;
	private String user_pw;
	private String user_name;
	private String user_email;
	private String user_status;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date regist_date;
	
}
