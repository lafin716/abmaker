package com.lafin.abmaker.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lafin.abmaker.dto.FormDto;
import com.lafin.abmaker.form.BoardForm;
import com.lafin.abmaker.service.FormService;
import com.lafin.abmaker.util.JsUtil;

@Controller
@RequestMapping(value = "/form/*")
public class FormController extends BaseController{
	
	@Autowired
	private FormService formService;

	public FormController(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	@RequestMapping
	public void index(HttpServletResponse response) throws IOException {
		response.sendRedirect("/form/list");
	}
	
	@RequestMapping(value = "/list")
	public String list(Model model, @ModelAttribute BoardForm form) {
		super.init(model);
		
		form.setUser_seq(userInfo.getUser_seq());
		BoardForm formList = formService.getFormList(form);
		
		logger.info(formList.toString());
		
		model.addAttribute("formList", formList.getResult());
		model.addAttribute("paging", formList.getPaging());
		
		return "content/form/list";
	}
	
	@RequestMapping(value = "/view")
	public String view(Model model, @ModelAttribute FormDto param) {
		super.init(model);
		
		param.setUser_seq(userInfo.getUser_seq());
		FormDto formData = formService.getForm(param);
		logger.info(formData.toString());
		model.addAttribute("formData", formData);
		
		return "content/form/view";
	}
	
	@PostMapping(value="add")
	public @ResponseBody String add(Model model, @ModelAttribute FormDto param) {
		super.init(model);
		
		param.setUser_seq(userInfo.getUser_seq());
		boolean result = formService.addForm(param);
		
		if(result) {
			return JsUtil.openAlert("양식이 추가되었습니다.", "reload", JsUtil.PARENT);
		}else {
			return JsUtil.openAlert("일시적인 오류입니다. 다시 시도해 주세요.", "reload", JsUtil.PARENT);
		}
	}
	
	@PostMapping(value="modify")
	public @ResponseBody String modify(Model model, @ModelAttribute FormDto param) {
		super.init(model);
		
		param.setUser_seq(userInfo.getUser_seq());
		boolean result = formService.modifyForm(param);
		
		if(result) {
			return JsUtil.openAlert("양식이 수정되었습니다.", "reload", JsUtil.PARENT);
		}else {
			return JsUtil.openAlert("일시적인 오류입니다. 다시 시도해 주세요.", "reload", JsUtil.PARENT);
		}
	}
	
	@PostMapping(value="delete")
	public @ResponseBody String delete(Model model, @ModelAttribute FormDto param) {
		super.init(model);
		
		param.setUser_seq(userInfo.getUser_seq());
		boolean result = formService.deleteForm(param);
		
		if(result) {
			return JsUtil.openAlert("양식이 삭제되었습니다.", "reload", JsUtil.PARENT);
		}else {
			return JsUtil.openAlert("일시적인 오류입니다. 다시 시도해 주세요.", "reload", JsUtil.PARENT);
		}
	}

}
