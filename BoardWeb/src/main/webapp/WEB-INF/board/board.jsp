<%@page import="com.yedam.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
<h3>글 상세 페이지</h3>

	<table class="table">
		<tr>
			<th>글 번호</th><td>${board.boardNo}</td>
		</tr>
		<tr>
			<th>제목</th><td>${board.title}</td>
		</tr>
		<tr>
			<th>내용</th><td>${board.content}</td>
		</tr>
		<tr>
			<th>글쓴이</th><td>${board.writer}</td>
		</tr>
		<tr>
			<th>조회수</th><td>${board.viewCnt}</td>
		</tr>
		<tr>
			<th>작성일</th><td>${board.creationDate}</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<button class="btn btn-secondary" onclick="location.href='boardList.do?page=${page.page}'">목록으로</button> <!-- 누르면 보던목록로 이동 -->
			<button class="btn btn-secondary" onclick="location.href='boardList.do?page=${page.page}'">수정은 숙제</button>
			<button class="btn btn-secondary" onclick="location.href='boardList.do?page=${page.page}'">삭제</button>
			</td>
		</tr>
	</table>

<jsp:include page="../includes/footer.jsp"></jsp:include>