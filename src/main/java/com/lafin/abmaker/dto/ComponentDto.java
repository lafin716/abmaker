package com.lafin.abmaker.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ComponentDto {
	private Integer comp_seq;
	private List<Integer> comp_seq_list;
	private Integer user_seq;
	private Integer asset_seq;
	private String asset_name;
	private String comp_name;
	private String type;
	private String type_text;
	private boolean is_writable;
	private boolean is_readonly;
	private String comp_content;
	private Date regist_date;
	
}
