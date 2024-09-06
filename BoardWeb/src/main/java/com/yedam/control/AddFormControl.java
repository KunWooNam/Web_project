package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;

public class AddFormControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//addForm.do 요청 -> 요청재지정(WEB-INF/html/addForm.jsp)
		request.setAttribute("msg", "Hello, World"); //request 객체에 msg 전달.
		request.getRequestDispatcher("html/addForm.tiles").forward(request, response);
		//디스패쳐 = 배분, JSP는 너무 불편함. 보안과 편리상 디스패쳐를 사용함.
		
		//커밋테스트용 수정
	}

}