<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<style>
a {text-decoration: none;}
table, th, td {border: 1px solid black; border-collapse: collapse;}
h2 {text-align: center;}
table {
	width: 1400px;
	margin: 0 auto;
	text-align: center;
}
th, td {height: 40px;}
tr td:nth-of-type(2) {
	text-align: left;
	padding-left: 40px;
	box-sizing: border-box;
}
div {
	width: 250px;
	height: 60px;
	margin: 12px auto 0;
}
button {
	display: inline-block;
	width: 120px;
	height: 40px;
}
.img {
	width: 10px;
}

.material-symbols-outlined {
	font-variation-settings: 'FILL' 0, 'wght' 200, 'GRAD' 0, 'opsz' 22
}

.searchDiv {
	width: 470px;
	height: 40px;
	margin: 50px auto 20px;
}

.searchDiv select {
	width: 80px;
	height: 40px;
	font-size: 15px;
}

.searchDiv input {
	width: 250px;
	height: 35px;
	font-size: 15px;
}

.numbering {
	width: 460px;
	height: 40px;
	margin: 20px auto 40px;
	text-align: center;
}

.numbering span {
	width: 40px;
	height: 40px;
	display: inline-block;
	border: 1px solid black;
	box-sizing: border-box;
	text-align: center;
	font-size: 15px;
	padding-top: 9px;
}

#on {
	background: #e56e00;
	color: #fff;
}
</style>
<script>
		  function searchBtn(){
			  if($("#searchWord").val().length<1){
				  alert("검색어를 입력하셔야 검색이 가능합니다.");
				  $("#searchWord").focus();
				  return;
			  }
			  searchFrm.submit();
		  }
		</script>
</head>
<body>
	<h2>자유게시판</h2>
	<div class="searchDiv">
		<form action="doBoardSearch.do" method="get" name="searchFrm">
			<select name="searchTitle" id="searchTitle">
				<option value="all">전체</option>
				<option value="btitle">제목</option>
				<option value="bcontent">내용</option>
				<option value="id">작성자</option>
			</select> <input type="text" name="searchWord" id="searchWord">
			<button type="button" onclick="searchBtn()">검색</button>
		</form>
	</div>
	<table>
		<colgroup>
			<col width="10%">
			<col width="10%">
			<col width="10%">
			<col width="10%">
			<col width="10%">
			<col width="10%">
			<col width="10%">
			<col width="10%">
			<col width="10%">
			<col width="10%">
		</colgroup>
		<tr>
			<th>사원번호</th>
			<th>이름</th>
			<th>이메일</th>
			<th>전화번호</th>
			<th>입사일</th>
			<th>월급</th>
			<th>관리자번호</th>
			<th>커미션</th>
			<th>퇴사일</th>
			<th>직급</th>
		</tr>
		<c:forEach var="edto" items="#{list}">
		<tr>
			<td>${edto.employee_id}</td>
			<td>${edto.emp_name}</td>
			<td>${edto.email}</td>
			<td>${edto.phone_number}</td>
			<td>${edto.hire_date}</td>
			<td>${edto.salary}</td>
			<td>${edto.manager_id}</td>
			<td>${edto.commission_pct}</td>
			<td>${edto.retire_date}</td>
			<td>${edto.department_id}</td>
		</tr>
			</c:forEach>

	</table>
	<div class="numbering">
		<!-- 첫페이지 이동  -->
		<span>&#171;</span>
		<!-- 이전페이지 이동 -->
		<span>&#60;</span>
		<!-- 하단넘버링부분 -->
		<span id="on">1</span>
		<!-- 다음페이지 이동 -->
		<span>&#62;</span>
		<!-- 끝페이지 이동 -->
		<span>&#187;</span>
	</div>

	<div>
		<a href=""><button type="button">글쓰기</button></a> 
		<a href=""><button type="button">메인페이지</button></a>
	</div>
</body>
</html>