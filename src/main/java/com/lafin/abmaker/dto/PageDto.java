package com.lafin.abmaker.dto;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PageDto {
	private Integer page_seq;
	private List<Integer> page_seq_list;
	private Integer form_seq;
	private String form_title;
	private String page_title;
	private Integer order;
	private Date regist_date;
}
