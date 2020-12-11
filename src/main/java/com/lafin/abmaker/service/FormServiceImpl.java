package com.lafin.abmaker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lafin.abmaker.dto.FormDto;
import com.lafin.abmaker.form.BoardForm;
import com.lafin.abmaker.mapper.FormListMapper;
import com.lafin.abmaker.util.PagingUtil;

@Service("formService")
public class FormServiceImpl extends BaseService implements FormService{
	
	@Autowired
	private FormListMapper formListMapper;
	
	@Override
	public List getFormListAll(Integer user_seq) {
		return formListMapper.getFormListAll(user_seq);
	}

	@Override
	public BoardForm getFormList(BoardForm form) {
		
		// 페이징 설정
		Integer totalCount = formListMapper.getTotal(form.getUser_seq());
		PagingUtil paging = new PagingUtil();
		paging.createPaging(totalCount, form.getPage());		
		form.setPaging(paging);
		
		List<FormDto> resultList = formListMapper.getFormList(form);
		BoardForm result = new BoardForm();
		
		result.setResult(resultList);
		result.setPaging(paging);
		
		return result;
	}

	@Override
	public FormDto getForm(FormDto param) {
		return formListMapper.getForm(param);
	}

	@Override
	public boolean setMainForm(FormDto param) {
		return formListMapper.setMainForm(param);
	}

	@Override
	public boolean addForm(FormDto param) {
		
		// 총 양식 갯수
		Integer totalCount = formListMapper.getTotal(param.getUser_seq());
		
		// 총 갯수가 0인경우 처음 등록 시 main 양식으로 등록함
		if(totalCount > 0) {
			param.setIs_main(0);
		}else {
			param.setIs_main(1);
		}
		
		return formListMapper.addForm(param);
	}

	@Override
	public boolean deleteForm(FormDto param) {
		return formListMapper.deleteForm(param);
	}

	@Override
	public boolean modifyForm(FormDto param) {
		if(param.getIs_main() == 1) {
			formListMapper.setMainForm(param);
		}
		
		return formListMapper.modifyForm(param);
	}
	
}
