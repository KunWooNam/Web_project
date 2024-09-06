package com.yedam.control;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.MemberService;
import com.yedam.service.MemberServiceImpl;
import com.yedam.vo.MemberVO;

public class ModifyMemberControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 4개 값을 읽어서 db 반영 -> 목록으로 이동
		MemberService svc = new MemberServiceImpl();
		String id = request.getParameter("id"); 
		String name = request.getParameter("name");
		String pw = request.getParameter("password");
		String mail = request.getParameter("email");
		MemberVO mvo = svc.getMember(id);
		mvo.setMemberName(name);
		mvo.setPassword(pw);
		mvo.setEmail(mail);
		
		if (svc.modifyMember(mvo)) {
			System.out.println("모디파이");
			response.sendRedirect("memberList.do");
		} else {
			request.setAttribute("message", "수정중에 오류가 발생하였습니다.");
			request.getRequestDispatcher("html/memberList.tiles").forward(request, response);
		}
	}

}
