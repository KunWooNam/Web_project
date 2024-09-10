/**
 * reply.js
 */

//글번호 148번이라는 가정하에 댓글등록을 만든다.
document.querySelector('#addReply').addEventListener('click', addReply);

function addReply(){
	let replyer = 'user01';
	let bno = '148';
	let reply = "&reply=" + document.querySelector('#reply').value;
	params = "bno=" + bno + "&replyer=" + replyer;
	
	const addHtp = new XMLHttpRequest();
	addHtp.open('get', "addReply.do?" + params + reply);
	addHtp.send();
	addHtp.onload = function() {
		let result = JSON.parse(addHtp.responseText); //컨트롤에서 반환해주는 정보 json문자열
		console.log(result); //retCode,retVal=>{}
		if(result.retCode == 'OK'){
		let tr = makeRow(result.retVal);
		document.querySelector('.list').appendChild(tr);
		} else {
			alert('처리중 예외');
		}
	}
}

const xhtp = new XMLHttpRequest(); //객체생성.
xhtp.open('get','replyList.do?bno=148'); // Ajax는 서버 페이지를 직접 요청. //http://localhost/BoardWeb/replyList.do?bno=148
xhtp.send();//서버요청.
xhtp.onload = function(){
	console.log(xhtp.responseText); //xhtp객체의 responseText 속성 =>결과값.(js든 json이든)
	let result = JSON.parse(xhtp.responseText);//json문자열을 =>객체로 변경 (파싱), 배열형태로 변경됨 
	console.log(result); 
	result.forEach(reply => {
		document.querySelector('.list').appendChild(makeRow(reply))
	});
}

let fields = ['replyNo', 'reply', 'replyer', 'replyDate'];//항목들을 담고있는 필드 변수
// 댓글정보 -> tr>td*4 생성 및 반환해주는 함수
function makeRow (reply = {}) {
	let tr = document.createElement('tr');
	//체크박스 생성
	let btn = document.createElement('input'); 
	btn.setAttribute('type','checkbox')
		
	let td = document.createElement('td'); 
	td.appendChild(btn);
	tr.appendChild(td);
	//td생성
	fields.forEach(field => {
		let td = document.createElement('td');
		td.innerHTML = reply[field];
		tr.appendChild(td);
	})	
	
	//삭제버튼
	btn = document.createElement('button'); //<button>삭제</button>
	btn.innerHTML = '삭제';
	btn.addEventListener('click',deleteRowFnc); //함수이름만 넣어줘야 이벤트발생시 함수실행
	
	td = document.createElement('td'); //<td><button>삭제</button></td>
	td.appendChild(btn);
	tr.appendChild(td);
	
	return tr;
}

//버튼을 클릭하면 삭제처리하는 함수
function deleteRowFnc(e) {
	console.log(e.target);
	console.log(e.target.parentElement.parentElement.firstChild.innerHTML);
	let rno = e.target.parentElement.parentElement.firstChild.innerHTML;
	console.log(e.target.parentElement.parentElement.children[1].innerHTML);
	rno = e.target.parentElement.parentElement.children[1].innerHTML;
	
	const delHtp = new XMLHttpRequest();
	delHtp.open('get', 'removeReply.do?rno=' + rno); //컨트롤 지정.
	delHtp.send();
	delHtp.onload = function(){ //onload를 읽어보는 이유는 정상삭제처리를 확인하기 위함
		let result = JSON.parse(delHtp.responseText);
		if(result.retCode == 'OK'){
			e.target.parentElement.parentElement.remove();
		} else if (result.retCode == 'NG'){
			alert("알 수 없는 예외 발생.");
		} else {
			alert("잘못된 반환 코드"); //OK,NG 말고 다른거 넘어오면 코드가 잘못된거임
		}
	} 
}

//일괄선택해제 체크박스 이벤트
		let totalBtn = document.querySelector('thead input[type="checkBox"]');
		totalBtn.addEventListener('change', function(){
		let isCheck = totalBtn.checked;
		if(isCheck){
			document.querySelectorAll('tbody input[type="checkbox"]').forEach(item => item.checked = true);	
		} else{
			document.querySelectorAll('tbody input[type="checkbox"]').forEach(item => item.checked = false);
		}	});
		

//선택삭제 이벤트
document.querySelector('#delChecked').addEventListener('click', delCheckedFnc1);

//선택삭제함수 일괄 실행
function delCheckedFnc1(e){
	// ?rno=21&rno=22&rno=23
	let params = "rno="
	document.querySelectorAll('.list input[type="checkbox"]:checked').forEach(item=> {
		let rno = item.parentElement.nextElementSibling.innerHTML;
		params += rno + "&rno=";
	})
	const delHtp = new XMLHttpRequest();
	delHtp.open('get', "removeReplys.do?" + params);
	delHtp.send();
	delHtp.onload = function() {
		let result = JSON.parse(delHtp.responseText);
		
		if(result.retCode == 'OK'){
			//화면상에서 체크된 input을 삭제
			document.querySelectorAll('.list input[type="checkbox"]:checked').forEach(item =>{
				item.parentElement.parentElement.remove();
			})
		} else{
			alert('처리중 예외');
		  }
	}
}
//선택삭제함수 반복 실행
/*function delCheckedFnc(e){
	document.querySelectorAll('.list input[type="checkbox"]:checked').forEach(item =>{
		
	let rno = item.parentElement.nextElementSibling.innerHTML;
			
	const delHtp = new XMLHttpRequest();
	delHtp.open('get', 'removeReply.do?rno=' + rno); //컨트롤 지정.
	delHtp.send();
	delHtp.onload = function(){ //onload를 읽어보는 이유는 정상삭제처리를 확인하기 위함
		let result = JSON.parse(delHtp.responseText);
		if(result.retCode == 'OK'){
			item.parentElement.parentElement.remove();
		} else if (result.retCode == 'NG'){
			alert("알 수 없는 예외 발생.");
		} else {"잘못된 반환 코드"} //OK,NG 말고 다른거 넘어오면 코드가 잘못된거임
	} 			
	
	})
}*/