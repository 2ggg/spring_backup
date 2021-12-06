package com.care.root.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration	//빈 등록 되도록 xml대신 자바코드를 이용하는 방식
public class FileConfig {
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver mr = new CommonsMultipartResolver();
		mr.setMaxUploadSize(52428800); //최대 50mb까지 업로드 가능(byte로 작성)
		mr.setDefaultEncoding("utf-8");
		return mr;
	}
	//CommonsMultipartResolver : 리턴 타입
	//bean을 꼭 만들어야 파일 업로드 처리 가능
	//리턴으로 돌려주는 값을 bean으로 
}
