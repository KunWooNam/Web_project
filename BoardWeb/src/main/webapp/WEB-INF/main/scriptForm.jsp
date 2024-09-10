<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h3>스크립트 연습</h3>

<div id="show">
	<!-- 	<p>이름 : GO GIL DONG</p>
	<p>연락처 : 010-1111-2222</p> -->
</div>
<table>
	<tr>
		<th>id</th><td><input type='text' id='id'></td>
	</tr>
	<tr>
		<th>name</th><td><input type='text' id='name'></td>
	</tr>
	<tr>
		<th>point</th><td><input type='number' id='point'></td>
	</tr>
	<tr>
		<td colspan="2"><button id='addBtn' >추가</button></td>
	</tr>
</table>


<table id="target">
	<thead>
		 <tr>
		 	<th>아이디</th><th>이름</th><th>포인트</th>
		 </tr>
	</thead>
	<tbody id='list'>
		
	</tbody>
</table>
	<!--  <p>연락처</p>
	<ul>
	<li>이름 Kim, 연락처: </li>
	<li>이름 Lee, 연락처: </li>
	</ul>-->
	
<div>
	salary: <input id="salary">
	gender: <select id="gender">
			<option value="Male">남성</option>
			<option value="Female">여성</option>
			</select>
			<button id="searchBtn">검색</button>
</div>

<h3>목록</h3>
<!-- 댓글등록 글번호148번임을 가정하고 만듬.-->
<table class="table">
	<tr>
		<td>댓글내용: <input type="text" id="reply"></td>
		<td><button id="addReply">댓글등록</button></td>
	</tr>
</table>



<table class="table">
	<thead>
		<tr>
			<th><input type="checkBox"></th><th>댓글번호</th><th>댓글내용</th><th>작성자</th><th>작성일시</th><th><button id="delChecked">선택삭제</button></th>
		</tr>
	</thead>
	<tbody class="list">
		
	</tbody>
</table>

<!-- <script src="js/basic.js"></script>
<script src="js/basic2.js"></script>
<script src="js/basic3.js"></script>

<script src="js/data.js"></script>
<script src="js/basic4.js"></script>
<script src="js/ajax.js"></script>
<script src="js/xmlhttprequest.js"></script> --> <!-- 원래2개쓰면 안됨 위에꺼만적용됨 -->
<!-- <script src="js/reply.js"></script> -->
<script src="js/replyService.js"></script>
<script src="js/replyBoard.js"></script> <!-- replyService.js에 있는 필드값을 fetch.js에서도 공유되어읽으수있다. -->
