package com.care.root.member.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.care.root.common.MemberSessionName;

public class MemberInterceptor 
extends HandlerInterceptorAdapter implements MemberSessionName{

	@Override	//포스트핸들, 프리핸들 클릭하면 자동 생성
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession(); //세션 얻어옴
		
		if(session.getAttribute(LOGIN)==null) {
			//response.sendRedirect("login");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script> alert('로그인 하시오');"
				//+"location.href='/root/member/login'; </script>");
			+"location.href='"+request.getContextPath()+"/member/login'; </script>");	//로그인페이지로 넘어감
			
			return false;	//false면 impl로 연결되지 않음
		}
		
		System.out.println("index 실행 전 실행");
		return true; //true면 impl로 연결됨
	}

	@Override	//포스트핸들, 프리핸들 클릭하면 자동 생성
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
				System.out.println("index(컨트롤러) 끝나고 실행" );
	}
}
