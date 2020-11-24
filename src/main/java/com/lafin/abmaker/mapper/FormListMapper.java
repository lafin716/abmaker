package com.lafin.abmaker.mapper;

import java.util.List;

import com.lafin.abmaker.dto.FormDto;
import com.lafin.abmaker.dto.PageDto;

public interface FormListMapper {
	
	public List<FormDto> getFormList();
	public List<PageDto> getPageList(Integer formSeq);
}
