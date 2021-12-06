package com.care.root.member.service;

import org.springframework.ui.Model;

public interface MemberService {
	public void test();
	public void register(String id, String pwd, String name);
	public void memberList(Model model);
	public void check(String id, String pwd, Model model);
	public void memberInfo(String id,Model model);
}
