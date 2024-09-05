package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.common.Control;

public class LogoutControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//중요한것 앞서 로그인시점의 logid를 세션에 담아두었음. session을 지워주거나 session자체를 삭제해줄것
		HttpSession session = request.getSession(); //getSession()으로 현재 세션정보 파악
		session.invalidate(); //현재 세션정보삭제
		
		response.sendRedirect("logForm.do"); //로그인화면으로 다시 이동
		
				
	}

}
