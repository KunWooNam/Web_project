package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.common.Control;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;

public class RemoveControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//bno(글 삭제목적), page(page로 이동목적)
		String bno = request.getParameter("bno");
		String page = request.getParameter("page");
		
		// 검색조건 서치컨디션 & 키워드	
		String sc = request.getParameter("searchCondition");
		String kw = request.getParameter("keyword");
	
		
		//로그인 정보.
		HttpSession session = request.getSession(); //세션정보 파악하기위한 세션읽어오기
		String logid = (String) session.getAttribute("logid");
		
		BoardService svc = new BoardServiceImpl();
		BoardVO board = svc.getBoard(Integer.parseInt(bno));
		//로그인정보가 없거나 로그인했더라도 login id와 작성자 정보가 다르면 삭제 불가
		if(logid == null || !logid.equals(board.getWriter())) {
			request.setAttribute("message", "삭제 권한이 없습니다!");
			request.setAttribute("board", board);
			request.setAttribute("page", page);
			request.getRequestDispatcher("board/board.tiles").forward(request, response);
			
			return;
		}
		
		//게시글 삭제.
		if(svc.removeBoard(Integer.parseInt(bno))){
//			request.setAttribute("page", page); //sendRedirect하면 setAttribute 사용불가
			response.sendRedirect("boardList.do?page=" + page +"&searchCondition=" + sc + "&keyword=" + kw); //그래서 직접 담아서 전달
		}
	}

}
