package com.care.root.controller;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.care.root.service.FileService;

@Controller
public class FileDownloadController {
	@GetMapping("download")
	public void download(@RequestParam("file") String fileName,
							HttpServletResponse response) throws Exception{
		
		response.addHeader("Content-disposition", "attachment; fileName="+fileName); 
		//content-disposition : 파일을 다운로드하겠다
		//addHeader:파일을 응답하는 방식 설정. 파일을 다운로드 하는 것으로 응답하겠다
		//attachment : 파일을 다운로드해 브라우저로 표현하겠다
		// fileName="+fileName: 다운로드 되는 파일이름은 이걸로 설정해라
		
		File file = new File(FileService.IMAGE_REPO+"/"+fileName);
		FileInputStream input = new FileInputStream(file); //저장소에 있는 데이터를 가져오는 inputstream
		FileCopyUtils.copy(input, response.getOutputStream()); //왼쪽에 있는 데이터를 오른쪽으로 보내줘라
		//response.getOutputStream() : 응답 받은 쪽
		input.close();//남아있을 수 있어서 되도록 닫아주기
	}
}
