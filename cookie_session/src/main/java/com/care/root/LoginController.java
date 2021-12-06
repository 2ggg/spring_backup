package com.care.root;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	@GetMapping("login")
	public String login() {
		return "login/login";
	}
	@PostMapping("chkUser")
	public String chkUser(@RequestParam String id,
			@RequestParam String pwd,
			HttpSession session) {
		String db_id = "1", db_pwd="2", db_nick="홍길동";
		if(id.equals(db_id)&&pwd.equals(db_pwd)) {//로그인 성공
			session.setAttribute("loginId", db_id);
			session.setAttribute("loginPwd", db_pwd);
			session.setAttribute("loginNick", db_nick);
			return "redirect:main";
		}else {//로그인 실패
			return "redirect:login";
		}
		
	}
	
	@RequestMapping("main")
	public String main(HttpSession session) {//로그인 성공하면 들어올 수 있는 페이지
		if(session.getAttribute("loginId")!=null) {//session에 id가 존재하면
			return "login/main";
		}
		return "redirect:login";//id가 존재하지 않으면 다시 로그인 페이지로 이동
		
	}
	/*
	@RequestMapping("main")
	public void main(HttpSession session, HttpServletResponse response) {//로그인 성공하면 들어올 수 있는 페이지
		PrintWriter out = null;
		response.setContentType("text/html; charset=utf-8");
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(session.getAttribute("loginId")!=null) {
			out.print("<script>alert('로그인 성공')</script>");
		}
	}
	@RequestMapping("logout")
	public String logout(HttpSession session, HttpServletResponse response) {
		session.invalidate();//모든 세션 종료
		PrintWriter out = null;
		response.setContentType("text/html; charset=utf-8");
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print("<script>alert('로그아웃!');"+"location.href='login';</script>");
		return "login/logout";
	}
	*/
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();//모든 세션 종료
		return "login/logout";
	}
}
