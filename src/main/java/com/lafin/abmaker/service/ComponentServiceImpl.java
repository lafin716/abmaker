package com.lafin.abmaker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lafin.abmaker.dto.ComponentDto;
import com.lafin.abmaker.form.BoardForm;
import com.lafin.abmaker.mapper.ComponentListMapper;
import com.lafin.abmaker.util.PagingUtil;

@Service("componentService")
public class ComponentServiceImpl implements ComponentService{
	
	@Autowired
	private ComponentListMapper componentListMapper;

	@Override
	public BoardForm getComponentList(BoardForm param) {
		// 페이징 설정
		Integer totalCount = componentListMapper.getTotal(param.getUser_seq());
		PagingUtil paging = new PagingUtil();
		paging.createPaging(totalCount, param.getPage());		
		param.setPaging(paging);
		
		List<ComponentDto> resultList = componentListMapper.getComponentList(param);
		BoardForm result = new BoardForm();
		
		result.setResult(resultList);
		result.setPaging(paging);
		
		return result;
	}
	
	@Override
	public ComponentDto getComponentById(Integer comp_seq) {
		return componentListMapper.getComponentById(comp_seq);
	}
	
	@Override
	public ComponentDto getComponent(ComponentDto param) {		
		return componentListMapper.getComponent(param);
	}

	@Override
	public boolean addComponent(ComponentDto param) {		
		return componentListMapper.addComponent(param);
	}

	@Override
	public boolean deleteComponent(ComponentDto param) {
		return componentListMapper.deleteComponent(param);
	}

	@Override
	public boolean modifyComponent(ComponentDto param) {
		return componentListMapper.modifyComponent(param);
	}
	
	
}
