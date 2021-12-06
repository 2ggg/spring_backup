package com.care.root;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@GetMapping("non_ajax")
	public String nonAjax() {
		System.out.println(">>non ajax 실행");
		return "non_ajax";
	}
	
	@GetMapping("ajax")
	public String ajax() {
		System.out.println(">>ajax 실행");
		return "ajax";
	}
	
	static int cnt = 0;	//좋아요 누르면 숫자 카운트 되는거 구현하기 위한 변수
	@GetMapping("ajax_result")
	@ResponseBody 
	//ajax쓸때 무조건 필요. 돌려주는 값 설정. 전체가 아닌 여기 적혀있는 데이터만 돌려주겠다.
	//이게 없으면 페이지 전체가 응답됨
	public String ajaxResult() {
		System.out.println(">>숫자 증가 : "+(cnt+1));
		return ++cnt + ""; 
		//""를 더해줘서 문자열로 형변환. int로 리턴하면 에러남(찾아보면 방법이 있을지도?)
	}
	
	@GetMapping("ajax01")
	public String ajax01() {
		return "ajax01";
	}
	/*
	@PostMapping(value="ajax_result01",produces="application/json; charset=utf-8")
	@ResponseBody
	public InfoDTO ajaxResult01(@RequestBody InfoDTO dto) {
		System.out.println("이름: "+dto.getName());
		System.out.println("나이: "+dto.getAge());
		System.out.println("주소: "+dto.getAddr());
		dto.setMsg("해당 아이디는 사용 가능합니다.");//아이디 중복 확인기능 넣어주면 될듯
		return dto;
	}*/
	
	@PostMapping(value="ajax_result01",produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> ajaxResult01(@RequestBody Map<String, Object> dto) {//key, value 존재하니까 map도 사용 가능
		System.out.println("이름: "+dto.get("name"));
		System.out.println("나이: "+dto.get("age"));
		System.out.println("주소: "+dto.get("addr"));
		return dto;
	}
	
	@GetMapping("rest01")
	public String rest01() {
		return "rest01";
	}
	
	@GetMapping("getuser")
	public String getUser() {
		return "getuser";
	}
	
	
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
}
