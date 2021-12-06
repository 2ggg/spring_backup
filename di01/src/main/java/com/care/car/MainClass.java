package com.care.car;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		String path = "classpath:applicationST.xml";
		GenericXmlApplicationContext ctx =
				new GenericXmlApplicationContext(path);
		Car car = ctx.getBean("car", Car.class);
		
		//Car car = new Controller02();
		System.out.println("메인에서 속력을 냅니다.");
		car.speed();
		SubClass sc = new SubClass();
		sc.subFunc();
	}
}
