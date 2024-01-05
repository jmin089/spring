<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>doLogin</title>
	</head>
	<body>
		<script>
			if(${result}==1){
				alert("로그인 성공하셨습니다.")
				location.href="/";
			}else{
				alert("로그인 실패하셨습니다. 아이디 또는 패스워드 다시 입력하세요.")
				location.href="login";
			}
		</script>
	</body>
</html>