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
		<h1>[ [ [ index-122101 ] ] ]</h1>
		<ul>
			<c:if test="${session_id==null}">
				<li><strong style="font_size:20px;">로그인을 해주세요.</strong></li>
				<a href="member/login"><li>로그인</li></a>
			</c:if>
			<c:if test="${session_id!=null}">
				<li><strong style="font_size:20px;">${session_name}님 환영합니다.</strong></li>
				<a href="member/logout"><li>로그아웃</li></a>
			</c:if>
			<a href="member/mInsert"><li>회원가입</li></a>
			<br>
			<a href="boardBno/7/10"><li>글번호</li></a>
			<br>
			<a href="list"><li>사원리스트(list)</li></a>
			<a href="list2"><li>사원부서리스트(list2)</li></a>
			<a href="list3"><li>회원,게시글리스트(list3)</li></a>
			<a href="board/bInsert"><li>공지사항글쓰기(insert)</li></a>
			<a href="bView"><li>공지사항보기(view)</li></a>
			<br>
			<br>
			<a href="board/bList2"><li>다중업로드 리스트</li></a>
			<a href="board/bInsert2"><li>다중업로드</li></a>
		</ul>
	</body>
</html>