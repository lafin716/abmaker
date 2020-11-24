package com.lafin.abmaker.service;

import java.util.List;

import com.lafin.abmaker.dto.FormDto;
import com.lafin.abmaker.dto.PageDto;

public interface MainService {
	
	public List<FormDto> getFormList();
	public List<PageDto> getPageList(Integer formSeq);
}
