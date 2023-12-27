/**
 * 
 */
$(function(){
    		$(".btn_login").click(function(){
    			if($("#id").val().length < 1){ //1글자 이상
    				alert("아이디를 입력해~")
    				$("#id").focus()
    				return false;
    			}
    			LoginFrm.submit();
    		})
    		//$(".btn_login").click(()=>{
    			//alert("테스트")
    		//}) - 브라우저 버전에 6??부터는 이렇게 지원하는데 아직 많이 사용안함.
    	})