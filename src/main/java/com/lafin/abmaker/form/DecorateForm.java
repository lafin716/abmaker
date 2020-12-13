package com.lafin.abmaker.form;

import java.util.List;

import com.lafin.abmaker.dto.ComponentDto;
import com.lafin.abmaker.dto.ComponentLocationLinkDto;
import com.lafin.abmaker.dto.PageDto;

import lombok.Data;

@Data
public class DecorateForm {
	private Integer user_seq;
	private Integer page_seq = 0;
	private Integer comp_seq;
	private List<Integer> comp_seq_list;
	private PageDto pageDto;
	private List<ComponentDto> componentList;
	private List<ComponentLocationLinkDto> componentLinkList;
}
