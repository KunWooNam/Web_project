package com.yedam.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;

/*common의 Control 인터페이스의 구현체 클래스 url 패턴에 
*맞는 서블릿을 이곳에 다 정리할 목적으로 만든 것임.
**/
public class MainControl implements Control {
	
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("메인컨트롤 실행");
	}
}
