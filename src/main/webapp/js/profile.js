//파일 읽어 이미지 띄워주는 부분
var num = 0;
var openFile = function(event){
	var input = event.target;
		
	var reader = new FileReader();
	reader.onload = function(){
		var dataURL = reader.result;
		var output = document.getElementById("profile-img");
		output.src = dataURL;
	};
	reader.readAsDataURL(input.files[0]);
};

$(function(){
	///////////////////////////////////////////////////
	//생년월일 설정
	//년
	var date = new Date();
	var year = date.getFullYear();
	var selectValue = document.getElementById("year");
	var optionIndex = 0;
			
	for(var i=year-80; i<=year; i++){
		selectValue.add(new Option(i+"년", i),optionIndex++);
	}
	
	//월
	var selectValue = document.getElementById("month");
	var optionIndex = 0;
			
	for(var i=1; i<=12; i++){
		selectValue.add(new Option(i+"월", i),optionIndex++);
	}
	
	//일
	var selectValue = document.getElementById("day");
	var optionIndex = 0;
			
	for(var i=1; i<=31; i++){
		selectValue.add(new Option(i+"일", i),optionIndex++);
	}
	///////////////////////////////////////////////////
});
	