package com.care.root.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.root.member.dto.MemberDTO;
import com.care.root.mybatis.member.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired MemberMapper mapper;
	
	public int userCheck(String id, String pwd) {
		MemberDTO dto = mapper.userCheck(id);
		if(dto != null) {
			if(pwd.equals(dto.getPwd())) {
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
}
