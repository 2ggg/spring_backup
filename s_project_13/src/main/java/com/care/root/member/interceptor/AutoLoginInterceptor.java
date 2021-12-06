package com.care.root.member.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.care.root.common.MemberSessionName;
import com.care.root.member.dto.MemberDTO;
import com.care.root.member.service.MemberService;

public class AutoLoginInterceptor extends HandlerInterceptorAdapter implements MemberSessionName{
	@Autowired MemberService ms;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//request.getCookies();
		Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");	//쿠키 가져옴
		System.out.println("loginCookie : "+loginCookie);
		
		if(loginCookie != null) {
			MemberDTO dto = ms.getUserSessionId(loginCookie.getValue());	//로그인된 sessionId가져옴
			if(dto != null) {
				request.getSession().setAttribute(LOGIN, dto.getId());//자동로그인 체크하면 세션을 만들어줌
			}
		}
		return true;//쿠키 없으면 index페이지로 연결
	}
}
