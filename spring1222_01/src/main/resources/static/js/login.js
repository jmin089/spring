/**
 * 
 */
$(function(){
	$("#saveBtn").click(function(){
		//alert("테스트");
		alert("아이디 : "+$("#id").val());
		if($("#id").val().length < 1){
			alert("아이디를 입력하셔야 다음진행 가능함.")
			$("#id").focus();
			return false;
		}
		loginFrm.submit();
	})
})
