package com.care.root.member.controller;

import java.sql.Date;
import java.util.Calendar;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
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
	@PostMapping("/user_check")	//로그인 성공 체크
	public String userCheck(
			@RequestParam String id,
			@RequestParam String pw,
			@RequestParam(required=false) String autoLogin,
			RedirectAttributes rs) {//required=false : 값이 없으면 null로 됨(값 없어도 오류 없음)
		int result= ms.userCheck(id, pw); //0성공, 1실패
		System.out.println("usercheck / autoLogin : "+autoLogin);	//체크되면 string으로 on이 들어옴
		if(result==0) {//0이면 로그인 성공
			rs.addAttribute("id",id);
			rs.addAttribute("autoLogin",autoLogin);
			return "redirect:successLogin";
		}else {//0이 아니면 실패
			return "redirect:login";	//member/login
		}
	}
	/*
	@GetMapping("/successLogin")	//로그인 성공했을 때 넘어옴 '/'넣어도 안 넣어도 상관 없음
	public String successLogin(@RequestParam String id, HttpSession session) {
		session.setAttribute("loginUser", id);
		return "member/successLogin";
	}
	*/
	@GetMapping("/successLogin")	//로그인 성공했을 때 넘어옴 '/'넣어도 안 넣어도 상관 없음
	public String successLogin(@RequestParam String id, 
			@RequestParam(required=false) String autoLogin,
			HttpSession session,
			HttpServletResponse response) {
		System.out.println("id : "+id);
		System.out.println("seccessLogin / autoLogin : "+autoLogin);	//체크되면 string으로 on이 들어옴
		session.setAttribute(MemberSessionName.LOGIN, id);	//loginUser = MemberSessionName.LOGIN
		
		if(autoLogin != null) {
			int limitTime = 60*60*24*90; //90일 후 쿠키만료
			Cookie loginCookie = new Cookie("loginCookie", session.getId());
			loginCookie.setPath("/");
			loginCookie.setMaxAge(limitTime);	//만료시간 세팅
			response.addCookie(loginCookie);
			
			Calendar cal = Calendar.getInstance();	//정확히 90일이 들어가려면 캘린더를 사용하는게 좋음
			cal.setTime(new java.util.Date());	//캘린더 세팅(util)
			cal.add(Calendar.MONTH, 3);	//지금부터 3달뒤(쿠키 만료 날자)
			
			Date limitDate = new Date(cal.getTimeInMillis()); //3개월(sql에 값 넣어줄때 씀)
			ms.keepLogin(session.getId(), limitDate, id);	//session.getId : 웹에서 자동으로 생성되는 세션id 얻어옴
		}
		return "member/successLogin";
	}

	@GetMapping("logout")
	public String logout(HttpSession session,
			HttpServletResponse response,
			@CookieValue(value="loginCookie", required = false) Cookie loginCookie) {
		if(session.getAttribute(LOGIN)!=null) {	//loginUser = MemberSessionName.LOGIN = LOGIN
			if(loginCookie != null) {
				
				loginCookie.setPath("/"); //쿠키를 어디까지 허용해줄 것인지 경로 설정. /의 하위로 모두 적용됨
				//최상위로 잡아둬야 전부 적용됨. 아니면 현재 페이지 및 그 하위 에서만 적용됨
				loginCookie.setMaxAge(0); //0분으로 바꿔서 쿠키 삭제
				response.addCookie(loginCookie);
				
				ms.keepLogin("nan",
						new java.sql.Date(System.currentTimeMillis()),
						(String)session.getAttribute(LOGIN)); 
				//nan : 값이 없을때 자동으로 세팅되는 값(오라클에 그렇게 세팅해뒀음)
				//System.currentTimeMillis() : 현재시간. 만료기간을 현재로 둠
				//이 sessionId가져와서 nan으로 바꾸고 쿠키 없애라
			}
		}
		session.invalidate();	//세션 모두 종료
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
