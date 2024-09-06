package com.yedam.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.yedam.common.Control;
import com.yedam.common.DataSource;
import com.yedam.mapper.MemberMapper;
import com.yedam.service.MemberService;
import com.yedam.service.MemberServiceImpl;
import com.yedam.vo.MemberVO;

public class AddMemberControl implements Control {
//넘모어렵다
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException{
		//web-memberlistserv클래스 내용 복붙했음
		response.setContentType("text/html;charset=utf-8");

		String id = request.getParameter("id"); 
		String name = request.getParameter("name");
		String pw = request.getParameter("pass");
		String mail = request.getParameter("email");
		MemberVO mvo = new MemberVO();
		mvo.setMemberId(id);
		mvo.setMemberName(name);
		mvo.setPassword(pw);
		mvo.setEmail(mail);

		MemberService svc = new MemberServiceImpl();
		
//		SqlSessionFactory factory = DataSource.getInstance();
//		SqlSession session = factory.openSession(true);
//		MemberMapper mapper = session.getMapper(MemberMapper.class);	
		
		
		// 회원등록이 정상적일 경우 -> 회원목록 페이지 출력
		// 회원등록이 비정상일 경우 -> 회원등록 페이지 이동(msg 전달)
		// 현재 페이지 : addMember.do -> 페이지 재지정 방식은 2가지, (1) forwarding:파라미터전달. (2)redirect:파라미터전달 불가.
		if (svc.addMember(mvo)) {
			//PrintWriter out = response.getWriter(); //출력 스트림을 반환
			//out.print("등록됨"); // 웹브라우저에 전달.
			response.sendRedirect("memberList.do");
		} else {
			request.setAttribute("message", "등록중에 오류가 발생하였습니다.");
			request.getRequestDispatcher("html/addForm.tiles").forward(request, response);
		}
	}

}
