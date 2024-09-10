/**
 * fetch.js(비동기처리: 서버상의 리소스 처리)
 */
/*-------------
초기 변수선언하는 부분
---------------*/
let bno = 149;
let writer = 'user01';
console.log(fields);
/*-------------
예제 fetch 연습
---------------*/
fetch('js/data.js') // promise객체 반환.-> 정상실행일 경우 then(함수),이라는 메서드에 정의되어있는 함수를 호출
 					// 				 -> 비정상실행일 경우 catch(함수) 실행
.then(function(resolve){ //resovle => js/data.js에서 처리된 결과값이 들어가있음
	console.log(resolve); //response 객체
	return resolve.text(); //resolve의 값을 text형식으로 출력시킬때text(),그리고 이걸 또 반환시킬수있음
})

.then(function(result){ //위에서 반환된 결과(result에 담음)를 재사용가능
	consoles.log(result);
	let json = result.substring(result.indexOf('['), result.indexOf(']')+1); //[의 위치]의 위치 사이의 substring.
	console.log(JSON.parse(json)); //json문자열을 js객체로 파싱하는 작업
})

.catch(function(err){
	console.log('에러가 발생', err);
})


/*-------------
 이벤트 (등록)
---------------*/
document.querySelector('#addReply').addEventListener('click', addReplyFnc);

/*-------------
 함수들
---------------*/
function addReplyFnc(e){ //e = 이벤트가 일어난대상
	let reply = document.querySelector('#reply').value;
	
	fetch('addReply.do',{ //참고. 주소표시줄 addReply.do?bno=148&reply=test&replyer=user01 -> get방식
		method: 'post',   //post방식
		headers: {'Content-Type' : 'application/x-www-form-urlencoded'},
		body: 'bno=' + bno + '&reply=' + reply + '&replyer=' + writer //이렇게하면 fetch함숙 알아서해줌
	}) 
	.then(resolve => resolve.json())
	.then(result => {
		console.log(result); //꼭 결과값 확인해보기
		//정상일 경우 => result.retCode 확인.
		if(result.retCode == "OK"){
			list.appendChild(makeRow(result.retVal)); //? 
		} else {
			alert("처리 중 예외발생")
		}
	})
}

/*-------------
서버의 댓글목록 요청작업
---------------*/
const list = document.querySelector('tbody.list');

fetch('replyList.do?bno=' + bno)
.then(resolve => resolve.json()) //응답객체(response)의 정보를 json문자열 -> 객체 변경메서드:json()

.then(result=>{
	console.log(result); //[배열]
	//반복처리
	result.forEach(reply=>{
		let tr = makeRow(reply);
		list.appendChild(tr);
	})
})
.catch(err =>{
	console.log('catch예외',err);
})

/*---------------
  삭제처리를 위한 함수
----------------*/
function deleteRowFnc(e) {
	let rno = e.target.parentElement.parentElement.dataset.rno; //2번째있는 atrribute값 적어주면 값을 가져옴.<tr data-rno=숫자
	
	fetch('removeReply.do?rno=' + rno)
	.then(resolve => resolve.json())
	.then(result => {
		if(result.retCode == 'OK'){
			alert("삭제완료");
			e.target.parentElement.parentElement.remove();
		} else {
			alert("삭제 처리중 예외발생");
		}
	}) 
}