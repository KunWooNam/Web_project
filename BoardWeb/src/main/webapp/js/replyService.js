/**
 * replyService.js
 */

/*--------------
 날짜 포맷: yyyy-MM-dd HH:mm:ss 반환하는 메서드를 Date객체에 추가.
 ---------------*/
Date.prototype.dateFormat = function() { //Date 객체에 메서드추가할땐 프로토타입에 함.
	let yyyy = this.getFullYear();
	let MM = ('0' + (this.getMonth() + 1)).slice(-2); //09. 10
	let dd = ('0' + this.getDate()).slice(-2);

	let hh = ('0' + this.getHours()).slice(-2);
	let mm = ('0' + this.getMinutes()).slice(-2);
	let ss = ('0' + this.getSeconds()).slice(-2);
	return yyyy + '-' + MM + '-' + dd + '' + hh + ":" + mm + ":" + ss; // Ex)2024-09-12 12:23:23 
}




/*-------------------------
 ReplyVO 값을 tr생성해주는 함수.
 --------------------------*/
let fields = ['replyNo', 'reply', 'replyer', 'replyDate'];//항목들을 담고있는 필드 변수
// 댓글정보 -> tr>td*4 생성 및 반환해주는 함수
function makeRow(reply = {}) {
	console.log('reply=>', reply);
	let tr = document.createElement('tr');
	tr.setAttribute('data-rno', reply.replyNo);
	//체크박스 생성
	let btn = document.createElement('input');
	btn.setAttribute('type', 'checkbox')

	let td = document.createElement('td');

	td.appendChild(btn);
	tr.appendChild(td);
	//td생성
	fields.forEach(field => {
		let td = document.createElement('td');
		if (field == 'replyDate') {
			let today = new Date(reply[field]); //날짜문자 -> 날짜객체.dateFormat()출력.
			td.innerHTML = today.dateFormat(); //replyDate넣을때는 안의 content를만들었던 형식의 date를 넣도록
		} else {
			td.innerHTML = reply[field]
		}
		tr.appendChild(td);
	})


	//댓글 삭제 버튼
	//삭제버튼
	btn = document.createElement('button'); //<button>삭제</button>
	btn.innerHTML = '삭제';
	btn.addEventListener('click', deleteRowFnc); //함수이름만 넣어줘야 이벤트발생시 함수실행

	td = document.createElement('td'); //<td><button>삭제</button></td>
	td.appendChild(btn);
	tr.appendChild(td);

	return tr;
}

// Service() 라고하는 메서드를 통해서 ajax기능을 실행.
// 1. 목록기능 2. 삭제기능 3. 추가기능 4...(추가예정)
const svc = {
	replyList: function(bno = 149, successCallback, erroCallback) {
		fetch('replyList.do?bno=' + bno)
			.then(resolve => resolve.json()) //결과를 다 json으로 변환하게금 해놨음
			.then(successCallback) //셋 중 뭐가 실행될지모름, 매개값에따라 다름
			.catch(erroCallback)
	},
	removeReply(rno = 1, successCallback, errorCallback) {
		fetch('removeReply.do?rno=' + rno)
			.then(resolve => resolve.json()) //결과를 다 json으로 변환하게금 해놨음
			.then(successCallback) //셋 중 뭐가 실행될지모름, 매개값에따라 다름
			.catch(erroCallback)
	},
	addReply(param = { bno, reply, replyer }, successCallback, errorCallback) {
		fetch('addReply.do', {
			method: 'post',
			headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
			body: 'bno=' + param.bno + '&reply=' + param.reply + '&replyer=' + param.replyer
		})
			.then(resolve => resolve.json()) //결과를 다 json으로 변환하게금 해놨음
			.then(successCallback) //셋 중 뭐가 실행될지모름, 매개값에따라 다름
			.catch(errorCallback)
	}
}