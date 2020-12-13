package com.lafin.abmaker.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class UserDto {
	private Integer user_seq;
	private String user_pw;
	private String user_name;
	private String user_email;
	private String user_status;
	private Date regist_date;
	
}
