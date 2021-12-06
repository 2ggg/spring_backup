package com.care.root.board.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service
public class BoardFileServiceImpl implements BoardFileService{
	public String saveFile(MultipartFile file) {
		SimpleDateFormat simpl = new SimpleDateFormat("yyyyMMddHHmmss-");
		//날짜
		Calendar calendar = Calendar.getInstance();
		String sysFileName = 
				simpl.format(calendar.getTime()) + file.getOriginalFilename();
		//날짜+파일이름
		File saveFile = new File(IMAGE_REPO+"/"+sysFileName);
		//파일이 저장될 이름 설정
		try {
			file.transferTo(saveFile);//해당 위치에 파일 저장
		}catch (Exception e) {
			e.printStackTrace();
		}
		return sysFileName;//다운로드하는 파일 이름(날짜 포함)
	}

	public String getMessage(int num, HttpServletRequest request) {
		String message = null;
		if(num == 1) {
			message = "<script>alert('새글을 추가하였습니다.');";
			message += "location.href='"+request.getContextPath()+
					"/board/boardAllList'</script>";
		}else {
			message = "<script>alert('문제가 발생하였습니다.');";
			message += "location.href='"+request.getContextPath()+
					"/board/writeForm'</script>";
		}
		return message;
	}
	public void deleteImage(String originFileName) {
		//originFileName : 파일 이름
		File deleteFile = new File(IMAGE_REPO+"/"+originFileName);
		//오리진 이름 삭제(새 이름으로 저장하기 위해)
		deleteFile.delete();
	}

	public String getMessage(HttpServletRequest request,
							String msg, String url) {
		String message = null;
		String path = request.getContextPath();
		
			message = "<script>alert('"+msg+"');";
			message += "location.href='"+path +url+"'</script>";
			
		return message;
	}
	
	
}
