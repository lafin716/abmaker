package com.lafin.abmaker.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lafin.abmaker.dto.FormDto;
import com.lafin.abmaker.dto.PageDto;
import com.lafin.abmaker.form.BoardForm;
import com.lafin.abmaker.form.DecorateForm;
import com.lafin.abmaker.service.ComponentService;
import com.lafin.abmaker.service.FormService;
import com.lafin.abmaker.service.PageService;
import com.lafin.abmaker.util.JsUtil;

@Controller
@RequestMapping(value = "/page/*")
public class PageController extends BaseController{
	
	@Autowired
	private FormService formService;
	
	@Autowired
	private PageService pageService;
	
	@Autowired
	private ComponentService componentService;

	public PageController(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	@RequestMapping
	public void index(HttpServletResponse response) throws IOException {
		response.sendRedirect("/page/list");
	}
	
	@RequestMapping(value = "/list")
	public String list(Model model, @ModelAttribute BoardForm form) {
		super.init(model);
		
		// 양식 리스트 가져오기
		List formList = formService.getFormListAll(userInfo.getUser_seq());
		// 메인 양식 가져오기
		FormDto mainForm = formService.getMainForm(userInfo.getUser_seq());
		
		// 양식이 없는 경우 핸들러 리다이렉트
		if(ObjectUtils.isEmpty(formList)) return "redirect:/empty?code=100";
		else if(ObjectUtils.isEmpty(mainForm)) return "redirect:/empty?code=101";
		
		// 검색한 양식번호가 없는경우 기본으로 메인 양식 선택
		if(form.getForm_seq() == 0) {			
			form.setForm_seq(mainForm.getForm_seq());
		}
		
		// 페이지 목록 가져오기
		form.setUser_seq(userInfo.getUser_seq());
		BoardForm pageForm = pageService.getPageList(form);
		
		logger.info(form.toString());
		
		model.addAttribute("formList", formList);
		model.addAttribute("pageList", pageForm.getResult());
		model.addAttribute("paging", pageForm.getPaging());
		model.addAttribute("param", form);
		
		return "content/page/list";
	}	
	
	@RequestMapping(value = "/view")
	public String view(Model model, @ModelAttribute PageDto param) {
		super.init(model);
		
		List formList = formService.getFormListAll(userInfo.getUser_seq());
		if(ObjectUtils.isEmpty(formList)) return "redirect:/empty?code=100";
		
		PageDto pageData = pageService.getPage(param);
		logger.info(pageData.toString());
		
		model.addAttribute("formList", formList);
		model.addAttribute("pageData", pageData);
		
		return "content/page/view";
	}
	
	@PostMapping(value="add")
	public @ResponseBody String add(Model model, @ModelAttribute PageDto param) {
		super.init(model);
		
		boolean result = pageService.addPage(param);
		
		if(result) {
			return JsUtil.openAlert("페이지가 추가되었습니다.", "reload", JsUtil.PARENT);
		}else {
			return JsUtil.openAlert("일시적인 오류입니다. 다시 시도해 주세요.", "reload", JsUtil.PARENT);
		}
	}
	
	@PostMapping(value="modify")
	public @ResponseBody String modify(Model model, @ModelAttribute PageDto param) {
		super.init(model);
		
		boolean result = pageService.modifyPage(param);
		
		if(result) {
			return JsUtil.openAlert("페이지가 수정되었습니다.", "reload", JsUtil.PARENT);
		}else {
			return JsUtil.openAlert("일시적인 오류입니다. 다시 시도해 주세요.", "reload", JsUtil.PARENT);
		}
	}
	
	@PostMapping(value="delete")
	public @ResponseBody String delete(Model model, @ModelAttribute PageDto param) {
		super.init(model);
		
		boolean result = pageService.deletePage(param);
		
		if(result) {
			return JsUtil.openAlert("페이지가 삭제되었습니다.", "reload", JsUtil.PARENT);
		}else {
			return JsUtil.openAlert("일시적인 오류입니다. 다시 시도해 주세요.", "reload", JsUtil.PARENT);
		}
	}
	
	@GetMapping(value = "/decorate")
	public String decorate(Model model, @ModelAttribute DecorateForm param) {
		super.init(model);
		
		param.setUser_seq(userInfo.getUser_seq());
		DecorateForm decorateData = pageService.getDecorate(param);
		
		model.addAttribute("pageData", decorateData.getPageDto());
		model.addAttribute("componentList", decorateData.getComponentList());
		model.addAttribute("componentLinkList", decorateData.getComponentLinkList());
		
		return "content/page/decorate";
	}
	
	@PostMapping(value="/decorate")
	public @ResponseBody String decorate_process(Model model, @ModelAttribute DecorateForm param) {
		super.init(model);
		
		boolean result = pageService.setDecorate(param);
		
		if(result) {
			return JsUtil.openAlert("꾸미기가 완료되었습니다.", "reload", JsUtil.PARENT);
		}else {
			return JsUtil.openAlert("일시적인 오류입니다. 다시 시도해 주세요.", "reload", JsUtil.PARENT);
		}
	}

}
