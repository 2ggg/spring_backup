package com.care.root.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.care.root.common.MemberSessionName;
import com.care.root.member.dto.MemberDTO;
import com.care.root.member.service.MemberService;

@Controller
@RequestMapping("member")//domain에 입력시 member를 기본 최상위 경로로 설정
public class MemberController implements MemberSessionName{//LOGIN 이름 쓰기 위해 상속
	@Autowired MemberService ms;//서비스 연결
	@GetMapping("/login")
	public String login() {
		System.out.println(">>member login 연결");
		return "member/login";
	}
	@PostMapping("/user_check")
	public String userCheck(
			@RequestParam String id,
			@RequestParam String pw,
			RedirectAttributes rs) {
		int result= ms.userCheck(id, pw); //0성공, 1실패
		if(result==0) {//0이면 로그인 성공
			rs.addAttribute("id",id);
			return "redirect:successLogin";
		}else {//0이 아니면 실패
			return "redirect:login";	//member/login
		}
	}
	/*
	@GetMapping("/successLogin")	// '/'넣어도 안 넣어도 상관 없음
	public String successLogin(@RequestParam String id, HttpSession session) {
		session.setAttribute("loginUser", id);
		return "member/successLogin";
	}
	*/
	@GetMapping("/successLogin")	// '/'넣어도 안 넣어도 상관 없음
	public String successLogin(@RequestParam String id, HttpSession session) {
		session.setAttribute(MemberSessionName.LOGIN, id);	//loginUser = MemberSessionName.LOGIN
		return "member/successLogin";
	}

	@GetMapping("logout")
	public String logout(HttpSession session) {
		if(session.getAttribute(LOGIN)!=null) {	//loginUser = MemberSessionName.LOGIN = LOGIN
			session.invalidate();	//세션 모두 종료
		}
		return "redirect:/index"; //그냥 index만 적으면 member/index로 연결돼서 /를 넣어야 member가 빠짐
	}
	@GetMapping("memberInfo")
	public String memberInfo(Model model, HttpSession session) {	//data 접근
		//if(session.getAttribute(LOGIN) != null) {//로그인 돼있나 확인
			ms.memberInfo(model);
			return "member/memberInfo";
		//}
		//return "redirect:login";
	}
	@GetMapping("info")
	public String info(@RequestParam String id, Model model) {
		ms.info(model, id);
		return "member/info";
	}
	@GetMapping("register_form")
	public String registerForm() {
		return "member/register";
	}
	@PostMapping("register")
	public String register(MemberDTO dto) {
		int result = ms.register(dto);
		if(result==1) {
			return "redirect:login";
		}else {
			return "redirect:register_form";
		}
	}
}
