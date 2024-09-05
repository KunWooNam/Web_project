<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
<h3>게시글 수정 페이지</h3>
<form action="modifyBoard.do" name="actForm">
	<input type="hidden" type="text" name="bno" value="${boardInfo.boardNo}">
	<table class="table table=dark table-hover">
	<input type="hidden" name="boardNo" value="${board.boardNo}"> <!-- 값은이력받되 수정은 못하도록 하기위함 -->
	<input type="hidden" name="boardNo" value="${board.creationDate}">
	<input type="hidden" name="boardNo" value="${board.viewCnt}">
		<tr>
			<th>글 번호</th><td>${board.boardNo}</td>
		</tr>
		<tr>
			<th>제목</th><td><input type="text" name="title" value="${board.title}"></td>
		</tr>
		<tr>
			<th>내용</th><td><input type="text" name="content"></td>
		</tr>
		<tr>
			<th>글쓴이</th><td><input type="text" name="writer"></td>
		</tr>
		<tr>
			<th>조회수</th><td>${board.viewCnt}</td>
		</tr>
		<tr>
			<th>작성일</th><td>${board.creationDate}</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<button class="btn btn-secondary" onclick="location.href='boardList.do?page=${page.page}'">수정하기</button> <!-- 누르면 보던목록로 이동 -->
			<button class="btn btn-secondary" onclick="location.href='boardList.do?page=${page.page}'">수정취소</button>
			</td>
		</tr>
	</table>
</form>
<script>
function form_submit(uri){
	document.forms.actForm.action = uri;
	document.forms.actForm.submit();
}
</script>
<jsp:include page="../includes/footer.jsp"></jsp:include>