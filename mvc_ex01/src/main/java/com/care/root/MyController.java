package com.care.root;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller //파일과 url 경로 설정
public class MyController {
	@RequestMapping(value="index")//url경로
	public String memberIndex() {
		return "member/index";//파일경로 설정
		//index를 요청하면 views/member/index 파일이 자동으로 뜸
	}
	@RequestMapping("logout")
	public String memberLogout(Model model) {//model객체 주입
		model.addAttribute("key", "로그아웃 되었습니다.");
		return "member/logout";
	}
	@RequestMapping("/login")
	public ModelAndView memberLogin() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("login","로그인 성공!");
		mv.setViewName("member/login");
		return mv;
	}
}
