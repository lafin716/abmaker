package com.lafin.abmaker.dto;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormDto {
	private Integer form_seq;
	private List<Integer> form_seq_list;
	private Integer user_seq;
	private String form_title;
	private Integer is_main;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date regist_date;	
}
