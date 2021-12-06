package com.care.root02;

public class MainClass {
	public static void main(String[] args) {
		STBean stBean = new STBean();
		Student st01 = new Student();
		
		stBean.setSt(st01);
		
		stBean.namePrint();
		stBean.agePrint();
	}
}
