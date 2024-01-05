<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Main</title>
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script type="text/javascript">
			$(function(){
				$("#ebtn").click(()=>{
					//alert("이메일 전송합니다.");
					let name = $("#name").val();
					let email = $("#email").val();
					//alert(name);
					//alert($("#email").val());
					//ajax 시작.
					$.ajax({
						url:"email",
						type:"post",
						data:{"name":name, "email":email},
						dataType:"text",
						success:function(data){
							alert("이메일이 발송되었습니다.");
			                console.log(data);
						},
						error:function(){
							alert("실패");
						}
					});//ajax 끝.
				});//ebtn
				
				$("#cbtn").click(()=>{
					//alert("인증번호를 확인합니다.");
					let check = $("#check").val();
					//alert(check);
					$.ajax({
						url:"emailCheck",
						type:"post",
						data:{"check":check},
						dataType:"text",
						success:function(data){
							alert("인증번호 확인합니다.");
							if(data=="success"){
								alert("인증이 완료되었습니다.");
								location.href="#";  //step02로 보내기 지금은 암것도 없음.
							}else{
								alert("인증가 불일치! 인증번호는 다시 확인하세요.");
							}
			                console.log(data);
						},
						error:function(){
							alert("실패");
						}
					});//ajax 끝.
				})//cbtn
			});//jquery
		</script>
	</head>
	<body>
		<h1>메인페이지</h1>
		<div>
			<label>이름</label>
			<input type="text" name="name" id="name">
			<br>
			<label>이메일</label>
			<input type="text" name="email" id="email">
			<button type="button" id="ebtn">이메일전송</button>
			<br>
			<hr>
			<label>인증번호입력</label>
			<input type="text" name="check" id="check">
			<button type="button" id="cbtn">인증번호확인</button>
		</div>
		<table>
		<tr>
			<table align='center' style='margin:0 0 0 40px;border:1px #D9D9D9 solid;'>
			<tr>
				<td style='width:618px;height:220px;padding:0;margin:0;vertical-align:top;font-size:0;line-height:0;background:#F9F9F9;'>
					<p style='width:620px;margin:30px 0 0 0;padding:0;text-align:center;'><img src='https://cfm.kt.com/images/v2/layout/gnb-ktlogo.png' alt='비밀번호 찾기를 요청하셨습니다.'/></p>
					<p style='width:620px;margin:10px 0 0 0;padding:0;text-align:center;color:#888888;font-size:12px;line-height:1;'>아래의 인증코드는 회원가입에 필요한 인증코드입니다.</p>
					<p style='width:620px;margin:28px 0 0 0;padding:0;text-align:center;color:#666666;font-size:12px;line-height:1;'><strong>회원가입 인증코드 : <span style='color:#F7703C;line-height:1;'>dasdw2341</span></strong></p>
					<p style='width:620px;margin:30px 0 0 0;padding:0;text-align:center;color:#888888;font-size:12px;line-height:1.4;'>회원가입에 필요한 인증코드를 발송해 드렸습니다.<br/>인증코드를 인증번호 입력에 넣고 인증번호 확인 버튼을 클릭해주세요.</p>
				</td>
			</tr>
			</table>	
		</tr>
		</table>
	</body>
</html>