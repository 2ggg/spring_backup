package com.care.root.mybatis.board;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.care.root.board.dto.BoardDTO;
import com.care.root.board.dto.BoardRepDTO;

public interface BoardMapper {
	public List<BoardDTO> selectAllBoardList(@Param("s") int start, @Param("e") int end);
	//2개 이상의 값이 넘어올 때는 반드시 이렇게 @param을 써서 명시해줘야함
	//("") : 보드매퍼.xml에서 붙임
	public int writeSave(BoardDTO dto);
	public BoardDTO contentView(int writeNo);
	public void upHit(int writeNo);
	public int delete(int writeNo);
	public int modify(BoardDTO dto);
	public int addReply(BoardRepDTO dto);
	public List<BoardRepDTO> getRepList(int write_group);
	public int selectBoardCount();
}
