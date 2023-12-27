/**
 * login 관련 loginChk
 */
$(function(){
	
	let chk_KeyUp = 0;
	
	//회원가입버튼 클릭
	$("#savebtn").click(function(){
		if(chk_KeyUp != 1){
			alert("아이디 중복확인하고 와!!!!!");
			return false;
		}
		//ajax
		$.ajax({
			url:"/member/mInsert",
			type:"post",
			//data:{"id":$("#id").val()},  //테이터를 개별적으로 보냄(각각)
			data:$("#memberFrm").serialize(),    //form데이터를 한번에 전달(보낼 데이터의 수가 많을때)
			dataType:"text",  //받는파일형태 - text,json,xml / contentType:"json"  내가 보내는 파일 형태
			success:function(data){
				alert("성공")
				console.log("mInsert.js data : "+data);
				if(data == "가입 완완완완완완완완"){  //MServiceImpl result 값이랑 동일해야함.
					 alert("가입 성공했어~")
					 location.href="/";
				}
			},
			error:function(){
				alert("실패");
			}
		})//ajax 끝.
	})
	
	//아이디 확인 버튼 클릭후 아이디가 수정되었는지 확인
	$("#id").keyup(function(){
		console.log("keyup 생성")
		$("#chkTxt").text("아이디 중복확인하고 가자!!!")
		$("#chkTxt").css({"color":"black", "font-weight":"700"})
		chk_KeyUp = 0;
	})
	
	$("#idCheckBtn").click(function(){
		//아이디 중복체크
		alert("아이디 중복 체크를 합니다.");
		console.log($("#id").val());
		
		if($("#id").val().length < 1){
			alert("아이디를 입력하셔야 체트 가능합니다.");
			$("#id").focus();
			return false;
		}
		//ajax
		$.ajax({
			url:"/member/idCheck",
			type:"post",
			data:{"id":$("#id").val()},  //테이터를 개별적으로 보냄(각각)
			//data:$("#memberFrm").serialize(),    //form데이터를 한번에 전달(보낼 데이터의 수가 많을때)
			dataType:"text",  //받는파일형태 - text,json,xml / contentType:"json"  내가 보내는 파일 형태
			success:function(data){
				if(data == "사용가능"){
					alert("아이디 사용가능가능가능입니다.");
					$("#chkTxt").text("아이디 사용가가능")
					$("#chkTxt").css({"color":"blue", "font-weight":"700"})
				}else{
					alert("아이디 사용불가능불가능불가능입니다.");
					$("#chkTxt").text("아이디 사용불불가능")
					$("#chkTxt").css({"color":"red", "font-weight":"700"})
				}
				console.log("mInsert.js data : "+data);
				chk_KeyUp = 1;
			},
			error:function(){
				alert("실패");
			}
		})//ajax 끝.
	})
})