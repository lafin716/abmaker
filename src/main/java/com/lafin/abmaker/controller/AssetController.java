package com.lafin.abmaker.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lafin.abmaker.dto.AssetDto;
import com.lafin.abmaker.dto.AssetLogDto;
import com.lafin.abmaker.dto.ComponentDto;
import com.lafin.abmaker.dto.FormDto;
import com.lafin.abmaker.form.BoardForm;
import com.lafin.abmaker.service.AssetService;
import com.lafin.abmaker.service.ComponentService;
import com.lafin.abmaker.service.FormService;
import com.lafin.abmaker.util.JsUtil;

@Controller
@RequestMapping(value = "/asset/*")
public class AssetController extends BaseController{
	
	@Autowired
	private FormService formService;
	
	@Autowired
	private AssetService assetService;
	
	@Autowired
	private ComponentService componentService;

	public AssetController(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	@RequestMapping
	public void index(HttpServletResponse response) throws IOException {
		response.sendRedirect("/asset/list");
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
		BoardForm assetForm = assetService.getAssetList(form);
		
		logger.info(form.toString());
		
		model.addAttribute("formList", formList);
		model.addAttribute("assetList", assetForm.getResult());
		model.addAttribute("paging", assetForm.getPaging());
		model.addAttribute("param", form);
		
		return "content/asset/list";
	}
	
	@RequestMapping(value = "/view")
	public String view(Model model, @ModelAttribute AssetDto param) {
		super.init(model);
		
		List formList = formService.getFormListAll(userInfo.getUser_seq());
		if(ObjectUtils.isEmpty(formList)) return "redirect:/empty?code=100";
		
		param.setUser_seq(userInfo.getUser_seq());
		AssetDto assetData = assetService.getAsset(param);
		logger.info(assetData.toString());
		
		model.addAttribute("formList", formList);
		model.addAttribute("assetData", assetData);
		
		return "content/asset/view";
	}
	
	@PostMapping(value="add")
	public @ResponseBody String add(Model model, @ModelAttribute AssetDto param) {
		super.init(model);
		
		param.setUser_seq(userInfo.getUser_seq());
		boolean result = assetService.addAsset(param);
		
		if(result) {
			return JsUtil.openAlert("자산이 추가되었습니다.", "reload", JsUtil.PARENT);
		}else {
			return JsUtil.openAlert("일시적인 오류입니다. 다시 시도해 주세요.", "reload", JsUtil.PARENT);
		}
	}
	
	@PostMapping(value="modify")
	public @ResponseBody String modify(Model model, @ModelAttribute AssetDto param) {
		super.init(model);
		
		param.setUser_seq(userInfo.getUser_seq());
		boolean result = assetService.modifyAsset(param);
		
		if(result) {
			return JsUtil.openAlert("자산이 수정되었습니다.", "reload", JsUtil.PARENT);
		}else {
			return JsUtil.openAlert("일시적인 오류입니다. 다시 시도해 주세요.", "reload", JsUtil.PARENT);
		}
	}
	
	@PostMapping(value="delete")
	public @ResponseBody String delete(Model model, @ModelAttribute AssetDto param) {
		super.init(model);
		
		param.setUser_seq(userInfo.getUser_seq());
		boolean result = assetService.deleteAsset(param);
		
		if(result) {
			return JsUtil.openAlert("자산이 삭제되었습니다.", "reload", JsUtil.PARENT);
		}else {
			return JsUtil.openAlert("일시적인 오류입니다. 다시 시도해 주세요.", "reload", JsUtil.PARENT);
		}
	}
	
	@PostMapping(value="calculate")
	public @ResponseBody String calculate(Model model, @ModelAttribute AssetLogDto param) {
		
		// 자산 가져오기
		AssetDto assetData = assetService.getAssetById(param.getAsset_seq());
		
		// 컴포넌트 가져오기
		ComponentDto compData = componentService.getComponentById(param.getComp_seq());
		
		logger.info(assetData.toString());
		logger.info(compData.toString());
		logger.info(param.toString());
		
		// 계산 시작
		boolean result = assetService.calculate(assetData, compData, param);
		
		if(result) {
			return JsUtil.openAlert("저장되었습니다.", "reload", JsUtil.PARENT);
		}else {
			return JsUtil.openAlert("일시적인 오류입니다. 다시 시도해 주세요.", "reload", JsUtil.PARENT);
		}
		
	}

}
