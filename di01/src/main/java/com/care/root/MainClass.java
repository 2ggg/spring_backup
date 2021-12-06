package com.care.root;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		String path = "classpath:applicationST.xml"; //src 리소스 경로(classpath)
		GenericXmlApplicationContext ctx =
				new GenericXmlApplicationContext(path);//해당 컨테이너(xml) 정보 가져와줌
		STBean stBean = ctx.getBean("stb", STBean.class); //stb라는 객체를 자료형은 STBean.class에서 가져옴
		
		
		//STBean stBean = new STBean();
		//stBean.setName("홍길동");
		//stBean.setAge(20);
		
		//stBean.setSt(new Student());
		stBean.namePrint();
		stBean.agePrint();
	}
}
