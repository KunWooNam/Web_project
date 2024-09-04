package com.yedam.common;



import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;

public class AppTest {
	public static void main(String[] args) {
		BoardVO board = new BoardVO();
		board.setTitle("입력테스트");
		board.setContent("수정-내용입니다.");
		board.setBoardNo(2053);
		//목록
		BoardService svc = new BoardServiceImpl();
//		svc.addBoard(board);
//		svc.modifyBoard(board);
//		svc.removeBoard(3);
		
//		System.out.println(svc.getBoard(board.getBoardNo()));
		
//		svc.boardList().forEach(System.out::println);
		//board(매개값으로만 전달됨) -> System.out.println(board) 
		//->(줄여서) System.out.println
	}
}
