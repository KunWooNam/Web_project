package com.yedam.web;

import java.util.HashMap;
import java.util.Map;

import com.yedam.common.Control;
import com.yedam.control.AddFormControl;
import com.yedam.control.AddMemberControl;
import com.yedam.control.GetMemberControl;
import com.yedam.control.LoginControl;
import com.yedam.control.LoginFormControl;
import com.yedam.control.LogoutControl;
import com.yedam.control.MemberListControl;
import com.yedam.control.ModFormControl;
import com.yedam.control.ModifyMemberControl;
import com.yedam.control.RemoveMemberControl;

//회원관련 메뉴와 컨트롤 등록, 팀장.
public class MenuMember {
	private static MenuMember instance = new MenuMember();
	
	private MenuMember() {}
	
	public static MenuMember getInstance() {
		return instance;
	}
	
	public Map<String, Control> menuMap(){
		Map<String, Control> menu = new HashMap<>();
		//기능등록.
		menu.put("/addMember.do", new AddMemberControl()); //회원등록페이지
		menu.put("/addForm.do", new AddFormControl()); //회원등록처리 페이지, 보안상 재요청하는메서드임
			//등록은 화면이 2개필요, 보여주는거 1개, 처리하는거 1개
		menu.put("/memberList.do", new MemberListControl());
		menu.put("/getMember.do", new GetMemberControl()); //회원아이디로 상세조회
		menu.put("/modifyForm.do", new ModFormControl()); //상세조회페이지에서 수정페이지 호출
		menu.put("/modifyMember.do", new ModifyMemberControl()); //실제 수정처리하는 페이지
		menu.put("/removeMember.do", new RemoveMemberControl()); //삭제처리
		
		// 로그인 관련
		menu.put("/logForm.do", new LoginFormControl());
		menu.put("/login.do", new LoginControl());
		menu.put("/logout.do", new LogoutControl());
		
		return menu;
	}
}
