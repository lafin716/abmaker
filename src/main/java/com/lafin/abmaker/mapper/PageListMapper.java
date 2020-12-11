package com.lafin.abmaker.mapper;

import java.util.List;

import com.lafin.abmaker.dto.PageDto;
import com.lafin.abmaker.form.BoardForm;

public interface PageListMapper{
	
	public Integer getTotal(Integer form_seq);
	public List<PageDto> getPageList(BoardForm param);
	public PageDto getPage(PageDto param);
	public boolean addPage(PageDto param);
	public boolean deletePage(PageDto param);
	public boolean modifyPage(PageDto param);
}
