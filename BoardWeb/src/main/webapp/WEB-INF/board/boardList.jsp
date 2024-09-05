<%@page import="com.yedam.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <!-- 날짜별 포맷지원하는 태그들 쓰기위함 -->
<jsp:include page="../includes/header.jsp"></jsp:include>

	<h3>게시글 목록</h3>
	<!-- 검색기능 -->
	<div class="center">
		<form action="boardList.do">
			<div class="row">
			
				<!-- 검색조건(title,writer검색) -->
				<div class="col-sm-4">
					<select name="searchCondition" class="form-control">
						<option value="">선택하세요</option>>
						<option value="T" ${sc eq 'T' ? 'selected' : ''}>제목</option>
						<option value="W" ${sc eq 'W' ? 'selected' : ''}>작성자</option>
						<option value="TW" ${sc eq 'TW' ? 'selected' : ''}>제목 & 작성자</option>
					</select>
				</div>
				
				<!-- 키워드 -->
				<div class="col-sm-5">
					<input type="text" name="keyword" value="${kw}" class="form-control">
				</div>
				<!-- 조회버튼 -->
				<div class="col-sm-2">
					<input type="submit" name="조회"  class="btn btn-primary">
				</div>
				
			</div> <!-- end of div.row -->
		</form>
	</div><!-- end of div.center-->
	
	<c:choose>
		<c:when test="${!empty message}">
			<p style=" color='red' ">${message}</p>
		</c:when>
		<c:otherwise>
	<table class="table">
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
	</table>
	
	<nav aria-label="...">
	  <ul class="pagination">
	    <li class="page-item ${paging.prev ? '' :  'disabled'} ">
	      <a class="page-link" href="boardList.do?keyword=${kw}&searchCondition=${sc}&page=${paging.startPage-1}">Previous</a>
	    </li>
	    
	    <!-- 페이지 -->
	    <c:forEach var="pg" begin="${paging.startPage}" end="${paging.endPage}"> <!-- c:choose는 if else랑 같음 -->
	   		<c:choose>
	    		<c:when test="${paging.page == pg}">
		    		<li class="page-item active" aria-current="page">
		      			<a class="page-link" href="boardList.do?keyword=${kw}&searchCondition=${sc}&page=${pg}">${pg}</a>
		      		</li>
		    	</c:when>
	    		<c:otherwise> <!-- 현재 페이지가 아닌경우 -->
		    		<li class="page-item" aria-current="page">
		    			<a class="page-link" href="boardList.do?keyword=${kw}&searchCondition=${sc}&page=${pg}">${pg}</a>
		    		</li>
	    		</c:otherwise>
	    	</c:choose> 
	    </c:forEach>
	    <li class="page-item ${paging.next ? '' :  'disabled'}">
	      <a class="page-link"  href="boardList.do?keyword=${kw}&searchCondition=${sc}&page=${paging.endPage+1}">Next</a>
	    </li>
	  </ul>
	</nav>
		</c:otherwise>
	</c:choose>
	
	<p>${paging}</p>
	
	
	
	<!-- jstl에 대한 사용례 -->
	
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
	
	
<jsp:include page="../includes/footer.jsp"></jsp:include>