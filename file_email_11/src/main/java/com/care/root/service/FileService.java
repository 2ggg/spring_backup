package com.care.root.service;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface FileService {
	public static final String IMAGE_REPO = "C:/spring/image_repo";	
	//IMAGE_REPO : 이미지 저장소를 지정. 경로 쓸때 \\=/ 같음
	public void fileProcess(MultipartHttpServletRequest mul);
	public void getShoesImage(Model model);
}
