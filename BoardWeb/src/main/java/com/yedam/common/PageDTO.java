package com.yedam.common;

import lombok.Data;

//페이징 계산 위한 클래스
@Data
public class PageDTO { //Data Transfer Object
	//현재 페이지 정보. 1.. 3.. 10
	//이전, 이후 정보.
	int page;
	int startPage, endPage;
	boolean prev, next;
	
	public PageDTO(int page, int totalCnt) { // page: 3, totalCnt: 76건. -> 16페이지
		this.page = page;
		this.endPage = (int) Math.ceil(page / 10.0) * 10; //1~10 or n1 ~ (n+1)0 에 대한 필드값임
		this.startPage = this.endPage - 9;
		
		//실제 마지막페이지
		int realEnd = (int) Math.ceil(totalCnt/5.0);
		this.endPage = this.endPage > realEnd ? realEnd : this.endPage;
		
		//이전, 이후 여부
		prev = this.startPage > 1; // 1, 11, 21
		next = this.endPage < realEnd ? true : false; 
	}
	
}
