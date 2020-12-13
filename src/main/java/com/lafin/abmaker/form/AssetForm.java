package com.lafin.abmaker.form;

import lombok.Data;

@Data
public class AssetForm {
	private Integer user_seq;
	private Integer asset_seq;
	private String asset_name;
	private Integer comp_seq;
	private String comp_name;
	private String type;
	private String memo;
	private Integer amount;
}
