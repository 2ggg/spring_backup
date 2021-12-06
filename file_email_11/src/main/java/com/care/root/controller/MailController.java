package com.care.root.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.care.root.service.MailService;

@Controller
public class MailController {
	@Autowired MailService ms;
	@GetMapping("sendmail")
	public void sendMail(HttpServletResponse response) throws Exception{
		
		StringBuffer sb = new StringBuffer(); //StringBuffer가 조금 더 빠름
		sb.append("<h1>제품소개</h1>");
		sb.append("<a href=\"https://tv.naver.com/\">");
		sb.append("<img alt=\"\" src=\"https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMTEwMjZfMTYg%2FMDAxNjM1MjU1Njc3NDk1.eDwtEzGKgHI6BWZXeEvME2PFvtv7UtlvCuXy9R8okRsg.z7e2lfCF6NHQq8xreu_Vst8hFTfiKLDYgxB-WiSHWTAg.JPEG.gkdi999%2FIMG_5964.jpg&type=sc960_832\">");
		sb.append("</a>");
		
		String msg = sb.toString(); //string으로 변환
		
		ms.sendMail("ljh03922@naver.com", "<제목>광고", msg); //받는 사람에 관한 내용
		//ms.sendMail("ljh03922@naver.com", "<제목>test", "<내용>test로 보내보는 메일"); //받는 사람에 관한 내용
		
		response.setContentType("text/html; charset=utf-8"); //응답 타입
		PrintWriter out = response.getWriter(); //그냥 확인용으로 만들어둠
		out.print("메일이 전송되었습니다.");
	}
	
	@GetMapping("auto_form")
	public String authForm() {
		return "auto";
	}
	@GetMapping("auth")
	public String auth(HttpServletRequest request) {
		ms.auth(request);
		return "redirect:https://www.naver.com/";
	}
	@GetMapping("auth_check")
	public String authCheck(@RequestParam String userid,
							@RequestParam String userkey,
							HttpSession session) {
		//인증을 위해 서버가 갖고 있는 사용자 id와, 세션 생성
		String sessionKey = (String)session.getAttribute(userid);
		if(sessionKey.equals(userkey)) {
			session.setAttribute("userid", userid);
		}
		return "redirect:auth_check";
	}
	
}
