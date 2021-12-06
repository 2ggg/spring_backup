package com.care.get_post;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class MyController {
	public MyController() {
		System.out.println("MyController 생성자 실행");
	}
	@RequestMapping(value="my/index", method= RequestMethod.GET)//root/my/index의 get으로 들어오는 값 처리해줌
	public String index() {
		System.out.println("index get방식");
		return "/get_post/index";
	}//@GetMapping("my/index")와 같음
	
	@GetMapping("my/result") //get방식으로 받겠다
	public String result_get(HttpServletRequest request, Model model) {
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		
		System.out.println("name:"+name);
		System.out.println("age:"+age);
		
		System.out.println("result get방식");
		
		model.addAttribute("method", request.getMethod());
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		return "/get_post/result";
	}
	
	@RequestMapping(value="my/result", method= RequestMethod.POST)//root/my/result의 post로 들어오는 값을 처리해줌
	public String result_post(HttpServletRequest request, Model model,
			 @RequestParam("name") String name,
			 @RequestParam("age") String age) {
		// @RequestParam("name") String name : String name = request.getParameter("name");
		//String age = request.getParameter("age");
		System.out.println("result post방식");
		
		model.addAttribute("method", request.getMethod());
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		
		return "/get_post/result";//
	}//@PostMapping("my/result")와 같음
}
