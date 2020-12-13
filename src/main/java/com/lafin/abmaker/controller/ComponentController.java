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

import com.lafin.abmaker.dto.AssetDto;
import com.lafin.abmaker.dto.ComponentDto;
import com.lafin.abmaker.form.BoardForm;
import com.lafin.abmaker.service.AssetService;
import com.lafin.abmaker.service.ComponentService;
import com.lafin.abmaker.util.JsUtil;

@Controller
@RequestMapping(value = "/component/*")
public class ComponentController extends BaseController {
	
	@Autowired
	private AssetService assetService;
	
	@Autowired
	private ComponentService componentService;
	
	public ComponentController(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	@RequestMapping
	public void index(HttpServletResponse response) throws IOException {
		response.sendRedirect("/component/list");
	}
	
	@GetMapping(value="/list")
	public String list(Model model, @ModelAttribute BoardForm form) {
		super.init(model);
		
		// 자산목록 가져오기
		List<AssetDto> assetList = assetService.getAssetListAll(userInfo.getUser_seq());
		if(ObjectUtils.isEmpty(assetList)) return "redirect:/empty?code=201";
		
		// 자산 중 첫번째행을 기본값으로 설정
		if(form.getAsset_seq() == 0) {
			AssetDto assetDto = (AssetDto) assetList.get(0);
			form.setAsset_seq(assetDto.getAsset_seq());
		}
		
		// 컴포넌트 목록 가져오기
		form.setUser_seq(userInfo.getUser_seq());
		BoardForm componentList = componentService.getComponentList(form);
		
		logger.info(componentList.toString());
		
		model.addAttribute("assetList", assetList);
		model.addAttribute("componentList", componentList.getResult());
		model.addAttribute("paging", componentList.getPaging());
		
		return "content/component/list";
	}
	
	@GetMapping(value="/add")
	public String add(Model model) {
		super.init(model);
		
		// 자산 목록 가져오기
		List<AssetDto> assetList = assetService.getAssetListAll(userInfo.getUser_seq());		
		model.addAttribute("assetList", assetList);
		model.addAttribute("componentData", new ComponentDto());
		
		return "content/component/view";
	}
	
	@PostMapping(value="/add")
	public @ResponseBody String add_process(Model model, @ModelAttribute ComponentDto param) {
		super.init(model);
		
		logger.info("add process");
		
		// 컴포넌트 추가
		param.setUser_seq(userInfo.getUser_seq());
		logger.info(param.toString());
		boolean result = componentService.addComponent(param);
		
		if(result) {
			return JsUtil.openAlert("컴포넌트가 추가되었습니다.", "parent.location.href='/component/list';", JsUtil.PARENT);
		}else {
			return JsUtil.openAlert("일시적인 오류입니다. 새로고침 후 다시 시도해주세요.", "reload", JsUtil.PARENT);
		}
	}
	
	@GetMapping(value="/view")
	public String view(Model model, @ModelAttribute ComponentDto param) {
		super.init(model);
		
		// 자산 목록 가져오기
		List<AssetDto> assetList = assetService.getAssetListAll(userInfo.getUser_seq());		
				
		// 자산 목록 가져오기
		param.setUser_seq(userInfo.getUser_seq());
		ComponentDto componentDto = componentService.getComponent(param);
		
		logger.info(componentDto.toString());
		
		model.addAttribute("assetList", assetList);
		model.addAttribute("componentData", componentDto);
		
		return "content/component/view";
	}
	
	@PostMapping(value="/modify")
	public @ResponseBody String modify(Model model, @ModelAttribute ComponentDto param) {
		super.init(model);
		
		// 자산 수정하기
		param.setUser_seq(userInfo.getUser_seq());
		boolean result = componentService.modifyComponent(param);
		
		if(result) {
			return JsUtil.openAlert("컴포넌트가 수정되었습니다.", "parent.location.href='/component/list';", JsUtil.PARENT);
		}else {
			return JsUtil.openAlert("일시적인 오류입니다. 새로고침 후 다시 시도해주세요.", "reload", JsUtil.PARENT);
		}
	}
	
	@PostMapping(value="/delete")
	public @ResponseBody String delete(Model model, @ModelAttribute ComponentDto param) {
		super.init(model);
		
		// 자산 수정하기
		param.setUser_seq(userInfo.getUser_seq());
		boolean result = componentService.deleteComponent(param);
		
		if(result) {
			return JsUtil.openAlert("컴포넌트가 삭제되었습니다.", "reload", JsUtil.PARENT);
		}else {
			return JsUtil.openAlert("일시적인 오류입니다. 새로고침 후 다시 시도해주세요.", "reload", JsUtil.PARENT);
		}
	}
}
