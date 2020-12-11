package com.lafin.abmaker.service;

import com.lafin.abmaker.dto.PageDto;
import com.lafin.abmaker.form.BoardForm;

public interface PageService {
	
	public BoardForm getPageList(BoardForm param);
	public PageDto getPage(PageDto param);
	public boolean addPage(PageDto param);
	public boolean deletePage(PageDto param);
	public boolean modifyPage(PageDto param);
}
