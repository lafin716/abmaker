package com.lafin.abmaker.mapper;

import java.util.List;

import com.lafin.abmaker.dto.ComponentLocationLinkDto;
import com.lafin.abmaker.dto.PageDto;
import com.lafin.abmaker.form.BoardForm;

public interface PageListMapper{
	
	public Integer getTotal(Integer form_seq);
	public List<PageDto> getPageList(BoardForm param);
	public List<PageDto> getPageListByFormSeq(Integer form_seq);	
	public PageDto getPage(PageDto param);
	public PageDto getPageById(Integer page_seq);
	public boolean addPage(PageDto param);
	public boolean deletePage(PageDto param);
	public boolean modifyPage(PageDto param);
	public Integer getMaxOrder(Integer form_seq);
	public boolean addComponentLink(ComponentLocationLinkDto param);
	public boolean clearComponentLink(Integer page_seq);
}
