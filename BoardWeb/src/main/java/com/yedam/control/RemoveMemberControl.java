package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.MemberService;
import com.yedam.service.MemberServiceImpl;

public class RemoveMemberControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService svc = new MemberServiceImpl();
		String id = request.getParameter("id"); 
		
		
		if (svc.removeMember(id)) {
			response.sendRedirect("memberList.do");
		} else {
			request.setAttribute("message", "수정중에 오류가 발생하였습니다.");
			request.getRequestDispatcher("html/memberList.tiles").forward(request, response);
		}
	}

}
