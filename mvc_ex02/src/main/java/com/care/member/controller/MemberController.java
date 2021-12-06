package com.care.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.care.member.dto.MemberDTO;
import com.care.member.service.MemberService;
@RequestMapping("member")//member를 앞에 자동으로 붙여줌
public class MemberController {
	@Autowired
	MemberService ms;
	public MemberController() {
		System.out.println("Controller 생성자 실행");
	}
	@RequestMapping("test")
	public void test() {
		System.out.println("ms: "+ms);
		ms.test();
	}
	@GetMapping("/index1")//member/index
	public String memberIndex() {
		return "member/index";
	}
	@GetMapping("/register_view")//@RequestMapping로 인해 member생략 가능. member/register_view
	public String registerView() {
		return "member/register_view";
	}
	@GetMapping("/member_list")//member/member_list
	public String memberList(Model model) {
		ms.memberList(model);
		
		return "member/member_list";
	}
	@PostMapping("register")
	public String register(@RequestParam("id") String id,
			@RequestParam String pwd,
			@RequestParam String name) {
		System.out.println(id);
		System.out.println(pwd);
		System.out.println(name);
		
		ms.register(id,pwd,name);
		
		return "redirect:/member/index";//redirect 쓸 때 경로 주의. /를 꼭 넣어야함
	}
	@PostMapping("register1")
	public String register1(HttpServletRequest req) {
		System.out.println(req.getParameter("id"));
		System.out.println(req.getParameter("pwd"));
		System.out.println(req.getParameter("name"));
		
		ms.register(req.getParameter("id"),req.getParameter("pwd"),req.getParameter("name"));
		
		return "redirect:/member/index";//redirect 쓸 때 경로 주의. /를 꼭 넣어야함
	}
	@PostMapping("register2")
	public String register2(MemberDTO dto) {
		System.out.println("dto.id: "+dto.getId());
		System.out.println("dto.pwd: "+dto.getPwd());
		System.out.println("dto.name: "+dto.getName1());
		
		ms.register(dto);
		
		return "redirect:/member/index";//redirect 쓸 때 경로 주의. /를 꼭 넣어야함
	}
}
