<%@page import="com.yedam.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <!-- 날짜별 포맷지원하는 태그들 쓰기위함 -->
<link rel="stylesheet" href="//cdn.datatables.net/2.1.5/css/dataTables.dataTables.min.css"> <!-- datatables 라이브러리를 위한 작업 -->
 <!-- datatables 라이브러리를 위한 작업 -->
<script src="js/jquery-3.7.1.js"></script>
<script src="js/dataTables.js"></script>

	<h3>게시글 목록</h3>
	
	<c:choose>
		<c:when test="${!empty message}">
			<p>${message}</p>
		</c:when>
		<c:otherwise>
	<table id="example" class="display" style="width: 100%">
		<thead>
			<tr>
               <th>글번호</th><th>제목</th><th>작성자</th><th>작성일시</th>
            </tr>
		</thead>
		<tbody> <!-- for(Board board: list) { -->
			<c:forEach var="board" items="${list}"> <!-- board에 있는 만큼 건수 반복, 값은 items라는 속성에 담아준다. -->
			<tr>
				<td><a href="getBoard.do?bno=${board.boardNo}&keyword=${kw}&searchCondition=${sc}&page=${paging.page}"><c:out value="${board.boardNo}"/></a></td>
				<td>${board.title}</td>
				<td>${board.writer}</td>
				<td><fmt:formatDate value="${board.creationDate}" pattern ="yyyy년MM월dd일 HH:mm:ss"/></td>
			</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<th>글번호</th><th>제목</th><th>작성자</th><th>작성일시</th>
			</tr>
		</tfoot>
	</table>
	
	
		</c:otherwise>
	</c:choose>
	
	<p>${paging}</p>
	
	
	
	<!-- jstl에 대한 사용례 -->
	<br>
	<p>============================================================================ jstl 사용례</p>
	<br>
	<p>${'문자열, 숫자, boolean null' }</p>
	<p>${ 3+5>8 ? '사실' : '거짓'}</p>
	<p>${msg}</p> <!-- 키값으로 request.setAttribute에 담긴값 바로가져옴 -->
	<p>${list}</p>
	<%
	String name = "hong";
	%>
	<c:set var="name" value="Hongildong"></c:set> <!-- name변수에 value값 담겠다는 의미 -->
	<c:out value="${name}"></c:out> <!-- name이라고 담긴 변수의 값을 호출 -->>
	<c:set var="age" value="20"></c:set>
	<c:out value="${age >= 20 ? '성인' : '미성인'}" ></c:out>
	<c:if test="${name eq 'Hongildong'}"> <!-- eq(equal) ne(!equal) empty(null or "") -->
		<p>맞습니다~</p>
	</c:if>
	
<script>
new DataTable('#example')
</script>
