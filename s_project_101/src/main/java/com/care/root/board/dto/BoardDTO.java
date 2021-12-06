package com.care.root.board.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class BoardDTO {
	private int writeNo;
	private String title;
	private String content;
	private String saveDate;
	private int hit;
	private String imageFileName;
	private String id;
	
	/*
	public void setSaveDate(String saveDate) {
		this.saveDate = saveDate;
		//이래도 문제는 없지만 13:51:47.0 < 이렇게 끝에 .0이 생김
	}
	*/
	public void setSaveDate(java.sql.Timestamp saveDate) {
		SimpleDateFormat fo = 
				new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		this.saveDate = fo.format(saveDate);
		//13:51:47.0 <이렇게 끝에 .0이 붙지 않기 위해 포맷 설정
		//13:51:47
	}
	
	public int getWriteNo() {
		return writeNo;
	}
	public void setWriteNo(int writeNo) {
		this.writeNo = writeNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSaveDate() {
		return saveDate;
	}
	
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
