package com.yedam.common;



import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;

public class AppTest {
	public static void main(String[] args) {
		SearchDTO search = new SearchDTO();
		search.setSearchCondition("TW");
		search.setKeyword("JAVA");
		search.setPage(1);
		
		//목록
		BoardService svc = new BoardServiceImpl();
		
		svc.boardList(search).forEach(System.out::println); 
		
		
		
//		svc.addBoard(board);
//		svc.modifyBoard(board);
//		svc.removeBoard(3);
		
//		System.out.println(svc.getBoard(board.getBoardNo()));
		
//		svc.boardList().forEach(System.out::println);
		//board(매개값으로만 전달됨) -> System.out.println(board) 
		//->(줄여서) System.out.println
	}
}
