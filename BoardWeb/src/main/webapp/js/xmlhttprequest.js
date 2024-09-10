/**
 * xmlhttprequest.js
 * 비동기처리 객체
 */
// javascript.do 페이지에서 js/MOCK_DATA.json 요청
const xhtp = new XMLHttpRequest(); //객체생성.
xhtp.open('get','js/MOCK_DATA.json'); // Ajax는 서버 페이지를 직접 요청. //http://localhost/BoardWeb/js/MOCK_DATA.json
xhtp.send();//서버요청.
xhtp.onload = function(){
	console.log(xhtp); //status 200, readyState 4 -> 정상처리됨을 의미
	console.log(xhtp.responseText); //xhtp객체의 responseText 속성 =>결과값.(js든 json이든)
	let result = JSON.parse(xhtp.responseText);//json문자열을 =>객체로 변경 (파싱), 배열형태로 변경됨 
	
	//보여줄 항목을 배열 담기
	let fields = ['id', 'first_name', 'last_name', 'gender', 'salary'];
	
	result.forEach(function(item, idx, ary){
		if(item.salary >= 50 && item.gender =='Female'){
			console.log(item); //{id,first_name,last_name} => tr>td*5 생성
			
			let tr = document.createElement('tr');
			//보여줄 필드 출력.
			fields.forEach(field => {
			let td = document.createElement('td');
			td.innerHTML = item[field]; // item.id, item.first_name, item.last_name...
			tr.appendChild(td);	
			})
			//요소생성.(삭제버튼)
			let btn = document.createElement('button'); //<button>삭제</button>
			btn.innerHTML = '삭제';
			btn.addEventListener('click',()=>{tr.remove();})
			let td = document.createElement('td');
			td.appendChild(btn);
			tr.appendChild(td);
			document.querySelector('.list').appendChild(tr);
		}		
	});
}

//jsp -> 완성된 html페이지
//ajax -> 데이터를 가져와서 DOM으로 페이지를만듬