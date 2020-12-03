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
public class MemberServiceImpl implements MemberService{
	
	// 로거 설정
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserListMapper userListMapper;
	
	@Override
	public Map login(UserDto param) {
		Map result = new HashMap();
		
		try {
			result.put("code", 200);
			result.put("msg", "회원가입이 정상적으로 처리 되었습니다.");
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
			int memberCount = userListMapper.getMemberCountById(param.getUser_id());
			
			if(memberCount > 0) {
				result.put("code", 400);
				result.put("msg", "이미 등롣 된 아이디입니다.");
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
	public Map checkDuplicate(String user_id) {
		Map result = new HashMap();
		
		if(user_id.trim().equals("")) {
			result.put("code", 400);
			result.put("msg", "아이디를 입력하세요.");
			return result;
		}
		
		Integer count = userListMapper.getMemberCountById(user_id);
		
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
	
	

	
	
}
