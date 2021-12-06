package com.care.root.board.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface BoardFileService {
	public static final String IMAGE_REPO = "D:/2Gwork/test";
	//파일 저장할 경로
	public String getMessage(int num, HttpServletRequest request);
	public String saveFile(MultipartFile file);
	public void deleteImage(String imageFileName);
	public String getMessage(HttpServletRequest request,
							String msg, String url);
}
