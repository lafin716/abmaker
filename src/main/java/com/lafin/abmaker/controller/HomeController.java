package com.lafin.abmaker.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lafin.abmaker.dto.FormDto;
import com.lafin.abmaker.dto.PageDto;
import com.lafin.abmaker.form.DecorateForm;
import com.lafin.abmaker.service.ComponentService;
import com.lafin.abmaker.service.FormService;
import com.lafin.abmaker.service.PageService;
import com.lafin.abmaker.util.StringUtil;

@Controller
public class HomeController extends BaseController {

	// 서비스 연결
	@Autowired
	private FormService formService;
	
	@Autowired
	private PageService pageService;
	
	@Autowired
	private ComponentService componenService;
	
	// autowired가 아닌 생성자 주입 방식
	public HomeController(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@RequestMapping(value="/")
	public String home(Model model, @ModelAttribute DecorateForm param) {
		super.init(model);
		
		// 메인 양식 가져옴
		FormDto mainForm = formService.getMainForm(userInfo.getUser_seq());
		if(ObjectUtils.isEmpty(mainForm)) return "redirect:/empty?code=101";		
		
		// 페이지 리스트 가져오기
		List<PageDto> pageList = pageService.getPageListByFormSeq(mainForm.getForm_seq());
		
		// 페이지번호가 없는경우 첫페이지의 컴포넌트를 조회
		Integer pageSeq = StringUtil.getInt(param.getPage_seq(), 0);
		if(pageSeq <= 0) {
			PageDto firstPage = pageList.get(0);
			pageSeq = firstPage.getPage_seq();
			param.setPage_seq(pageSeq);
		}
		
		// 페이지가 연결된 컴포넌트 목록 가져오기
		DecorateForm decorateData = pageService.getDecorate(param);
		
		// 객체 전달
		model.addAttribute("mainForm", mainForm);
		model.addAttribute("pageList", pageList);
		model.addAttribute("pageSeq", pageSeq);
		model.addAttribute("compList", decorateData.getComponentList());
		model.addAttribute("compLinkList", decorateData.getComponentLinkList());
		
		return "index";
	}
}
