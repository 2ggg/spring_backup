package com.care.root.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class BoardController {
	@Autowired BOardService bs;
	
	@GetMapping("boardAllList")
	public String selectAllBoardList(Model model) {
		bs.selectAllBoardList(model);
		return "board/";
	}
}
