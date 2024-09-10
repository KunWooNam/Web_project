package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.ReplyService;
import com.yedam.service.ReplyServiceImpl;

public class RemoveReplysControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//getParameterValues = > 문자배열로 받아옴
		String[] params = request.getParameterValues("rno"); //?rno=1&rno=3
		
		ReplyService svc = new ReplyServiceImpl();
		if(svc.removeReplys(params)) {
			response.getWriter().print("{\"retCode\": \"OK\"}");
		} else {
			response.getWriter().print("{\"retCode\": \"NG\"}");
		}
		
	}

}
