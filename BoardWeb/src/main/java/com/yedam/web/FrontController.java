package com.yedam.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.control.AddFormControl;
import com.yedam.control.AddMemberControl;
import com.yedam.control.GetMemberControl;
import com.yedam.control.IntroControl;
import com.yedam.control.MainControl;
import com.yedam.control.MemberListControl;
import com.yedam.control.ModFormControl;
import com.yedam.control.ModifyMemberControl;
import com.yedam.control.RemoveMemberControl;
import com.yedam.control.SubControl;

@WebServlet("*.do") //.do라는 패턴으로 끝이나는 요청이 들어오면 밑의 서블릿 클래스를 실행하겠다는 의미
public class FrontController extends HttpServlet{
	
	//url pattern - 실행되는 기능 -> map 컬렉션에 지정.
	Map<String, Control> map; //타입 맞춰줘야함
	
	public FrontController() {
		System.out.println("FrontController 생성자.");
		map = new HashMap<>();
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init 메서드");
		//url 패턴 -> 실행되는기능 이메서드에 등록할 것임
		map.put("/main.do", new MainControl()); //첫번째인수는 url패턴, 두번째는 실행되는 Controller
		map.put("/sub.do", new SubControl()); //첫번째 인수에 따라 어떤 객체를 반환할것인지 (이것도만들어줘야함)
		map.put("/intro.do", new IntroControl());
		//기능등록.
		map.put("/addMember.do", new AddMemberControl()); //회원등록페이지
		map.put("/addForm.do", new AddFormControl()); //회원등록처리 페이지, 보안상 재요청하는메서드임
			//등록은 화면이 2개필요, 보여주는거 1개, 처리하는거 1개
		map.put("/memberList.do", new MemberListControl());
		map.put("/getMember.do", new GetMemberControl()); //회원아이디로 상세조회
		map.put("/modifyForm.do", new ModFormControl()); //상세조회페이지에서 수정페이지 호출
		map.put("/modifyMember.do", new ModifyMemberControl()); //실제 수정처리하는 페이지
		map.put("/removeMember.do", new RemoveMemberControl()); //삭제처리)
		
	}
	//init에 보면 request,response 객체가 없음
	
	// init()실행후 service()실행전에 톰캣이 HttpServletRequest, HttpServletResponse 객체를 생성해서 service의 매개값으로 전달해준다.
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");	
		//요청방식이 POST일 경우에 body포함된 문자열 인코딩을 지정 (get은그냥 해줌)			
		//get -> head값을 인코딩없이 그대로 입력(입력에 제한 O)
		//post -> body값에서 인코딩을 해서 입력받는다.(입력에 제한 X)
		System.out.println("service 메서드");
		req.getRequestURI(); //이것이 요청정보에 따라 객체를 반환해줌 Ex) http://localhost/BoardWeb/main.do
		String uri = req.getRequestURI(); //BoardWeb/main.do
		String context = req.getContextPath(); //여기서는 /BoardWeb을 의미, JSP에서 context는 프로젝트를 의미함
		String page =uri.substring(context.length()); // main.do(키값이 된다고 볼수있음)를 읽어올 수 있게되는 것(이해하기.중요)
		System.out.println(page);
		
		Control control = map.get(page); //page값(키 값)을 넣어주면 해당 url 패턴에 맞는 컨트롤러를 반환하게 되는 것이다.
		control.exec(req, resp);
		//uri는 무슨뜻? 프로토콜(https)+페이지정보(naver.com) =url, 페이지정보만 = uri
	}
}