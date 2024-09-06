package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;

public class ModifyBoardControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bno = request.getParameter("bno");
		String sc = request.getParameter("searchCondition");
		String kw = request.getParameter("keyword");
		String page = request.getParameter("page");
		request.setAttribute("sc", sc);
		request.setAttribute("kw", kw);
		request.setAttribute("page", page);
		BoardService svc = new BoardServiceImpl();
		BoardVO board = svc.getBoard(Integer.parseInt(bno));
		request.setAttribute("boardInfo", board);
		request.setAttribute("bno", board.getBoardNo());
		
		request.getRequestDispatcher("board/modifyBoard.tiles").forward(request, response);
	}

}
