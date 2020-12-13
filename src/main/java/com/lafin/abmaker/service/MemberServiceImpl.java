package com.lafin.abmaker.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lafin.abmaker.dto.UserDto;
import com.lafin.abmaker.mapper.UserListMapper;

@Service("memberService")
public class MemberServiceImpl extends BaseService implements MemberService{
	
	@Autowired
	private UserListMapper userListMapper;
	
	@Override
	public Map login(String user_email, String user_pw) {
		Map result = new HashMap();
		
		try {
			UserDto userDto = userListMapper.getMember(user_email, user_pw);
			
			if(userDto != null) {
				
				switch(userDto.getUser_status()) {
					case "alive":						
						result.put("code", 200);
						result.put("userInfo", userDto);
						result.put("msg", "로그인 완료.");												
						break;
					case "dormant":						
						result.put("code", 300);
						result.put("msg", "휴면회원 입니다.");
						break;
					case "lock":						
						result.put("code", 301);
						result.put("msg", "비밀번호를 5회 이상 잘못 입력하셨습니다.");
						break;
					case "change":						
						result.put("code", 302);
						result.put("msg", "현재 비밀번호 재설정 요청 중입니다. 비밀번호를 재설정해주세요.");												
						break;
					case "delete":						
						result.put("code", 303);
						result.put("msg", "탈퇴 된 회원입니다.");												
						break;
				}
				
			}else {
				result.put("code", 400);
				result.put("msg", "회원정보가 없습니다. 아이디 또는 비밀번호를 확인해주세요.");
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			result.put("code", 500);
			result.put("msg", "일시적 오류 입니다. :: " + e.getMessage());
		}	
		
		return result;
	}

	@Override
	public Map join(UserDto param) {
		Map result = new HashMap();
		
		try {
			int memberCount = userListMapper.getMemberCountByEmail(param.getUser_email());
			
			if(memberCount > 0) {
				result.put("code", 400);
				result.put("msg", "이미 등롣 된 이메일입니다.");
			}else {
				
				// 유저상태 설정
				param.setUser_status("alive");
				int memberSetResult = userListMapper.setMember(param);
				
				if(memberSetResult > 0) {
					result.put("code", 200);
					result.put("msg", "회원가입이 정상적으로 처리 되었습니다.");
				}else {
					result.put("code", 501);
					result.put("msg", "회원가입이 실패하였습니다.");
				}			}
			
		}catch(Exception e) {
			e.printStackTrace();
			result.put("code", 500);
			result.put("msg", "일시적 오류 입니다. :: " + e.getMessage());
		}	
		
		return result;
	}

	@Override
	public Map findID(UserDto param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map findPW(UserDto param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map checkDuplicateByEmail(String user_email) {
		Map result = new HashMap();
		
		if(user_email.trim().equals("")) {
			result.put("code", 400);
			result.put("msg", "이메일을 입력하세요.");
			return result;
		}
		
		Integer count = userListMapper.getMemberCountByEmail(user_email);
		
		logger.info("member count :: " + count);
		
		if(count == 0) {
			result.put("code", 200);
			result.put("msg", "사용 가능한 아이디입니다.");
		}else {
			result.put("code", 500);
			result.put("msg", "이미 등록 된 아이디입니다.");
		}
		
		return result;
	}

	@Override
	public UserDto getMemberInfo(String user_email) {
		return userListMapper.getMemberByEmail(user_email);
	}
	
	
	
}
