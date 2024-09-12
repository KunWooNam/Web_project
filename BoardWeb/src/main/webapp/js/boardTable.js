/**
 * boardTable.js
 */
const table = new DataTable('#example', {
	ajax: 'replyTable.do?bno=' + bno,
	columnDefs: [
        {
            render: (data, type, row) => '<button onclick="deleteRow('+row.replyNo+')">삭제</button>', 
            targets: 4
        },
        //{ visible: false, targets: [3] }
    ],
	columns: [
		{ data: 'replyNo' },
		{ data: 'reply' },
		{ data: 'replyer' },
		{ data: 'replyDate' },
	],
	lengthMenu: [
		[5, 10, 20, -1],
		[5, 10, 20, 'All']
	],
	order: {
		idx: 0,
		dir: 'desc'
	},
});

//deleteRow()
function deleteRow(e){
	console.log(e); // => delNumber 
	/*if(e.target.parentElement.parentElement.classList.contains('selected')){
		e.stopPropagation(); //상위요소 이벤트 차단(클릭할때마다 행 색상 바뀌는것),면접에도나옴	
	}*/
	const rno = e;
	//Ajax
	fetch('removeReply.do', {
		method: 'post',
		headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
		body: 'rno=' + rno
	})
		.then(resolve => resolve.json())
		.then(result => {
			console.log(result)
		})
		.catch(err => console.log(err));
}

//삭제기능 구현을 위한 rno 번호
let delNum = '';
//삭제기능 구현
document.querySelector('#delReply').addEventListener('click', function() {
	//Ajax 호출.
	delNum = document.querySelector('.selected').firstChild.innerHTML;
	console.log(delNum);
	fetch('removeReply.do', {
		method: 'post',
		headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
		body: 'rno=' + delNum
	})
		.then(resolve => resolve.json())
		.then(result => {
			console.log(result)
		})
		.catch(err => console.log(err));
	table.row('.selected').remove().draw(false);
});
//row 선택
table.on('click', 'tbody tr', (e) => {
	console.log(e.currentTarget);
	let classList = e.currentTarget.classList;

	if (classList.contains('selected')) {
		classList.remove('selected');
	}
	else {
		table.rows('.selected').nodes().each((row) => row.classList.remove('selected'));
		classList.add('selected');
	}
});





//#addpely 클릭
//replyNo:111, reply:test, replyer: user01, replyDate: 2024.11.11
document.querySelector('#addReply').addEventListener('click', addNewRow);

//화면에 데이터 추가하는 함수
function addNewRow() {
	//입력값 정보 확인
	let param = {
		bno: bno
		, replyer: writer
		, reply: document.querySelector('#reply').value
	}
	console.log(param.replyer);
	if (!writer || !reply) {
		alert("필수 입력값 확인");
		return;
	}
	fetch('addReply.do', {
		method: 'post',
		headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
		body: 'bno=' + param.bno + '&reply=' + param.reply + '&replyer=' + param.replyer
	})
		.then(resolve => resolve.json())
		.then(result => {
			console.log(result);
			console.log(result.retVal.replyer);
			document.querySelector('#reply').value = "";
			table.row
				.add({
					replyNo: result.retVal.replyNo,
					reply: result.retVal.reply,
					replyer: result.retVal.replyer,
					replyDate: result.retVal.replyDate
				})
				.draw(false);
		})
		.catch(err => console.log(err));
	//console.log("check");
}


