package com.care.root.member.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.root.member.dto.MemberDTO;
import com.care.root.mybatis.member.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired MemberMapper mapper;
	BCryptPasswordEncoder encoder; //패스워드 암호화 인코더
	
	public MemberServiceImpl() {encoder = new BCryptPasswordEncoder();}
	
	public int userCheck(String id, String pwd) {//아이디 비번 일치 확인
		MemberDTO dto = mapper.userCheck(id);
		if(dto != null) {
			if(encoder.matches(pwd, dto.getPwd()) || pwd.equals(dto.getPwd()) ) {
				//encoder.matches(pwd, dto.getPwd()) : 사용자가 입력한 비번, 암호화된 비번을 넣어 비교
				return 0;	//로그인성공
			}
		}return 1;	//로그인 실패
	}
	
	public void memberInfo(Model model) {//jsp로 값을 전달하려면 model을 이용해야함
		model.addAttribute("memberList", mapper.memberInfo()); //array list형태로 돌려줌
	}
	
	public void info(Model model, String id) {
		model.addAttribute("info", mapper.userCheck(id));
	}
	
	public int register(MemberDTO dto) {
		
		System.out.println("비번 변경 전 : "+dto.getPwd());
		String securePw = encoder.encode(dto.getPwd());	//비번에 암호화 작업한 것
		System.out.println("비번 변경 후: "+securePw);
		
		dto.setPwd(securePw); 
		//비번들을 모두 암호화 시킴. db에도 암호화돼 저장되고, 개발자도 확인 못함. 
		//암호화시 60글자로 대체됨. 때문에 table 생성시 varchar2(100byte) 정도로 만들어야 오류 안남
		
		int result =0;
		String msg = "";
		try {
			msg = "회원가입에 성공했습니다.";
			result = mapper.register(dto);
			
		}catch(Exception e){
			msg = "동일한 아이디가 존재합니다.";
			e.printStackTrace();
		}
		
		return result;
	}
	public void keepLogin(String sessionId, Date limitDate, String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sessionId", sessionId);
		map.put("limitDate", limitDate);
		map.put("id", id);
		mapper.keepLogin(map);
	}

	public MemberDTO getUserSessionId(String sessionId) {
		return mapper.getUserSessionId(sessionId);
	}
}
