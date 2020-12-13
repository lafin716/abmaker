package com.lafin.abmaker.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lafin.abmaker.common.Config;
import com.lafin.abmaker.dto.UserDto;
import com.lafin.abmaker.service.MemberService;
import com.lafin.abmaker.util.JsUtil;
import com.lafin.abmaker.util.PagingUtil;
import com.lafin.abmaker.util.TokenUtil;

@Controller
@RequestMapping(value="/member/*")
public class MemberController extends BaseController {
	
	// 회원관련 서비스
	@Autowired
	private MemberService memberService;
	
	// 공통 폴더
	private final String folder = "content/member/";
	
	public MemberController(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	@GetMapping(value="login")
	public String login(Model model) {
		super.init(model);
		
		if(loginCheck()) return "redirect:/";
		
		return folder + "login";
	}
	
	@PostMapping(value="login")
	public @ResponseBody String login_process(Model model, @RequestParam("user_email") String user_email, @RequestParam("user_pw") String user_pw, @RequestParam(name = "remember_me", required = false, defaultValue = "0") Integer remember_me) throws Exception{
		super.init(model);
		
		if(loginCheck()) return JsUtil.redirect("/", JsUtil.PARENT);
		
		logger.info("user_email : " + user_email);
		logger.info("user_pw : " + user_pw);
		
		Map result = memberService.login(user_email, user_pw);
		
		if(Integer.parseInt(result.get("code").toString()) == 200) {
			session.setAttribute("userInfo", (UserDto) result.get("userInfo"));
			
			// 자동로그인에 체크한 경우 쿠키 생성
			if(remember_me == 1) {
				setCookie("userInfo", TokenUtil.encrypt(user_email, Config.COOKIE_KEY));
			}
			
			return JsUtil.redirect("/", JsUtil.PARENT);
		}else {
			return JsUtil.openAlert(result.get("msg").toString(), "reload", JsUtil.PARENT);					
		}
	}
	
	@GetMapping(value="join")
	public String join(Model model) {
		super.init(model);
		
		if(loginCheck()) return "redirect:/";
		
		logger.info("Join In");
		
		return folder + "join";
	}
	
	@PostMapping(value="join")
	public String join_process(Model model, UserDto userDto) throws IOException{
		super.init(model);
		
		if(loginCheck()) return "redirect:/";
		
		Map result = memberService.join(userDto);
		
		logger.info("join result :: " + result.get("code").toString());
		logger.info("join msg :: " + result.get("msg").toString());
		
		return "redirect:/";
	}
	
	@GetMapping(value="findid")
	public String findID(Model model) {
		super.init(model);
		if(loginCheck()) return "redirect:/";
		
		return folder + "findId";
	}
	
	@PostMapping(value="check-id-count")
	public @ResponseBody Map<String, Object> checkIdCount(Model model, String user_email) {
		super.init(model);
		if(loginCheck()) return null;
		
		Map result = memberService.checkDuplicateByEmail(user_email);
		
		return result;
	}
	
	@GetMapping(value="logout")
	public String logout() {
		session.invalidate();
		deleteCookie("userInfo");
		return "redirect:/member/login";
	}
	
	
	// 로그인 체크해서 세션이 있는경우 메인으로 리다이렉트
	public boolean loginCheck() {
		if(session.getAttribute("userInfo") != null) {
			return true;
		}else {
			try {
				// 쿠키체크 추가
				if(ObjectUtils.isEmpty(cookie.get("userInfo"))) {
					return false;
				}
				
				logger.info(cookie.get("userInfo").toString());
				String userEmail = TokenUtil.decrypt(cookie.get("userInfo").toString(), Config.COOKIE_KEY);
				UserDto tmpUserInfo = memberService.getMemberInfo(userEmail);
				
				if(!ObjectUtils.isEmpty(tmpUserInfo)) {
					session.setAttribute("userInfo", tmpUserInfo);					
					return true;
				}else {
					return false;
				}
			}catch (Exception e) {
				e.printStackTrace();
				return false;
			}		 
		}
	}
	
}
