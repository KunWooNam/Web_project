package com.yedam.web;

import java.util.HashMap;
import java.util.Map;

import com.yedam.common.Control;
import com.yedam.control.AddBoardControl;
import com.yedam.control.BoardControl;
import com.yedam.control.BoardFormControl;
import com.yedam.control.BoardListControl;
import com.yedam.control.ModifyBoardControl;
import com.yedam.control.ModifyBoardFormControl;
import com.yedam.control.RemoveControl;


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
		//특정 글 삭제
		menu.put("/removeBoard.do", new RemoveControl());
		//등록(화면,기능)
		menu.put("/addBoardForm.do", new BoardFormControl());
		menu.put("/addBoard.do", new AddBoardControl());
		//특정 글 수정(화면,기능)
		menu.put("/modifyBoard.do", new ModifyBoardControl()); //디스패처
		menu.put("/modifyFormBoard.do", new ModifyBoardFormControl()); //실제수정처리
		return menu;
	}
}
