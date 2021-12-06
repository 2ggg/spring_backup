package com.care.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.member.dto.MemberDTO;
import com.care.root.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	MemberDAO dao;
	public MemberServiceImpl() {
		System.out.println("service 생성자 실행");
	}
	public void test() {
		System.out.println("dao: "+dao);
	}
	@Override
	public void register(String id, String pwd, String name) {
		MemberDTO dto = new MemberDTO();
		dto.setId(id);
		dto.setPwd(pwd);
		dto.setName1(name);
		dao.register(dto);
	};
}
