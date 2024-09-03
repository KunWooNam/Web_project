package com.yedam.vo;

import java.util.Date;

import lombok.Data;
// lombok 활용 : getter, setter, toString, 메서드 생성.
// Lombok 설치, 라이브러리 pom에 추가
@Data //거의 모든 메서드들을 오버라이딩해준다. (get,set,toString,hash등등)
public class MemberVO {
	private String memberId;
	private String memberName;
	private String password;
	private String email;
	private String authority;
	private Date creationDate;
}