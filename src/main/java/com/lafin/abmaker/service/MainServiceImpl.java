package com.lafin.abmaker.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lafin.abmaker.dto.FormList;
import com.lafin.abmaker.mapper.FormListMapper;

@Service("mainService")
public class MainServiceImpl implements MainService{
	
	// 로거 설정
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private FormListMapper formListMapper;

	@Override
	public List<FormList> getFormList() {
		
		List<FormList> list = formListMapper.getFormList();
		
		for(int i=0; i<list.size(); i++) {
			FormList tmpForm = (FormList) list.get(i);
			
			System.out.println(tmpForm.toString());
			
			logger.info(tmpForm.getFormSeq() + "");
			logger.info(tmpForm.getFormTitle());
			logger.info(tmpForm.getIsMain() + "");
			logger.info(tmpForm.getRegistDate().toString());
		}

		return list;
	}
	
	
}
