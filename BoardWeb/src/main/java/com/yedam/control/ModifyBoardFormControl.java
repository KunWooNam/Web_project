package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;

public class ModifyBoardFormControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bno = request.getParameter("bno");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String image = request.getParameter("srcImage");
		
		String sc = request.getParameter("searchCondition");
		String kw = request.getParameter("keyword");
		String page = request.getParameter("page");
		
		request.setAttribute("sc", sc);
		request.setAttribute("kw", kw);
		request.setAttribute("page", page);
		BoardService svc = new BoardServiceImpl();
		BoardVO board = svc.getBoard(Integer.parseInt(bno));
		board.setTitle(title);
		board.setContent(content);
		board.setImage(image);
		request.setAttribute("board", board);
		System.out.printf("bno : %s title : %s sc : %s kw : %s page : %s page $s image", bno,title,content,kw,page,image);
		if(svc.modifyBoard(board)) {
			System.out.println(board.toString());
			System.out.println("page : " + page);
			response.sendRedirect("boardList.do?page=" + page + "&searchCondition=" + sc + "&keyword" + kw);
		} else {
			request.setAttribute("message", "변경 중 오류가 발생했습니다.");
			request.getRequestDispatcher("board/modifyBoard.tiles").forward(request, response);
		}
	}

}
