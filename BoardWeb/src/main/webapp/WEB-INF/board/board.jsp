<%@page import="com.yedam.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- 이건 왜? -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
div.reply div {
	margin: auto;
}

div.reply ul {
	list-style-type: none;
}

div.reply span {
	display: inline-block;
}
</style>
<link rel="stylesheet" href="//cdn.datatables.net/2.1.5/css/dataTables.dataTables.min.css"> <!-- datatables 라이브러리를 위한 작업 -->
 <!-- datatables 라이브러리를 위한 작업 -->
<script src="js/jquery-3.7.1.js"></script>
<script src="js/dataTables.js"></script>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script> <!-- 알림창 라이브러리 -->

<script defer src="js/replyService.js"></script>
<!-- <script defer src="js/replyBoard.js"></script> -->
<script>
	const bno = '${board.boardNo}'; //원본글번호
	const writer = '${logid}'; //로그인 정보.
</script>
<h3>글 상세 페이지</h3>
<p>searchCondition: ${sc}, keyword: ${kw}</p>
<table class="table">
	<tr>
		<th>글 번호</th>
		<td>${board.boardNo}</td>
	</tr>
	<tr>
		<th>제목</th>
		<td>${board.title}</td>
	</tr>
	<tr>
		<th>내용</th>
		<td>${board.content}</td>
		<td colspan="2" rowspan="3">
			<c:if test="${!empty board.image}">
			<!-- 보드의 이미지가 null값인 경우 보여주지 않는다. -->
			<img width="150px" src="images/${board.image}">
			</c:if>
		</td>
	</tr>
	<tr>
		<th>글쓴이</th>
		<td>${board.writer}</td>
	</tr>
	<tr>
		<th>조회수</th>
		<td>${board.viewCnt}</td>
	</tr>
	<tr>
		<th>작성일</th>
		<td colspan="3">
			<fmt:formatDate value="${board.creationDate}" pattern="yyyy.MM.dd HH:mm:ss" />
		</td>
	</tr>
</table>
<!-- 버튼형식 -->
<div style="text-align: center;">
	<button type="button" class="btn btn-primary ${board.writer ne logid ? 'disabled' : ''}" onclick="form_submit('modifyBoard.do')">
		수정하기
	</button>
	<!-- 디스패처로이동 -->
	<button type="button" class="btn btn-secondary" onclick="form_submit('boardList.do')">목록으로</button>
	<button type="button" class="btn btn-danger" onclick="form_submit('removeBoard.do')">삭제</button>
	<c:if test="${!empty message}">
		<span style="color: red;">${message}</span>
	</c:if>
</div>

<!-- 댓글관련 -->
<div class="container reply">

	<!-- 댓글등록 -->
	<div class="header">
		<input type="text" id="reply" class="col-sm-9">
		<!-- 너비 12등분 중 8을 차지 -->
		<button id="addReply" class="btn btn-primary">댓글등록</button>
		<button id="delReply" class="btn btn-danger">댓글삭제</button>
	</div>
	
	<!-- 댓글목록 -->
	<table id="example" class="display" style="width:100%">
        <thead>
            <tr>
                <th>댓글번호</th>
                <th>댓글내용</th>
                <th>댓글작성자</th>
                <th>작성일시</th>
            </tr>
        </thead>
       
        <tfoot>
            <tr>
                <th>댓글번호</th>
                <th>댓글내용</th>
                <th>댓글작성자</th>
                <th>작성일시</th>
            </tr>
        </tfoot>
    </table>
	<!-- 댓글페이징 -->
	
	
</div>


<!-- 컨트롤하기 위한 폼 -->
<form action="removeBoard.do" name="actForm">
	<input type="hidden" name="keyword" value="${kw}"> 
	<input type="hidden" name="searchCondition" value="${sc}"> 
	<input type="hidden" name="page" value="${page.page}"> 
	<input type="hidden" name="bno" value="${board.boardNo}">
</form>


<script>

	function form_submit(uri) {
		//매개값으로 이동할 컨트롤을 받아서 파라미터를 전달
		document.forms.actForm.action = uri;
		document.forms.actForm.submit();
	}
</script>

<script src="js/boardTable.js"></script>