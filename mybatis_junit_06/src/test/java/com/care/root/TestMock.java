package com.care.root;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.care.root.member.controller.MemberController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@RunWith(SpringRunner.class)//test 동작 시킬때 필요
@ContextConfiguration(locations= {"classpath:TestMember.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})//동작할 xml파일 연결
public class TestMock {
	@Autowired MemberController mc;
	MockMvc mock;
	
	@Before //test 전에 실행됨. 무언가 초기화 시키고 싶을때 많이 씀
	public void setUp() {//build : 실행. mc(컨트롤러)에서 주소를 찾는다.
		System.out.println("test 실행 전");
		mock = MockMvcBuilders.standaloneSetup(mc).build();
	}
	@Test
	public void testIndex() throws Exception {
		System.out.println("---test 실행");
		mock.perform(get("/index")) //get방식. 경로 앞에 무조건 /있어야함
		.andDo(print()) //상태를 쭉 보여주는 코드
		.andExpect(status().isOk())//200:성공. 200이 나오면 성공이다.
		.andExpect(forwardedUrl("member/index")); //리다이렉트 = 포워드 링크가 member/index다
	}
	@Test //했던 결과를 없던 것으로 되돌림
	@Transactional(transactionManager = "tsMgr")
	public void testInsert() throws Exception{
		mock.perform(post("/insert").param("id", "1234").param("name", "111이"))
		.andDo(print())
		.andExpect(status().is3xxRedirection());//302 : 리다이렉트, 즉 다른 위치로 이동하는 경로
	}
	@Test
	public void testMemberview() throws Exception {
	        mock.perform(get("/memberview")).andDo(print())//모든 사용자 데이터 가져와 출력,
	        .andExpect(status().isOk())//스테이터스가 200, 즉 성공인가 확인
	        .andExpect(forwardedUrl("member/memberview"))//이 경로로 이동하는게 맞나 확인
	        .andExpect(model().attributeExists("list"));//list라는 이름이 있나 확인
	}

}
