package com.care.root;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController //데이터만 넘겨줌
//post(추가), get(요청), delete(삭제), put(업데이트) 종류가 있음

public class TestRestController {
	static int cnt = 0;
	static Map<String, InfoDTO> DBMap = new HashMap<String, InfoDTO>();
	
	@GetMapping(value="users", produces = "application/json; charset=utf-8")
	public ArrayList<InfoDTO> users(){
		ArrayList<InfoDTO> list = new ArrayList<InfoDTO>();
		for(int i=cnt; i<cnt+10; i++) {//데이터 베이스 예시로 list를 만들어준 것 뿐
			InfoDTO info = new InfoDTO();
			info.setName("홍길동"+i);
			info.setAge(10+i);
			list.add(info);
			DBMap.put("홍길동"+i, info);
		}
		cnt+=10;
		return list;
	}
	
	@PutMapping(value="modify", produces = "application/json; charset=utf-8")
	public InfoDTO modify(@RequestBody InfoDTO dto) {
		//update member set age=? where name=?
		DBMap.replace(dto.getName(), dto);
		return DBMap.get(dto.getName());
	}
	
	@GetMapping(value="user", produces = "application/json; charset=utf-8")
	public InfoDTO user(@RequestParam String id) {//id 검색해서 정보찾기
		//파라미터 넘어올때
		return DBMap.get(id);
		//select * from member where id=id; 대신 쓰는 것
	}
	@GetMapping(value="user/{name}", produces = "application/json; charset=utf-8")
	public InfoDTO user1(@PathVariable String name) {//id 검색해서 정보찾기
		//@PathVariable : 경로로 넘어올때 사용. {name}을 꺼내줌
		return DBMap.get(name);
		//select * from member where id=id; 대신 쓰는 것
	}
	
	@PostMapping(value="membership", produces = "application/json; charset=utf-8")
	public String membership(@RequestBody Map<String, Object> member) {
		System.out.println("uId");
		System.out.println("uName");
		System.out.println("uAge");
		System.out.println("uAddr");
		System.out.println("uPhone");
		return "{\"test\":true}";
	}
	
	
	
	@GetMapping(value="rest", produces = "application/json; charset=utf-8")
	public String get() {
		return "{\"execute\" : \"get 데이터 요청할 때 사용\"}";
		//키:값 / execute(키) : "get 데이터 요청할 때 사용"(값)
		//이렇게 페이지가 아니라 데이터만 돌려줌(responsebody가 생략됐다고 생각하면 됨)
	}
	@PostMapping(value="rest", produces = "application/json; charset=utf-8")
	public String post() {
		return "{\"execute\" : \"post 데이터 추가할 때 사용\"}";
		//이렇게 페이지가 아니라 데이터만 돌려줌(responsebody가 생략됐다고 생각하면 됨)
	}
	@PutMapping(value="rest", produces = "application/json; charset=utf-8")
	public String put() {
		return "{\"execute\" : \"put 데이터 수정할 때 사용\"}";
		//이렇게 페이지가 아니라 데이터만 돌려줌(responsebody가 생략됐다고 생각하면 됨)
	}
	@DeleteMapping(value="rest", produces = "application/json; charset=utf-8")
	public String delete() {
		return "{\"execute\" : \"delete 데이터 삭제할 때 사용\"}";
		//이렇게 페이지가 아니라 데이터만 돌려줌(responsebody가 생략됐다고 생각하면 됨)
	}
}