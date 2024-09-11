package com.yedam.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.Control;
import com.yedam.common.SearchDTO;
import com.yedam.service.ReplyService;
import com.yedam.service.ReplyServiceImpl;
import com.yedam.vo.ReplyVO;

public class ReplyListControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//DB의 추출된 정보를 활용해서 json포맷의 문자열을 반환.
		//[{"replyNO": 1, "replyer": "user01", "reply":"1번댓글", "boardNo": 148...},{},{},...{}]
		
		response.setContentType("text/json;charset=utf-8");
		
		String bno = request.getParameter("bno");
		String page = request.getParameter("page");
		//검색조건 SearchDTO
		SearchDTO search = new SearchDTO();
		search.setBoardNo(Integer.parseInt(bno));
		search.setPage(Integer.parseInt(page));
		
		ReplyService svc = new ReplyServiceImpl();
		List<ReplyVO> list = svc.replyList(search);
//json문자열 연습		String json = "[";
//		for(int i=0; i<list.size(); i++) {
//			json += "{\"replyNo\": " + list.get(i).getReplyNo() + ", " //
//					+ "\"replyer\": \"" + list.get(i).getReplyer() + "\" , "//
//					+ "\"reply\" : \"" + list.get(i).getReply() + "\" , " //
//					+ "\"boardNo\" : " + list.get(i).getBoardNo() + ", " //;
//					+ "\"replyDate\" : \"" + list.get(i).getReplyDate() + "\"}";
//			//[{},{},{}]
//			if(i != list.size()-1) {
//				json += ",";  //마지막순번이 아니면 ,를 계속 붙여줘야한다.
//			}
//			
//		}
//		json += "]";
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(list); //gson을 활용해서 json문자열 생성. ,문자의 출력포맷이 위와 좀 다름
		
		response.getWriter().print(json);
	}

}
