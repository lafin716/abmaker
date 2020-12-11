package com.lafin.abmaker.dto;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class PageDto {
	private Integer page_seq;
	private List<Integer> page_seq_list;
	private Integer form_seq;
	private String page_title;
	private Integer order;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date regist_date;
}
