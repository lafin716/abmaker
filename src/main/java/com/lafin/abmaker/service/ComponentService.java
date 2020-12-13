package com.lafin.abmaker.service;

import com.lafin.abmaker.dto.ComponentDto;
import com.lafin.abmaker.form.BoardForm;

public interface ComponentService {
	
	public BoardForm getComponentList(BoardForm param);
	public ComponentDto getComponent(ComponentDto param);
	public ComponentDto getComponentById(Integer comp_seq);
	public boolean addComponent(ComponentDto param);
	public boolean deleteComponent(ComponentDto param);
	public boolean modifyComponent(ComponentDto param);
}
