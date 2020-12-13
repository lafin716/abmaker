package com.lafin.abmaker.mapper;

import java.util.List;

import com.lafin.abmaker.dto.FormDto;
import com.lafin.abmaker.form.BoardForm;

public interface FormListMapper{
	
	public Integer getTotal(Integer user_seq);
	public List<FormDto> getFormListAll(Integer user_seq);
	public List<FormDto> getFormList(BoardForm param);
	public FormDto getForm(FormDto param);
	public FormDto getMainForm(Integer user_seq);
	public boolean setMainForm(FormDto param);
	public boolean addForm(FormDto param);
	public boolean deleteForm(FormDto param);
	public boolean modifyForm(FormDto param);
	public Integer countMainForm(FormDto param);
}
