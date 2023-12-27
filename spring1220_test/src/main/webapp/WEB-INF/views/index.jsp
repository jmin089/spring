<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>메인페이지</title>
	</head>
	<body>
		<h1>[ [ [ index-test ] ] ]</h1>
		<ul>
			<c:if test="${session_id==null}">
				<li><strong style="font_size:20px;">로그인을 해주세요.</strong></li>
				<a href="login"><li>로그인</li></a>
			</c:if>
			<c:if test="${session_id!=null}">
				<li><strong style="font_size:20px;">${session_name}님 환영합니다.</strong></li>
				<a href="logout"><li>로그아웃</li></a>
			</c:if>
			<a href="mInsert"><li>회원가입</li></a>
			<br>
			<br>
			<a href="bList"><li>공지사항리스트(list)</li></a>
			<br>
			<br>
			<a href="bList2"><li>다중업로드 리스트</li></a>
			<a href="bInsert2"><li>다중업로드</li></a>
		</ul>
	</body>
</html>