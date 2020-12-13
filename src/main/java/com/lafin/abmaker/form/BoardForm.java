package com.lafin.abmaker.form;

import java.util.List;

import com.lafin.abmaker.util.PagingUtil;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class BoardForm {
	private Integer page = 1;
	private Integer user_seq = 0;
	private Integer form_seq = 0;
	private Integer link_seq = 0;
	private Integer asset_seq = 0;
	private String keyword = "";
	private Integer entries = 10;
	private PagingUtil paging = new PagingUtil();
	private List result;
}
