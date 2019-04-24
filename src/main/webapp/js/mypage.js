$(function(){	
	//간편결제 버튼 클릭시 
	$("#mypage-card-ok").click(function(){
		$("#card-submit").show();
	}); 
	//간편결제 x버튼 클릭시 
	$("#card-x").click(function(){
		$("#card-submit").hide();
	});
	
	//카드버튼 클릭시 경고글
	$("#card-btn").click(function(){
		var cardNum1 = $("#cardNum1").val();
		var cardNum2 = $("#cardNum2").val();
		var cardNum3 = $("#cardNum3").val();
		var cardNum4 = $("#cardNum4").val();
		
		var cardDate = $("#cardDate").val();
		
		var cardName = $("#cardName").val();
		
		var cardPwd = $("#cardPwd").val();
		
		if(cardNum1 == "" || cardNum2 == "" || cardNum3 == "" || cardNum4 == ""
			|| cardDate == "" || cardName =="" || cardPwd == ""){
			$("#card-warning").text("모든 정보를 입력해주세요:)");
			return false;
		}
	});
	
	//서포터즈 - 나의 리워드
	$("#mypage-reward").click(function(){
		$("#myReward").css('display','block');
		$("#my-like").css('display','none');
		$("#mypage-reward").css('text-decoration','underline').css('color','#92D1E0');
		$("#mypage-like").css('text-decoration','none').css('color','black');
	});
	//서포터즈 - 좋아요
	$("#mypage-like").click(function(){
		$("#myReward").css('display','none');
		$("#my-like").css('display','block');
		$("#mypage-reward").css('text-decoration','none').css('color','black');
		$("#mypage-like").css('text-decoration','underline').css('color','#92D1E0');
	});
	
	//크리에이트 - 진행중
	$("#mypage-starting").click(function(){
		$("#cr-list").css('display','block');
		$("#cr-list-waiting").css('display','none');
		$("#mypage-starting").css('text-decoration','underline').css('color','#92D1E0');
		$("#mypage-waiting").css('text-decoration','none').css('color','black');
	});
	//크리에이트 - 승인대기중
	$("#mypage-waiting").click(function(){
		$("#cr-list-waiting").css('display','block');
		$("#cr-list").css('display','none');
		$("#mypage-waiting").css('text-decoration','underline').css('color','#92D1E0');
		$("#mypage-starting").css('text-decoration','none').css('color','black');
	});
	
	//서포터즈
	$("#mypage-su").click(function(){
		$("#mypage-su").css('background','#021044').css('color','white');
		$("#mypage-cr").css('background','white').css('color','black');
		
		$("#mypage-reward").show();
		$("#myReward").show();
		
		$("#mypage-like").show();
		
		$("#cr-list").hide();
		$("#cr-list-waiting").hide();
		
		$("#mypage-waiting").hide();
		$("#mypage-starting").hide();
		
		$("#mypage-reward").css('text-decoration','underline').css('color','#92D1E0');
		$("#mypage-like").css('text-decoration','none').css('color','black');
	});
	
	//크리에이터
	$("#mypage-cr").click(function(){
		$("#mypage-su").css('background','white').css('color','black');
		$("#mypage-cr").css('background','#021044').css('color','white');
		
		$("#cr-list").show();
		
		$("#mypage-waiting").show();
		$("#mypage-starting").show();
		
		$("#mypage-reward").hide();
		$("#myReward").hide();
		
		$("#mypage-like").hide();
		$("#my-like").hide();
		
		$("#mypage-starting").css('text-decoration','underline').css('color','#92D1E0');
		$("#mypage-waiting").css('text-decoration','none').css('color','black');
	});
	
	//ajax - 간편결제 삭제 (update)
	/*$("#mypage-card-delete").click(function(){
		
		var uri = "/bridge/cardDelete"
		var param = "userMail="+$("#userMail").val();
		
		$.ajax({
			type:"POST",
			url:uri,
			data:param,
			success:function(result){
				alert("카드 정보가 삭제되었습니다.");
				
				$("#mypage-card-delete").css("display","none");
				$("#mypage-card-ok").css("display","block");
				
			},
			error:function(error){
						
			}
		});
	});*/
	
	//ajax - 간편결제 삭제 (update)
	$("#mypage-card-delete").click(function() {
		if (confirm("카드 정보를 삭제하시겠습니까?") == true) { // 확인
			// ajax - 간편결제 삭제 (update)

			var uri = "/bridge/cardDelete"
			var param = "userMail=" + $("#userMail").val();

			$.ajax({
				type : "POST",
				url : uri,
				data : param,
				success : function(result) {
					/*$("#mypage-card-delete").css("display", "none");
					$("#mypage-card-ok").css("display", "block");*/
					$("#mypage-card-delete").hide();
					$("#mypage-card-ok").show();

				},
				error : function(error) {

				}
			});

		} else { // 취소
			return;
		}
	});
	
	
	$("#mypage-reward").click(function(){
		menu = "my";
		//ajax - 나의 리워드
		var uri = "/bridge/selectMyReward"
		var param = "userMail="+$("#userMail").val();
		
		$.ajax({
			type:"POST",
			url:uri,
			data:param,
			success:function(result){
				var $result = $(result);
				txt = "<div class='list-content'>";
				$result.each(function(idx,data){
					txt += "<ul class='project-view'>";
					txt += "<li>";
					txt += "<span class='cate-txt'>"+data.cateName+"</span>";
					/*txt += "<img alt='상품이미지' src='/bridge/ckstorage/"+data.proImg+"' onerror='this.src=\"/bridge/img/profile/noImg.png\"'>";*/
					txt += "<a href='/bridge/deTailPage?cateCode="+data.cateCode+"&proCode="+data.proCode+"'><img alt='상품이미지' src='/bridge/ckstorage/"+data.proImg+"' onerror='this.src=\"/bridge/img/profile/noImg.png\"'></a>";
					txt += "<h4>"+data.proName+"</h4>";
					txt += "<button class='like-btn'>좋아요</button>";
					txt += "<ul class='detail-info'>" +
								"<li class='col1'>" +
									"<span>목표달성</span>" +
									"<strong>"+data.proGoal+"</strong>" +
								"</li>" +
								"<li class='col2'>" +
									"<span>판매금액</span>" +
									"<strong>"+data.proNow+"</strong>" +
								"</li>" +
								"<li class='col3'>" +
									"<span>종료일</span>" +
									"<strong>"+data.proEnd+"</strong>" +
								"</li>" +
							"</ul>";
					txt += "</li>";
					txt += "</ul>";
				});
				txt += "</div>";
				$("#myReward").html(txt);
			},
			error:function(error){
						
			}
		});
	});
	
	$("mypage-su").click(function(){
		$("#mypage-reward").trigger("click");
	});
	
	//ajax - 좋아요 프로젝트
	$("#mypage-like").click(function(){
		menu = "like";
		var uri = "/bridge/selectLike"
		var param = "userMail="+$("#userMail").val();
				
		$.ajax({
				type:"POST",
				url:uri,
				data:param,
				success:function(result){
					var $result = $(result);
					txt = "<div class='list-content'>";
					$result.each(function(idx,data){
						txt += "<ul class='project-view'>";
						txt += "<li>";
						txt += "<span class='cate-txt'>"+data.cateName+"</span>";
						/*txt += "<img alt='상품이미지' src='/bridge/ckstorage/"+data.proImg+"' onerror='this.src=\"/bridge/img/profile/noImg.png\"'>";*/
						txt += "<a href='/bridge/deTailPage?cateCode="+data.cateCode+"&proCode="+data.proCode+"'><img alt='상품이미지' src='/bridge/ckstorage/"+data.proImg+"' onerror='this.src=\"/bridge/img/profile/noImg.png\"'></a>";
						txt += "<h4>"+data.proName+"</h4>";
						txt += "<button class='like-btn'>좋아요</button>";
						txt += "<ul class='detail-info'>" +
									"<li class='col1'>" +
										"<span>목표달성</span>" +
										"<strong>"+data.proGoal+"</strong>" +
									"</li>" +
									"<li class='col2'>" +
										"<span>판매금액</span>" +
										"<strong>"+data.proNow+"</strong>" +
									"</li>" +
									"<li class='col3'>" +
										"<span>종료일</span>" +
										"<strong>"+data.proEnd+"</strong>" +
									"</li>" +
								"</ul>";
						txt += "</li>";
						txt += "</ul>";
					});
					txt += "</div>";
					$("#my-like").html(txt);
				},
				error:function(error){
							
				}
			});
		});
		
	
	$("#mypage-starting").click(function(){
		menu = "make";
	//ajax - 진행중인 프로젝트
		var uri = "/bridge/selectStartingPro"
		var param = "userMail="+$("#userMail").val();
		
		$.ajax({
			type:"POST",
			url:uri,
			data:param,
			success:function(result){
				var $result = $(result);
				var txt = "<table class='cr-list-table'>";
				var procodeTxt = "";
				$result.each(function(idx,data){
					txt += "<tbody>";
					txt += "<tr>";
					txt += "<th>";
					txt += "<a href='/bridge/deTailPage?cateCode="+data.cateCode+"&proCode="+data.proCode+"'>"+
					       "<div class='cr-list-img'><img class='cr-list-img' src='/bridge/ckstorage/"+data.proImg+"' onerror='this.src=\"/bridge/img/profile/noImg.png\"'/></div>" +
						   "<div class='cr-list-cat'>"+data.cateName+"<br/>"+data.proName+"</div></a>";
					txt += "</th>"; 	
					txt += "<th>";
					txt += "<div class='cr-list-last'>최종펀딩금액</div>";
					txt += "<div class='cr-list-goal'>(목표금액"+data.proGoal+"원)</div>"
					txt += "<div>"+data.proNow+"원</div>";
					txt += "</th>";
					txt += "<th>";	
					txt += "<div class='cr-list-pro'>프로젝트기간</div>" +
							"<div>시작일 &nbsp;"+ data.proOpen +"</div>" +
							"<div>종료일 &nbsp;"+ data.proEnd + "</div>";
					txt += "</th>";
					txt += "<th><div class='cr-list-like'><a href='javascript:likeList(\"" 
					txt	+= data.proCode+"\")'>좋아요<br/>리스트 확인</a></div></th>" +
							"<th><div class='cr-list-funding'><a href='javascript:fundingList(\"" 
					txt +=	data.proCode+"\")'>펀딩<br/>리스트 확인</a></div></th>";			
					txt += "</tr>";	
					txt += "</tbody>";
					txt += "<div hidden='hidden'>"+data.proCode+"</div>"; //프로젝트 코드 11
					procodeTxt += data.proCode;
				});
				txt +="<table>";
				
				$("#cr-list").html(txt);
				$("#proCode").val(procodeTxt);
			},
			error:function(error){
				$("#warning-text").html("진행중 ERROR :(");
			}
		});
	});
	
	$("#mypage-cr").click(function(){
		$("#mypage-starting").trigger("click");
	});
	
	//ajax - 승인대기중
	$("#mypage-waiting").click(function(){
		var uri = "/bridge/selectWaitingPro"
		var param = "userMail="+$("#userMail").val();	
	
		$.ajax({
			type:"POST",
			url:uri,
			data:param,
			success:function(result){
				var $result = $(result);
				txt = "<table class='cr-list-table'>";
				$result.each(function(idx,data){
					txt += "<tbody>";
					txt += "<tr>";
					txt += "<th>";
					txt += "<div class='cr-list-img'><img class='cr-list-img' src='/bridge/ckstorage/"+data.proImg+"' onerror='this.src=\"/bridge/img/profile/noImg.png\"'/></div>" +
							"<div class='cr-list-cat'>"+data.proName+"</div>";
					txt += "</th>"; 	
					txt += "<th>";	
					txt += "<div class='cr-list-goal-waiting'>목표금액"+data.proGoal+"원</div>";
					txt += "</th>";
					txt += "<th>";	
					txt += "<div class='cr-list-pro-waiting'>프로젝트기간</div>";
					if(data.proDate == 15){
						txt += "<div>"+data.proDate+"일</div>";
					}else{
						txt += "<div>"+data.proDate+"개월</div>";
					}
					txt += "</th>";
					txt += "<th>";
					txt += "<div class='cr-list-like'>";
					txt += "<a href='/bridge/preview?userMail="+data.userMail+"&proNum="+data.proNum+"&proCode="+data.proCode+"'>미리보기</a>";
					txt += "</div>";
					txt +=	"</th>";
					txt += "<th>";
					txt += "<div class='cr-list-like'>";
					txt += "<a href='/bridge/inputProject2?userMail="+data.userMail+"&proNum="+data.proNum+"&proCode="+data.proCode+"'>수정하기</a>";
					txt += "</div>";
					txt +=	"</th>";
					txt += "</tr>";	
					txt += "</tbody>";
				});
				txt +="<table>";
				$("#cr-list-waiting").html(txt);
			},
			error:function(error){
				$("#warning-text").html("승인 대기중 ERROR :(");
			}
		});
	});
	
	//메뉴 구분
	if(menu == "my"){
	
		$("#mypage-reward").trigger("click");
		
	}else if(menu =="like"){
		
		$("#mypage-like").trigger("click");
		
	}else if(menu == "make"){
		
		//$("#mypage-starting").trigger("click");
		$("#mypage-cr").trigger("click");
	}
	
});


function likeList(proCode){
	//좋아요 리스트
	$("#like-list-div").css('display','block');
	$("#like-wrap").css('display','block');
	$("#like-wrap").css('width',$(window).width()+'px').css('height',$(window).height()+'px');
	$("body").css('height',$(window).height()+'px');
	
		//ajax
		var uri = "/bridge/likeList"
		var param = "userMail="+$("#userMail").val()+"&proCode="+proCode;
		
		$.ajax({
			type:"POST",
			url:uri,
			data:param,
			success:function(result){
				var $result = $(result);
				var txt = "<div class='like-list-div'>";
					txt += "<div class='like-card-x'><a href='javascript:closeX()'>&Chi;</a></div>";
				$result.each(function(idx,data){
					
					txt += "<hr/>";
					txt += "<img class='like-list' src='/bridge/upload/"+data.userImg+"' onerror='this.src=\"/bridge/img/profile/user.png\"'/>";
					txt += "<div class='like-text'>"+data.userName+"님이 좋아요를 눌렀습니다.</div>";
					txt += "<hr/>";
				});
				txt += "</div>";
				$("#like-wrap").html(txt);
			},
			error:function(error){
				alert(error.responseText);
			}
		});
}

function closeX(){
	//좋아요 리스트 x버튼 클릭시 
	$("#like-list-div").css('display','none');
	$("#like-wrap").css('display','none');
}

function fundingList(proCode){
	//펀딩 리스트
	$("#funding-list-div").css('display','block');
	$("#funding-wrap").css('display','block');
	$("#funding-wrap").css('width',$(window).width()+'px').css('height',$(window).height()+'px');
	$("body").css('height',$(window).height()+'px');
	
	//ajax
	var uri = "/bridge/fundingList"
	var param = "proCode="+proCode;
		
	$.ajax({
		type:"POST",
		url:uri,
		data:param,
		success:function(result){
			var $result = $(result);
			var txt = "<div class='funding-list-div'>";
				txt += "<div class='funding-card-x'><a href='javascript:closeX2()'>&Chi;</a></div>";
			$result.each(function(idx,data){
				txt += "<hr/>";
				txt += "<img class='funding-list' src='/bridge/upload/"+data.userImg+"' onerror='this.src=\"/bridge/img/profile/user.png\"'/>";
				txt += "<div class='funding-text'>"+data.userName+"님이 펀딩하였습니다.</div>";
				txt += "<hr/>";
			});
			txt += "</div>";
			$("#funding-wrap").html(txt);
		},
		error:function(error){
			alert(error.responseText);
		}
	});
}

function closeX2(){
	//펀딩 리스트 x버튼 클릭시 
	$("#funding-list-div").hide();
	$("#funding-wrap").css('display','none');
}