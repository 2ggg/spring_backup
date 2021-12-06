package com.care.root;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.care.root.member.controller.MemberController;
import com.care.root.member.dao.MemberDAO;
import com.care.root.member.dto.MemberDTO;
import com.care.root.member.service.MemberService;

@RunWith(SpringRunner.class)//test 동작 시킬때 필요
@ContextConfiguration(locations= {"classpath:TestMember.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})//동작할 xml파일 연결

public class TestMember {
	@Autowired MemberController mc;//자동 주입
	@Autowired MemberService ms;//자동 주입
	@Autowired MemberDAO dao;//자동 주입
	
	@Test
	public void testMc() {
		System.out.println("mc : "+mc);
		assertNotNull(mc); //mc가 null이 아니면 성공
	}
	@Test
	public void testMs() {
		System.out.println("ms : "+ms);
		assertNotNull(ms); //mc가 null이 아니면 성공
	}
	@Test
	public void testDao() {
		assertNotNull(dao); //mc가 null이 아니면 성공
	}
	@Test
	public void testDaoIns() {
		MemberDTO dto = new MemberDTO();
		dto.setId(111); 
		dto.setName("송ㅇ숑길"); 
		dao.insertMember(dto);
	}
	@Test
	public void testServiceIns() {
		MemberDTO dto = new MemberDTO();
		dto.setId(222); 
		dto.setName("고길동"); 
		ms.insertMember(dto);
	}
}
