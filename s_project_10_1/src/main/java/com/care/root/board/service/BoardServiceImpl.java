package com.care.root.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.care.root.mybatis.board.BoardMapper;

public class BoardServiceImpl {
	@Autowired BoardMapper mapper;
	public void selectAllBoardList(Model model) {
		model.addAttribute("boardList", mapper.selectAllBoardList());
	}
}
