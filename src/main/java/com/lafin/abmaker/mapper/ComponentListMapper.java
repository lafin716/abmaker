package com.lafin.abmaker.mapper;

import java.util.List;

import com.lafin.abmaker.dto.ComponentDto;
import com.lafin.abmaker.dto.ComponentLocationLinkDto;
import com.lafin.abmaker.form.BoardForm;

public interface ComponentListMapper {
	public Integer getTotal(Integer user_seq);
	public List<ComponentDto> getComponentListAll(Integer user_seq);
	public List<ComponentLocationLinkDto> getComponentLinkList(Integer page_seq);
	public List<ComponentDto> getComponentList(BoardForm param);
	public ComponentDto getComponent(ComponentDto param);
	public ComponentDto getComponentById(Integer comp_seq);
	public boolean addComponent(ComponentDto param);
	public boolean deleteComponent(ComponentDto param);
	public boolean modifyComponent(ComponentDto param);
}
