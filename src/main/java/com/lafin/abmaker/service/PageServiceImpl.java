package com.lafin.abmaker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lafin.abmaker.dto.FormDto;
import com.lafin.abmaker.dto.PageDto;
import com.lafin.abmaker.form.BoardForm;
import com.lafin.abmaker.mapper.PageListMapper;
import com.lafin.abmaker.util.PagingUtil;

@Service("pageService")
public class PageServiceImpl implements PageService{
	
	@Autowired
	private PageListMapper pageListMapper;

	@Override
	public BoardForm getPageList(BoardForm param) {
		
		// 페이징 설정
		Integer totalCount = pageListMapper.getTotal(param.getForm_seq());
		PagingUtil paging = new PagingUtil();
		paging.createPaging(totalCount, param.getPage());		
		param.setPaging(paging);
		
		List<PageDto> resultList = pageListMapper.getPageList(param);
		BoardForm result = new BoardForm();
		result.setResult(resultList);
		result.setPaging(paging);
		
		return null;
	}

	@Override
	public PageDto getPage(PageDto param) {
		return pageListMapper.getPage(param);
	}

	@Override
	public boolean addPage(PageDto param) {		
		return pageListMapper.addPage(param);
	}

	@Override
	public boolean deletePage(PageDto param) {
		// TODO Auto-generated method stub
		return pageListMapper.deletePage(param);
	}

	@Override
	public boolean modifyPage(PageDto param) {
		// TODO Auto-generated method stub
		return pageListMapper.modifyPage(param);
	}
	
	
}
