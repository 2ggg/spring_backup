package com.care.root.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.root.member.dao.MemberDAO;
import com.care.root.member.dto.MemberDTO;
import com.care.root.member.dto.UserCheckDTO;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired MemberDAO dao;
	public MemberServiceImpl() {
		System.out.println("service 생성자 실행");
	}
	public void test() {
		System.out.println("dao : "+dao);
	}
	@Override
	public void register(String id, String pwd, String name) {
		MemberDTO dto = new MemberDTO();
		dto.setId(id); dto.setPwd(pwd); dto.setName(name);
		dao.register(dto);
	}
	@Override
	public void memberList(Model model) {
		model.addAttribute("list", dao.memberList() );
	}
	
	
	public void check(String id, String pwd, Model model) {
		UserCheckDTO dto = dao.check(id, pwd);
		model.addAttribute("usercheck",dto);
	}
	public void memberInfo(String id,Model model) {
		model.addAttribute("member",dao.memberInfo(id) );
	}
}















