package com.yedam.control;

import java.io.IOException;
import java.lang.reflect.Method;
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

/*
 *  "/eventList.do"   ->
 *  "/addEvent.do"    ->
 *  "/removeEvent.do" ->
 */

public class EventControl implements Control {
	ReplyService svc = new ReplyServiceImpl();
	
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json;charset=utf-8");
		
		String uri = request.getRequestURI();
		String context = request.getContextPath(); 
		String page =uri.substring(context.length());
		
		// 요청 uri값에서 "/"와 ".do" 를 제외한 값을 실행할 메서드의 이름으로 지정.
		String methodName = page.substring(1, page.indexOf("."));
		System.out.println(methodName);
		
		try {
			Class<?> cls = Class.forName(this.getClass().getName()); //실행되는시점의 클래스정보
			Method method = cls.getDeclaredMethod(methodName //모든 메서드를 반환해줌, 첫번째 인수위치에 있는 메서드를 반환한다.
					, HttpServletRequest.class // 파라미터1
					, HttpServletResponse.class //파라미터2
					);  
					method.invoke(this, request, response); //메서드 실행
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void eventList(HttpServletRequest request, HttpServletResponse response) {
		//전역으로 안쓰고 메서드마다 추가해줘야 하는 이유: mapper에서 빠른 결과를 출력하려고 캐시를씀. 전역으로 쓰려면 매퍼 쿼리태그 속성으로 useCache="false"를 써줘야함.
		List<Map<String, Object>> list = svc.eventList();
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(list);
		
		try {
			response.getWriter().println(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addEvent(HttpServletRequest request, HttpServletResponse response) {
		String title = request.getParameter("title");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		
		
		SearchDTO event = new SearchDTO();
		event.setTitle(title);
		event.setStart(start);
		event.setEnd(end);
	try {
		if(svc.addEvent(event)) {
			//{"retCode": "OK"}
			response.getWriter().print("{\"retCode\": \"OK\"}");
		} else {
			response.getWriter().print("{\"retCode\": \"NG\"}");
		} 
	} catch (IOException e) {
		e.printStackTrace();
	}
	}

	public void removeEvent(HttpServletRequest request, HttpServletResponse response) {
		String title = request.getParameter("title");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		
		
		SearchDTO event = new SearchDTO();
		event.setTitle(title);
		event.setStart(start);
		event.setEnd(end);
	try {
		if(svc.removeEvent(event)) {
			//{"retCode": "OK"}
			response.getWriter().print("{\"retCode\": \"OK\"}");
		} else {
			response.getWriter().print("{\"retCode\": \"NG\"}");
		} 
	} catch (IOException e) {
		e.printStackTrace();
	}
	}
}