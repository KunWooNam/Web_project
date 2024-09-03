<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
	<%
	//자바 영역, 자바주석사용가능
	String message = (String) request.getAttribute("message");
	%>
	<%if (message != null) {%>
	<p style="color:red;"> <%=message%></p>
	<%} %>
	<!-- html의 주석 -->
	<form action="addMember.do" method="post">
		<table class="table">
		<!--아까만든 서블릿 이름?이 = 경로지정--> <tr><th>회원아이디:</th><td><input type="text" name="id" class="form-control" ></td></tr>
		<!--name 속성값은 servlet에 있는 매개변수가들어감--> <tr><th>회원이름:</th><td> <input type="text" name="name" class="form-control"></td></tr>
		<tr><th>비밀번호:</th><td> <input type="text" name="pass" class="form-control"></td></tr> 
		<tr><th>이메일:</th><td> <input type="email" name="email" class="form-control"></td></tr> 
		<tr><td colspan="2" align="center">
			<input type="submit" class="col-sm-2 btn btn-success">
			<input type="reset" class="col-sm-2 btn btn-secondary">
		</td></tr> 
		</table>
	</form>
	<jsp:include page="../includes/footer.jsp"></jsp:include>
</body>
</html>