package com.yedam.control;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.Control;
import com.yedam.service.ReplyService;
import com.yedam.service.ReplyServiceImpl;
import com.yedam.vo.ReplyVO;

public class AddReplyControl implements Control {
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	String json = gson.toJson(null);
		
	Map<String, Object> map = new HashMap<>(); //json 객체를 생성하기 위한 map선언
	
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json;charset=utf-8"); //한글쓰면써야함
			
		
		
		// 댓글작성자, 원본글번호, 댓글내용.
		String replyer= request.getParameter("replyer");
		String bno = request.getParameter("bno");
		String reply = request.getParameter("reply");
		
		
		
		ReplyVO replyVO = new ReplyVO();
		replyVO.setReplyer(replyer);
		replyVO.setBoardNo(Integer.parseInt(bno));
		replyVO.setReply(reply);
		replyVO.setReplyDate(new Date());
		ReplyService svc = new ReplyServiceImpl();
		//retCode =>OK. retCode=>NG.
		if(svc.addReply(replyVO)) { //쿼리문 실행되며 replyNo에 값이 지정(?)
//			response.getWriter().print("{\"retCode\": \"OK\"," //
//					+ " \"retVal\": {\"replyDate\": \"" + replyVO.getReplyDate()//
//					+ "\" ,          \"replyNo\": " + replyVO.getReplyNo()//
//					+ ",             \"reply\": \"" + replyVO.getReply()//
//					+ "\",           \"replyer\" : \"" + replyVO.getReplyer()//
//					+ "\",           \"boardNo\": " + replyVO.getBoardNo()//
//					+ "}}");
			map.put("retCode", "OK");
			map.put("retVal", replyVO);
		} else {
			//{"retCode": "NG", "retVal" : {"replyNo": 19, "reply": "reply", "replyer": "replyer", "boardNo": 148}} 
//			response.getWriter().print("{\retCode\":\"NG\"");
			map.put("retCode", "NG");
		}
		
		String json = gson.toJson(map); //map객체를 json문자열로 변환
		response.getWriter().print(json); //출력스트림에 json문자열 출력.
	}
	//http://localhost/BoardWeb/addReply.do?bno=149&reply=test&replyer=user01 로 확인

}
