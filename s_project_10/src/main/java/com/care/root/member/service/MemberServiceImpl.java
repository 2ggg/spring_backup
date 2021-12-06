package com.care.root.member.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.board.dto.BoardDTO;
import com.care.root.board.service.BoardFileService;
import com.care.root.board.service.BoardFileServiceImpl;
import com.care.root.common.MemberSessionName;
import com.care.root.member.dto.MemberDTO;
import com.care.root.mybatis.member.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired MemberMapper mapper;
	
	BCryptPasswordEncoder encoder;
	
	public MemberServiceImpl() {
		encoder = new BCryptPasswordEncoder();
	}
	
	public int userCheck(String id,String pw) {
		MemberDTO dto = mapper.userCheck(id);
		if(dto != null) {
			//if(pw.equals(dto.getPw())) {
			if(encoder.matches(pw, dto.getPw()) || pw.equals(dto.getPw()) ) {
				return 0;
			}
		}
		return 1;
	}
	public void memberInfo(Model model) {
		model.addAttribute("memberList", mapper.memberInfo() );
	}
	public void info(Model model, String id) {
		model.addAttribute( "info",mapper.userCheck(id) );
	}
	public int register(MemberDTO dto) {
		//System.out.println("비번 변경 전 : "+dto.getPw());
		String securePw = encoder.encode(dto.getPw());
		//System.out.println("비번 변경 후 : "+securePw);
		
		dto.setPw(securePw);
		
		int result = 0;
		try {
			result = mapper.register(dto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public void keepLogin(String sessionId, Date limitDate, String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sessionId", sessionId);
		map.put("limitDate", limitDate);
		map.put("id", id);
		mapper.keepLogin(map);
	}
	public MemberDTO getUserSessionId(String sessionId) {
		return mapper.getUserSessionId(sessionId);
	}
	public String writeSave(MultipartHttpServletRequest mul, 
            HttpServletRequest request) {
    BoardDTO dto = new BoardDTO();
    dto.setTitle( mul.getParameter("title") );
    dto.setContent( mul.getParameter("content") );
    HttpSession session = request.getSession();
    dto.setId((String)session.getAttribute(MemberSessionName.LOGIN));
    //세션 통해서사용자 id 가져와라 또는 jsp에서 id넘겨저도 됨

    MultipartFile file = mul.getFile("image_file_name");
    BoardFileService bfs = new BoardFileServiceImpl();
    if(file.getSize() != 0) {
   //이미지 있을경우 처리
    }else {
    	dto.setImageFileName("nan"); //파일 없으면 nan으로 저장
    }
    return bfs.getMessage(mapper.writeSave(dto), request);//mapper로 보내주겠다
    //mapper.writeSave(dto) : 1/0으로 성공실패 여부 결과 들어옴
}

}








