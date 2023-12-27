<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<title>뷰페이지</title>
	<link
		href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,500,700,900&display=swap&subset=korean"
		rel="stylesheet">
	<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/read.css">
</head>
<body>
	<section>
		<h1>NOTICE</h1>
		<form action="" id="bvFrm" name="bvFrm" method="post">
		<input type="hidden" name="bno" value="${map.bdto.bno}">
			<table>
				<colgroup>
					<col width="10%">
					<col width="55%">
					<col width="15%">
					<col width="20%">
				</colgroup>
				<tr>
					<th><strong>게시글 번호</strong></th>
					<th colspan="3">${map.bdto.bno}</th>
				</tr>
				<tr>
					<th><strong>게시글 제목</strong></th>
					<th colspan="3">${map.bdto.btitle}</th>
				</tr>
				<tr>
					<td><strong>작성자</strong></td>
					<td>${map.bdto.id}</td>
					<td><strong>조회수</strong></td>
					<td>${map.bdto.bhit}</td>
				</tr>
				<tr>
					<td colspan="4" class="article">${map.bdto.bcontent}</td>
				</tr>
				<tr>
					<td><strong>파일이름</strong></td>
					<td colspan="3">${map.bdto.bfile}</td>
				</tr>
				<tr>
					<td><strong>이미지</strong></td>
					<td colspan="3">
					<c:if test="${map.bdto.bfile != null }">
						<img src="/upload/${map.bdto.bfile}">
					</c:if>
					<c:if test="${map.bdto.bfile == null }">
						파일첨부가 없습니다.
					</c:if>
					</td>
				</tr>
				<tr>
					<td><strong>다음글 제목</strong></td>
					<td colspan="3">
					<c:if test="${map.nextDto != null}">
						<a href="bView?bno=${map.nextDto.bno}">${map.nextDto.btitle}</a>
					</c:if>
					<c:if test="${map.nextDto == null}">
						다음 게시글이 없습니다.
					</c:if>
					</td>
				</tr>
				<tr>
					<td><strong>이전글 제목</strong></td>
					<td colspan="3">
					<c:if test="${map.prevDto != null}">
					<a href="bView?bno=${map.prevDto.bno}">${map.prevDto.btitle}</a>
					</c:if>
					<c:if test="${map.prevDto == null}">
					이전 게시글이 없습니다.
					</c:if>
					</td>
				</tr>
			</table>
			<script>
				$(function(){
					$(".UBtn").click(function(){
						alert("게시글 수정페이지로 이동합니다.")
						$("#bvFrm").attr("action","bUpdate").submit();
					})
					$(".DBtn").click(function(){
						if(confirm("게시글을 삭제하시겠습니까??")){
						$("#bvFrm").attr("action","bDelete").submit();
						}
					})
					$(".RBtn").click(function(){
						alert("게시글 답글페이지로 이동합니다.")
						$("#bvFrm").attr("action","bReply").submit();
					})
				})
			</script>
			<a href="bList"><div class="list">목록</div></a>
			<c:if test="${session_id == map.bdto.id}">
			<div class="list DBtn" style="cursor: pointer;">삭제</div>
			<div class="list UBtn" style="cursor: pointer;">수정</div>
			</c:if>
			<c:if test="${session_id != null}">
			<div class="list RBtn" style="cursor: pointer;">답변</div>
			</c:if>
		</form>
	</section>
</body>
</html>