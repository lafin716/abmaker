package com.lafin.abmaker.service;

import java.util.List;
import java.util.Map;

import com.lafin.abmaker.dto.FormDto;
import com.lafin.abmaker.form.BoardForm;

public interface FormService {
	
	public List getFormListAll(Integer user_seq);
	public BoardForm getFormList(BoardForm param);
	public FormDto getForm(FormDto param);
	public FormDto getMainForm(Integer user_seq);
	public boolean setMainForm(FormDto param);
	public boolean addForm(FormDto param);
	public Map deleteForm(FormDto param);
	public boolean modifyForm(FormDto param);
}
