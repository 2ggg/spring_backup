package com.care.root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class MemberController {
	@Autowired //자료형을 보고 알아서 객체(빈) 주입해줌
	@Qualifier("ms1")//빈이 여러개일 때, 그 중 넣을 것을 하나만 지정해줌
	MemberService ms;
	
	@RequestMapping("insert")
	public String insert(Model model) {
		//ms = new MemberService();
		int result = ms.insert();
		System.out.println("ms: "+ms);
		model.addAttribute("result", result);
		return "di/index";
	}

}
