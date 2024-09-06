/**
 * 
 */
console.log(json); //문자열
let data = JSON.parse(json); //문자열 -> 객체변경
console.log(data); //객체
console.clear(); //보기 더러우니까 위에꺼 지우기


/*data.forEach(function(item, idex, ary){
	if(idex > ary.length-10){
		console.log(item);
	}
}); */

let result = data.forEach(function(item, idex, ary){ //객체,인덱스,배열은 정해져있는거임/foreach는 처리하고 반환값이 없는 함수다.
	if(item.salary >= 5 && item.gender =='Female'){
		console.log(item);
	}
}); 
console.log(result);

//filert는 ~한 조건을 만족할 경우 boolean값을 반환함 그리고 true시점의 객체를 배열로 반환.
result = data.filter(function(item, idex, ary){ 
	if(item.salary >= 5 && item.gender =='Female'){
		console.log(item);
	}
}); 
console.log(result);