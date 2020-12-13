package com.lafin.abmaker.dto;

import lombok.Data;

@Data
public class AssetLogDto {
	private int asset_log_seq;
	private int comp_seq;
	private int asset_seq;
	private String asset_name;
	private String comp_name;
	private String type;
	private int amount;
	private String memo;
	private String regist_date;
	
}
