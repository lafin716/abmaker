package com.lafin.abmaker.service;

import java.util.List;

import com.lafin.abmaker.dto.PageDto;
import com.lafin.abmaker.form.BoardForm;
import com.lafin.abmaker.form.DecorateForm;

public interface PageService {
	
	public BoardForm getPageList(BoardForm param);
	public List<PageDto> getPageListByFormSeq(Integer form_seq);
	public PageDto getPage(PageDto param);
	public boolean addPage(PageDto param);
	public boolean deletePage(PageDto param);
	public boolean modifyPage(PageDto param);
	public DecorateForm getDecorate(DecorateForm param);
	public boolean setDecorate(DecorateForm param);
	
}
