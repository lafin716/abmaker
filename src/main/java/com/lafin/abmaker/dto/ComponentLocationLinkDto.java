package com.lafin.abmaker.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ComponentLocationLinkDto {
	private Integer comp_set_seq;
	private Integer comp_seq;
	private String comp_name;
	private Integer asset_seq;
	private String type;
	private Integer page_seq;
	private Integer comp_order;
	private Date regist_date;
}
