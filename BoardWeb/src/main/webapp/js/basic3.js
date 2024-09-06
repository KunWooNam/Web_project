/**
 * baisc3.js
 */

//클릭이벤트가 발생하면 해야할 일.
//tr요소를 생성해서 id, name, point의 각 요소의 값을 td에 담아서 append
//만들어진 tr을 tbbdy에 append
let btn = document.querySelector('#addBtn')
btn.addEventListener('click', 
					function(){
	let tr = document.createElement('tr');
	let obj = {
		id: document.querySelector('#id').value,
		name: document.querySelector('#name').value,
		point: document.querySelector('#point').value};
	
	for(let prop in obj){
		let td = document.createElement('td');
		td.innerHTML = obj[prop];
		tr.appendChild(td);}
	
	//tbody의 하위 요소로 추가
	let list = document.querySelector('#list');
	
	//초기화
	document.querySelector('#id').value = '';
	document.querySelector('#name').value = '';
	document.querySelector('#point').value = '';
	
	list.appendChild(tr);
});

/*
let td = document.createElement('td');
td.append.querySelector('#id').value;
tr.appendChild(td);

td = document.createElement('td');
td.append.document.querySelector('#name').value;
tr.appendChild(td);

td = document.createElement('td');
td.append.document.querySelector('#point').value;
tr.appendChild(td);

let list = document.querySelector('#list');

let btn = document.querySelector('#addBtn')
btn.addEventListener('click', 
					function(){list.appendChild(tr);}
					);
*/