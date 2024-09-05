package com.yedam.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.common.Control;
import com.yedam.service.MemberService;
import com.yedam.service.MemberServiceImpl;
import com.yedam.vo.MemberVO;

public class LoginControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MemberService svc = new MemberServiceImpl();
		MemberVO member = svc.loginCheck(id, pw);
		
		if(member == null) {
			request.setAttribute("message", "아이디와 비밀번호를 확인하세요!");
			request.getRequestDispatcher("WEB-INF/html/logForm.jsp").forward(request, response);
			return;
		}
		
		// session 객체 - 요청정보(브라우저)를 확인해서 쿠키를 통해서 생성된 정보 구분
		HttpSession session = request.getSession();
		session.setAttribute("logid", id); //세션정보는 접속브라우저마다 각각 다른값임. 전부다 다른객체, 사용자가입력한 id값
		session.setAttribute("logName", member.getMemberName()); //추가정보(필요한경우 이렇게 사용할수있다 정도)
		
		response.sendRedirect("boardList.do");
		
	}


}
