package com.care.root;

import java.util.ArrayList;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {
	//@Autowired
	@Inject
	@Qualifier("memberService")
	MemberService ms;
	
	@RequestMapping("insert")
	public String insert(Model model) {
		//ms = new MemberService();
		int result = ms.insert();
		System.out.println("ms : "+ms);
		model.addAttribute("result", result);
		
		return "di/index";
	}
}






