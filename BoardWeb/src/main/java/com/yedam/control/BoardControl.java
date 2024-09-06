package com.yedam.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.common.PageDTO;
import com.yedam.common.SearchDTO;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;

public class BoardControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bno = request.getParameter("bno");
		String page = request.getParameter("page");
		
		//검색조건. searchCondition & keyword
		String sc = request.getParameter("searchCondition");
		String kw = request.getParameter("keyword");
	
		
		SearchDTO search = new SearchDTO();
		search.setPage(Integer.parseInt(page));
		BoardService svc = new BoardServiceImpl();
		int totalCnt = svc.getTotalCnt(search); //getTotalCnt() 메서드를 수정했기때문에 미리 SearchDTO를 만듦
		PageDTO paging = new PageDTO(Integer.parseInt(page), totalCnt);
		BoardVO board = svc.getBoard(Integer.parseInt(bno));
		
		
		request.setAttribute("page", paging);
		request.setAttribute("board", board);
		
		request.setAttribute("sc", sc);
		request.setAttribute("kw", kw);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("board/board.tiles");
		rd.forward(request, response);	
		}

}
