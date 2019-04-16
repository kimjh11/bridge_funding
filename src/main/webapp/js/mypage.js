$(function(){	
	//간편결제 버튼 클릭시 
	$("#mypage-card-ok").click(function(){
		$("#card-submit").toggle();
	}); 
	//간편결제 x버튼 클릭시
	$("#card-x").click(function(){
		$("#card-submit").hide();
	})
	
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
	});
	
	//ajax - 간편결제 삭제 (update)
	$("#mypage-card-delete").click(function(){
		
		var uri = "/bridge/cardDelete"
		var param = "userMail="+$("#userMail").val();
		
		$.ajax({
			type:"POST",
			url:uri,
			data:param,
			success:function(result){
				alert("카드 정보가 삭제되었습니다.");
					$("#mypage-card-delete").replaceWith("<button id='mypage-card-ok'>간편결제등록</button>");
			},
			error:function(error){
						
			}
		});
	});
	
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
				txt += "<img alt='상품이미지' src='/bridge/ckstorage/"+data.proImg+"'>";
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
	
	
	//ajax - 좋아요 프로젝트
	$("#mypage-like").click(function(){
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
						txt += "<img alt='상품이미지' src='/bridge/ckstorage/"+data.proImg+"'>";
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
					txt += "<div class='cr-list-img'><img class='cr-list-img' src='/bridge/ckstorage/"+data.proImg+"'/></div>" +
							"<div class='cr-list-cat'>"+data.cateName+"</div>" +
							"<div>"+data.proName+"</div>";
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
					txt += "<th><div class='cr-list-like'><a href='javascript:likeList()'>좋아요<br/>리스트 확인</a></div></th>" +
							"<th><div class='cr-list-funding'><a href='javascript:fundingList()'>펀딩<br/>리스트 확인</a></div></th>"			
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
					txt += "<div class='cr-list-img'><img class='cr-list-img' src='/bridge/ckstorage/"+data.proImg+"'/></div>" +
							"<div class='cr-list-cat'>"+data.cateName+"</div>" +
							"<div>"+data.proName+"</div>";
					txt += "</th>"; 	
					txt += "<th>";	
					txt += "<div class='cr-list-goal-waiting'>목표금액"+data.proGoal+"원</div>";
					txt += "</th>";
					txt += "<th>";	
					txt += "<div class='cr-list-pro-waiting'>프로젝트기간</div>" +
							"<div>"+data.proDate+"개월</div>";
					txt += "</th>";		
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
	
});


function likeList(){
	//좋아요 리스트
	$("#like-list-div").css('display','block');
	$("#like-wrap").css('display','block');
	$("#like-wrap").css('width',$(window).width()+'px').css('height',$(window).height()+'px');
	$("body").css('height',$(window).height()+'px');
	
		//ajax
		var uri = "/bridge/likeList"
		var param = "userMail="+$("#userMail").val()+"&proCode="+$("#proCode").val();
			
		$.ajax({
			type:"POST",
			url:uri,
			data:param,
			success:function(result){
				var $result = $(result);
				var txt = "<div class='like-list-div'>";
				$result.each(function(idx,data){
					txt += "<div class='like-card-x'><a href='javascript:closeX()'>&Chi;</a></div>";
					txt += "<hr/>";
					txt += "<img class='like-list' src='/bridge/upload/"+data.userImg+"'/>";
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

function fundingList(){
	//펀딩 리스트
	$("#funding-list-div").css('display','block');
	$("#funding-wrap").css('display','block');
	$("#funding-wrap").css('width',$(window).width()+'px').css('height',$(window).height()+'px');
	$("body").css('height',$(window).height()+'px');
	
	//ajax
	var uri = "/bridge/fundingList"
	var param = "proCode="+$("#proCode").val();
		
	$.ajax({
		type:"POST",
		url:uri,
		data:param,
		success:function(result){
			var $result = $(result);
			var txt = "<div class='funding-list-div'>";
			$result.each(function(idx,data){
				txt += "<div class='funding-card-x'><a href='javascript:closeX2()'>&Chi;</a></div>";
				txt += "<hr/>";
				txt += "<img class='funding-list' src='/bridge/upload/"+data.userImg+"'/>";
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