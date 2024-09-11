package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.ReplyService;
import com.yedam.service.ReplyServiceImpl;

public class ReplyCountControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// bno 인수를 갖고 총 댓글갯수를 JSON으로 반환해야함.
		String bno = request.getParameter("bno");
		ReplyService svc = new ReplyServiceImpl();
		int totalCnt = svc.getReplyCount(Integer.parseInt(bno));
		
		//{"totalCount" : 35}
		response.getWriter().print("{\"totalCount\": " + totalCnt + "}");
	}

}
