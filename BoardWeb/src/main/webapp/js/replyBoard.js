/**
 * replyBoard.js
 * replyService에 정의된 메서드를 통해서 기능 실행.
 */
console.log(svc); // replyService.js에 정의된 svc 객체
console.log(bno, writer);
let pagination
//DOM 요소를 다 읽어들인 다음에 코드실행
document.addEventListener("DOMContentLoaded", function(e) {
	//이벤트등록하는것들은 다 이곳으로
	pagination = document.querySelector('ul.pagination');
	//댓글등록, 페이지링크클릭,댓글목록출력

	//댓글등록
	document.querySelector('#addReply').addEventListener('click', addReplyFnc);
	//페이지링크 클릭
	document.querySelectorAll('ul.pagination a').forEach(aTag => {
		aTag.addEventListener('click', showReplyListFnc);
	})
	/*-----------------------------
	 *  댓글 목록 출력
	 *------------------------------*/

	svc.replyList({ bno, page }, //원본 글번호 
		function(result) {
			console.log(result);
			console.log(reply);
			result.forEach(reply => {
				console.log(reply)
				document.querySelector('div.content ul').appendChild(makeLi(reply));
			});
			showPagingListFnc();
		}, function() {
			console.log("에러발생");
		}//실패했을 때 실행함수
	);
});

/*-----------------------------
	이벤트(댓글등록)
------------------------------ */


let page = 1; // 페이지변경될때마다 사용해야함.


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
		title: "정말 삭제하시겠습니까?",
		text: "삭제 한 댓글은 되돌릴 수 없습니다.",
		icon: "warning",
		showCancelButton: true,
		confirmButtonColor: "#3085d6",
		cancelButtonColor: "#d33",
		confirmButtonText: "삭제하기!"
	}).then((result) => {
		let rno = e.target.parentElement.parentElement.dataset.rno;
		svc.removeReply(rno, //삭제글번호
			function(result) {
				if (result.retCode == 'OK') {
					//e.target.parentElement.parentElement.remove();
					showReplyListAndPagingList();
				} else {
					alert('삭제처리 중 예외발생');
				}
			},
			function(err) {
				console.log(err);
			}
		);
	});
}
//스윗알림 나중에 다시

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
				/*showReplyListFnc();*/
				page = "1";
				showReplyListAndPagingList();
			} else {
				alert('추가처리 중 예외발생');
			}
		},
		function() {
			console.log();
		}
	);
}

/*-----------------------
   링크클릭시 댓글 목록 새로출력.
-----------------------*/
function showReplyListFnc(e) {
	page = e.target.dataset.page; //페이지번호
	//함수호출로 대체
	showReplyListAndPagingList();
}

/*----------------------------
	댓글갯수를 활용해서 페이지리스트 생성
	함수: showPaginListFnc
------------------------------*/


showPagingListFnc();

function showPagingListFnc() {
	svc.replyPagingCount(bno, // 글 번호
		makePagingList, //성공했을때 실행할 콜백함수. callback
		function(err) {
			console.log(err);
		}
	)
};


//정상처리 실행할 콜백함수
function makePagingList(result) {
	pagination.innerHTML = ''; //기존 페이지 리스트 지우기
	console.log(result); //확인용
	let totalCnt = result.totalCount; //확인용
	console.log(totalCnt); //확인용
	//페이지목록 만들기
	let startPage, endPage, realEnd; // 첫페이지 ~ 마지막페이지
	let prev, next; // 이전페이지, 이후페이지

	endPage = Math.ceil(page / 5) * 5;
	startPage = endPage - 4; // 6~10 
	realEnd = Math.ceil(totalCnt / 5);
	endPage = endPage > realEnd ? realEnd : endPage;

	prev = startPage > 1;
	next = endPage < realEnd;

	//이전 페이지 생성
	let li = document.createElement('li');
	li.className = 'page-item'; //class속성 부여
	let aTag = document.createElement('a');
	aTag.className = 'page-link';
	aTag.innerHTML = "Previous";
	li.appendChild(aTag);
	if (prev) {
		aTag.setAttribute('href', '#'); //왜 #?			
		aTag.setAttribute('data-page', startPage - 1) //data-page가젤좋다.
	} else {
		li.classList.add('disabled');
	}

	pagination.appendChild(li);

	// li 생성. (페이지 범위에 들어갈 li 생성중)
	for (let p = startPage; p <= endPage; p++) {
		let li = document.createElement('li');
		li.className = 'page-item'; //class속성 부여
		let aTag = document.createElement('a');
		aTag.className = 'page-link';
		aTag.setAttribute('href', '#');
		aTag.setAttribute('data-page', p) //data-page가젤좋다.
		aTag.innerHTML = p;
		li.appendChild(aTag);
		if (p == page) {
			li.classList.add('active');
			li.setAttribute('aria-current', 'page');
		}
		pagination.appendChild(li);
	}

	// 이후 페이지 생성
	li = document.createElement('li');
	li.className = 'page-item'; //class속성 부여
	aTag = document.createElement('a');
	aTag.className = 'page-link';
	aTag.innerHTML = "Next";
	li.appendChild(aTag);
	if (next) {
		aTag.setAttribute('href', '#');
		aTag.setAttribute('data-page', endPage + 1)
	} else {
		li.classList.add('disabled');
	}

	pagination.appendChild(li);

	//이벤트 등록.
	document.querySelectorAll('ul.pagination a').forEach(aTag => {
		aTag.addEventListener('click', showReplyListFnc);
	})
}

// 
function showReplyListAndPagingList() {
	svc.replyList({ bno, page },
		function(result) {// 원본글정보
			// 기존의 출력 리스트를 삭제
			document.querySelectorAll('div.content li').forEach((li, idx) => {
				if (idx > 2) {
					li.remove();
				}
			})
			// 새페이지의 댓글 리스트를 출력

			result.forEach(reply => {
				console.log(reply)
				document.querySelector('div.content ul').appendChild(makeLi(reply));
			});
			showPagingListFnc();
		}, function() {
			console.log("에러발생");
		}//실패했을 때 실행함수
	);
}
