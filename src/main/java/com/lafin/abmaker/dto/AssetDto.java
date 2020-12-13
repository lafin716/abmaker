package com.lafin.abmaker.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class AssetDto {
	private Integer asset_seq;
	private List<Integer> asset_seq_list;
	private Integer user_seq;
	private String asset_name;
	private Integer amount;
	private String type;
	private Integer link_seq;
	private String form_title;
	private String memo;
	private Date regist_date;		
}
