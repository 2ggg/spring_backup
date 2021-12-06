package com.care.root.member.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.care.root.member.dto.MemberDTO;
import com.care.root.member.dto.UserCheckDTO;

@Repository
public class MemberDAO {
	ArrayList<MemberDTO> list;
	public MemberDAO() {
		System.out.println("dao 생성자 실행");
		list = new ArrayList<MemberDTO>();
	}
	public void register(MemberDTO dto) {
		list.add(dto);
	}
	public ArrayList<MemberDTO> memberList(){
		return list;
	}
	public UserCheckDTO check(String id, String pwd) {
		UserCheckDTO dto = new UserCheckDTO();
		if(list.size() != 0) {
			for(MemberDTO m : list) {
				if(id.equals(m.getId())) {
					if(pwd.equals(m.getPwd())) {
						dto.setChk(0); //로그인 성공
						dto.setName(m.getName());
						return dto;
					}
					dto.setChk(1); // 비밀번호 틀림
				}
			}
		}
		if(dto.getChk() == 0) dto.setChk(-1); // 아이디 없음
		return dto;
	}
	public MemberDTO memberInfo(String id) {
		for(MemberDTO dto : list) {
			if(dto.getId().equals(id)) {
				return dto;
			}
		}
		return null;
	}
}








