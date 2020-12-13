package com.lafin.abmaker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.lafin.abmaker.dto.ComponentDto;
import com.lafin.abmaker.dto.ComponentLocationLinkDto;
import com.lafin.abmaker.dto.FormDto;
import com.lafin.abmaker.dto.PageDto;
import com.lafin.abmaker.form.BoardForm;
import com.lafin.abmaker.form.DecorateForm;
import com.lafin.abmaker.mapper.ComponentListMapper;
import com.lafin.abmaker.mapper.FormListMapper;
import com.lafin.abmaker.mapper.PageListMapper;
import com.lafin.abmaker.util.PagingUtil;

@Service("pageService")
public class PageServiceImpl extends BaseService implements PageService{
	
	@Autowired
	private FormListMapper formListMapper;
	
	@Autowired
	private PageListMapper pageListMapper;
	
	@Autowired
	private ComponentListMapper componentListMapper;

	@Override
	public BoardForm getPageList(BoardForm param) {
		
		// 페이징 설정
		Integer totalCount = pageListMapper.getTotal(param.getForm_seq());
		PagingUtil paging = new PagingUtil();
		paging.createPaging(totalCount, param.getPage());		
		param.setPaging(paging);
		
		// 페이지리스트는 양식번호가 무조건 있어야함 기본값은 메인 사용 양식번호
		if(param.getForm_seq() == 0) {
			FormDto mainForm = formListMapper.getMainForm(param.getUser_seq());
			
			if(!ObjectUtils.isEmpty(mainForm)) {
				param.setForm_seq(mainForm.getForm_seq());
			}			
		}
		
		List<PageDto> resultList = pageListMapper.getPageList(param);
		BoardForm result = new BoardForm();
		result.setResult(resultList);
		result.setPaging(paging);
		
		return result;
	}
	
	@Override
	public List<PageDto> getPageListByFormSeq(Integer form_seq) {		
		return pageListMapper.getPageListByFormSeq(form_seq);
	}

	@Override
	public PageDto getPage(PageDto param) {
		return pageListMapper.getPage(param);
	}

	@Override
	public boolean addPage(PageDto param) {
		// 해당 양식에 마지막 순서번호를 가져온다
		Integer maxOrder = pageListMapper.getMaxOrder(param.getForm_seq());
		param.setOrder(maxOrder + 1);
		
		return pageListMapper.addPage(param);
	}

	@Override
	public boolean deletePage(PageDto param) {
		return pageListMapper.deletePage(param);
	}

	@Override
	public boolean modifyPage(PageDto param) {
		return pageListMapper.modifyPage(param);
	}

	@Override
	public DecorateForm getDecorate(DecorateForm param) {
		
		List<ComponentDto> componentList = componentListMapper.getComponentListAll(param.getUser_seq());
		List<ComponentLocationLinkDto> componentLinkList = componentListMapper.getComponentLinkList(param.getPage_seq()); 
		PageDto pageDto = pageListMapper.getPageById(param.getPage_seq());
		
		
		param.setComponentLinkList(componentLinkList);
		param.setComponentList(componentList);
		param.setPageDto(pageDto);
		
		return param;
	}

	@Override
	public boolean setDecorate(DecorateForm param) {
		
		// 컴포넌트를 비움
		pageListMapper.clearComponentLink(param.getPage_seq());
		
		// 순서대로 insert
		List<Integer> compSeqList = param.getComp_seq_list();
		for(int i=0; i<compSeqList.size(); i++) {
			ComponentLocationLinkDto addData = new ComponentLocationLinkDto();
			Integer comp_seq = compSeqList.get(i);
			
			addData.setComp_seq(comp_seq);
			addData.setPage_seq(param.getPage_seq());
			addData.setComp_order(i);
			
			boolean tmpResult = pageListMapper.addComponentLink(addData);
			 
			if(!tmpResult) return tmpResult;
		}
		
		logger.info(param.toString());
		
		return true;
	}
	
}
