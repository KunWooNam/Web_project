package com.yedam.web;

import java.util.HashMap;
import java.util.Map;

import com.yedam.common.Control;
import com.yedam.control.BoardControl;
import com.yedam.control.BoardListControl;
import com.yedam.control.ModifyBoardControl;


//게시글관련 메뉴와 컨트롤 등록, 팀원1.
public class MenuBoard {
private static MenuBoard instance = new MenuBoard();
	
	private MenuBoard() {}
	
	public static MenuBoard getInstance() {
		return instance;
	}
	
	public Map<String, Control> menuMap(){
		Map<String, Control> menu = new HashMap<>();
		//기능등록.
		menu.put("/boardList.do", new BoardListControl());
		//단건조회.
		menu.put("/getBoard.do", new BoardControl());
		//특정 글 수정
		menu.put("/modifyBoard.do", new ModifyBoardControl());
		return menu;
	}
}
