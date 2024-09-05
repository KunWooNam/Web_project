package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yedam.common.Control;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;

public class AddBoardControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//기본타입일떄 application/x-www-form-urlencoded => key=value&key=value&...
		//multipart 요청일 때 multipart/form-data (mr이 가지고있는 메서드를통해 읽어들인다.) => key=value..
		
		//multipart(파일 업로드) 요청을 처리. 서버의 위치(images) 파일복사.
		String saveDir = request.getServletContext().getRealPath("images");//get서블릿콘텍스트 = 최상위경로, 입력받은 파일이 배포되는위치 서버에서 서버로케이션에서 설정및확인가능(아마?)
		int maxSize = 5 * 1024 * 1024; // 5MB로 최대파일크기 제한
		MultipartRequest mr;
		mr = new MultipartRequest(request // 1.요청
								,saveDir // 2.파일저장경로
								,maxSize // 3.최대파일크기
								,"utf-8" // 4.인코딩
								,new DefaultFileRenamePolicy() // 5.리네임 정책
				);
		String title = mr.getParameter("title"); //request가 아닌 mr.get~ 으로 바뀐다.
		String content = mr.getParameter("content");
		String writer = mr.getParameter("writer");
		String img = mr.getFilesystemName("srcImage"); //getFile~()메서드의 역할 -> 리네임된 파일이름을 읽어온다.
		
		
		BoardVO board = new BoardVO();
		board.setTitle(title);
		board.setContent(content);
		board.setWriter(writer);
		board.setImage(img);
		
		BoardService svc = new BoardServiceImpl();
		svc.addBoard(board);
		
		response.sendRedirect("boardList.do");
	}

}
