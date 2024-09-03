<%@page import="com.yedam.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
<h3>회원정보 수정화면</h3>
<% 
	MemberVO member = (MemberVO) request.getAttribute("memberInfo");
%>
<%-- <%if(message != null) {%> --%>
<form action="modifyMember.do">
	<input type="hidden" name="id" value="<%=member.getMemberId() %>"> <!-- 값은이력받되 수정은 못하도록 하기위함 -->
	<input type="hidden" name="date" value="<%=member.getCreationDate() %>">
	<table class="table">
		<tr>
			<th>회원아이디</th><td><%=member.getMemberId() %></td>
		</tr>
		<tr>
			<th>회원이름</th><td><input class="form-control" type="text" name="name" value="<%=member.getMemberName() %>"></td>
		</tr>
		<tr>
			<th>비밀번호</th><td><input class="form-control" type="password" name="password" value="<%=member.getPassword() %>"></td>
		</tr>
		<tr>
			<th>이메일</th><td><input class="form-control" type="email" name="email" value="<%=member.getEmail() %>"></td>
		</tr>
		<tr>
			<th>가입날짜</th><td><%=member.getCreationDate() %></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button class="btn btn-primary" type="submit">저장</button>
				<button class="btn btn-secondary">취소</button>
			</td>
		</tr>
	</table>
</form>
<jsp:include page="../includes/footer.jsp"></jsp:include>