package com.care.member.service;

import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

@Repository
public interface MemberService {
	public void test();
	public void register(String id, String pwd, String name);
	public void memberList(Model model);
}
