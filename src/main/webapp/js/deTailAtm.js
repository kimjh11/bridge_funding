$(function(){
	$("#ok").css({"background":"#92D1E0",
				  "height":"10px",
				  "margin-bottom":"0px"})
	
	if($("#goalRate").text()>=100){			  
		$("#ok").css("width","100%");
	}else{
		$("#ok").css("width",$("#goalRate").text());
	}
	$(".btns").hover(function(){
			$(this).css("background","#eee")
		},
		function(){
			$(this).css("background","#fff")
		});
	$("#punding").hover(function(){
			$(this).css("background","#81b9c6")
		},
		function(){
			$(this).css("background","#92D1E0")
		})
		
	if($("#chk").val() == ""){
		$("#like").click(function(){
			if($("#logStatus").val() == "Y"){
				$("#like img").toggle();
				$("#like").toggleClass("action");
				if($("#like").attr("Class") == "btns action"){
					$.ajax({
						type : "POST",
						url : "likeUp",
						error : function(){
							alert("실패");
						},
						success : function(){
							var like= $("#like div").text();
							like = Number(like);
							$("#num").html(like+1);			
						}
					})
				}else{
					$.ajax({
						type : "POST",
						url : "likeDown",
						error : function(){
							alert("실패");
						},
						success : function(data){
							var like= $("#like div").text();
							like = Number(like);
							$("#num").html(like-1);			
						}
					})
				}
			}else{
				alert("로그인 후 가능합니다.");
			}
		})
	}else{
		$("#bin").css("display","none");
		$("#full").css("display","inline");
		$("#like").addClass("action");
		$("#like").click(function(){
			$("#like img").toggle();
			$("#like").toggleClass("action");
			if($("#like").attr("Class") == "btns action"){
				$.ajax({
					type : "POST",
					url : "likeUp",
					error : function(){
						alert("실패");
					},
					success : function(){
						var like= $("#like div").text();
						like = Number(like);
						$("#num").html(like+1);			
					}
				})
			}else{
				$.ajax({
					type : "POST",
					url : "likeDown",
					error : function(){
						alert("실패");
					},
					success : function(data){
						var like= $("#like div").text();
						like = Number(like);
						$("#num").html(like-1);			
					}
				})
			}
		})
	}
	$("#punding").click(function(){
		if($("#logStatus").val() == "Y"){
			location.href="/bridge/payment?proCode="+$("#proCode").val()+"&cateCode="+$("#cateCode").val();		
		}else{
			alert("로그인 후 가능합니다.");
		}		
	})
})