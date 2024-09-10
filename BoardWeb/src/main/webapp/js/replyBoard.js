/**
 * replyBoard.js
 * replyService에 정의된 메서드를 통해서 기능 실행.
 */
console.log(svc); // replyService.js에 정의된 svc 객체
console.log(bno, writer);


/*-----------------------------
	이벤트(댓글등록)
------------------------------ */
document.querySelector('#addReply').addEventListener('click', addReplyFnc);


/*-----------------------------
 *  댓글 목록 그리기
 *------------------------------*/

svc.replyList(bno, //원본 글번호 
	function(result) {
		console.log(result);
		console.log(reply);
		result.forEach(reply => {
			console.log(reply)
			document.querySelector('div.content ul').appendChild(makeLi(reply));
		});
	}, function(err) {
		console.log(err);
	}//실패했을 때 실행함수
);

/*------------------
 댓글정보 -> li 생성하는 함수.
--------------------*/
function makeLi(reply = {}) {
	let cloned = document.querySelector('#template').cloneNode(true); //board.jsp에 있는 id="template"인 노드 완전복제 
	cloned.style.display = 'block'; //display속성도 복사되기 때문에 block을 변경
	cloned.setAttribute('data-rno', reply.replyNo);
	cloned.querySelector('span').innerHTML = reply.replyNo;
	cloned.querySelector('span:nth-of-type(2)').innerHTML = reply.reply;
	cloned.querySelector('span:nth-of-type(3)').innerHTML = reply.replyer;
	cloned.querySelector('button').addEventListener('click', deleteLiFnc);
	console.log(cloned);
	return cloned;
}

/*--------------------------------
	함수: deleteLiFnc
	기능: 버튼이 포함된 row 삭제.(ajax포함)
----------------------------------*/
function deleteLiFnc(e) {
	// SweetAlert 
	Swal.fire({
		title: "진심 삭제?",
		text: "이 결정을 취소하실 수 있습니다!",
		icon: "warning",
		showCancelButton: true,
		confirmButtonColor: "#3085d6",
		cancelButtonColor: "#d33",
		confirmButtonText: "삭제하기!"
	}).then((result) => {
		if (result.isConfirmed) {
			Swal.fire({
				title: "삭제완료!!",
				text: "삭제 되었습니다.",
				icon: "success"
			});
		}
	}); //스윗알림 나중에 다시
	let rno = e.target.parentElement.parentElement.dataset.rno;
	svc.removeReply(rno, //삭제글번호
		function(result) {
			if (result.retCode == 'OK') {
				e.target.parentElement.parentElement.remove();
			} else {
				alert('삭제처리 중 예외발생');
			}
		},
		function(err) {
			console.log(err);
		}
	);
}

/*------------------------------
	댓글등록 이벤트핸들러.
------------------------------*/
function addReplyFnc(e) {
	//bno, replyer, reply
	//로그인 정보, 댓글정보가 있는지 확인하고 처리를 진행
	if (!writer || !reply) {
		alert("필수 입력값 확인");
		return;
	}
	//입력값 정보 확인
	let param = {
		bno: bno
		, replyer: writer
		, reply: document.querySelector('#reply').value
	}
	// svc객체의 addReply 메서드 호출
	svc.addReply(param
		, function(result) {
			let newReply = result.retVal; //신규로 입력된 댓글정보
			Swal.fire({
				title: "성공!",
				text: "등록 성공!",
				icon: "success"
			});
			if (result.retCode == 'OK') {
			} else {
				alert('추가처리 중 예외발생');
			}
		},
		function() {
			console.log();
		}
	);
}
