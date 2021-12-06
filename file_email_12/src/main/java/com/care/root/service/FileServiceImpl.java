package com.care.root.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.dto.ShoesDTO;
import com.care.root.mybatis.FileMapper;

@Service
public class FileServiceImpl implements FileService{
	@Autowired FileMapper fm;
	
	public void fileProcess(MultipartHttpServletRequest mul) {
		
		ShoesDTO dto = new ShoesDTO();	//db에 저장 위해 가져옴
		dto.setId(mul.getParameter("id")); //사용자가 넣는 id
		dto.setName(mul.getParameter("name")); //사용자가 넣는 이름
		
		
		MultipartFile file = mul.getFile("file");
		if(file.getSize()!=0) {//파일 사이즈가 0이 아니라면, = 업로드 할 파일을 선택했다면
			//!file.isEmpty() : 파일이 비어있지 않다면,
			
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss-");	//년,월,일,시,분,초, 뒤에 OriginalFilename
			Calendar calendar = Calendar.getInstance();//캘린더 생성. 현재의 시간 들어감
			
			String sysFileName = format.format(calendar.getTime());
			//calendar에 있는 현재 시간이 yyyyMMddHHmmss(2021111541)-파일이름형태로 변환됨
			
			sysFileName += file.getOriginalFilename();
			
			dto.setImgName(sysFileName); //db에 파일 이름을 세팅
			
			File saveFile = new File(IMAGE_REPO+"/"+sysFileName); //파일이 저장되는 경로
			
			try {
				file.transferTo(saveFile);
				System.out.println("파일 업로드");
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			dto.setImgName("nan"); //파일이 없을때 db에 저장되는 이름 : nan
		}
		fm.saveData(dto); //dto 데이터 저장하는 메소드
	}
	
	public void getShoesImage(Model model) {
		model.addAttribute("list", fm.getShoesImage());
	}
}
