package com.lafin.abmaker.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lafin.abmaker.dto.PageDto;
import com.lafin.abmaker.form.BoardForm;
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
		
		List formList = formService.getFormListAll(userInfo.getUser_seq());
		BoardForm pageForm = pageService.getPageList(form);
		
		model.addAttribute("formList", formList);
		model.addAttribute("pageList", pageForm.getResult());
		model.addAttribute("paging", pageForm.getPaging());
		
		return "content/page/list";
	}
	
	@RequestMapping(value = "/view")
	public String view(Model model, @ModelAttribute PageDto param) {
		super.init(model);
		
		PageDto pageData = pageService.getPage(param);
		logger.info(pageData.toString());
		model.addAttribute("formData", pageData);
		
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
			return JsUtil.openAlert("양식이 삭제되었습니다.", "reload", JsUtil.PARENT);
		}else {
			return JsUtil.openAlert("일시적인 오류입니다. 다시 시도해 주세요.", "reload", JsUtil.PARENT);
		}
	}

}
