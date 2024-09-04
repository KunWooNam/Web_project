package com.yedam.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.common.Control;

public class LoginControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		// session 객체 - 요청정보(브라우저)를 확인해서 쿠키를 통해서 생성된 정보 구분
		HttpSession session = request.getSession();
		session.setAttribute("logid", id); //세션정보는 접속브라우저마다 각각 다른값임. 전부다 다른객체
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/html/새로만들거.jsp");
		rd.forward(request, response);	
	}


}
