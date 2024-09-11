package com.yedam.control;

import java.io.IOException;
import java.util.List;

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

public class BoardListControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String page = request.getParameter("page");
		page = page == null ? "1" : page; //페이지 값이 없을 경우 1페이지로 이동.
// 		
//		//검색조건 파라미터
//		String sc = request.getParameter("searchCondition");
//		String kw = request.getParameter("keyword");
//		
//		//검색조건이 공백일 경우(검색조건이 없으면)
//		if(sc == null || kw == null || sc.isEmpty() || kw.isEmpty()) {
//			request.setAttribute("message", "검색조건을 입력하세요~!");
//		} else {				
			SearchDTO search = new SearchDTO();
//			search.setSearchCondition(sc); //T, W ,TW
//			search.setKeyword(kw); //Java, html
//			search.setPage(Integer.parseInt(page));
//			request.setAttribute("search", search);
			
			BoardService svc = new BoardServiceImpl();
			List<BoardVO> list = svc.boardList(search);
			request.setAttribute("list",list);
			
			//페이징 처리를 위한 기능.
			int totalCnt = svc.getTotalCnt(search);
			PageDTO paging = new PageDTO(Integer.parseInt(page), totalCnt);
			request.setAttribute("paging", paging);
			
			//jsp에 전달한 searchCondition, Keyword를 리퀘셋
//			request.setAttribute("sc", sc);
//			request.setAttribute("kw", kw);
//		}
		
		RequestDispatcher rd =  request.getRequestDispatcher("board/boardList.tiles");
		rd.forward(request, response);
		
	}
} // end of class
