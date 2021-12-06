package com.care.car;

import org.springframework.context.support.GenericXmlApplicationContext;

public class SubClass {
	public void subFunc() {
		String path = "classpath:applicationST.xml";
		GenericXmlApplicationContext ctx =
				new GenericXmlApplicationContext(path);
		Car car = ctx.getBean("car", Car.class);
		
		//Car car = new Controller02();
		System.out.println("subFunc에서 속력 내기");
		car.speed();
	}
}
