package com.care.root.mybatis.member;

import java.util.ArrayList;

import com.care.root.member.dto.MemberDTO;

public interface MemberMapper {

	public MemberDTO userCheck(String id); //id를 가져와 MemberDTO 형태로 돌려줘라
	public ArrayList<MemberDTO> memberInfo();
	public int register(MemberDTO dto);
}
