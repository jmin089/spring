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
  <link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,500,700,900&display=swap&subset=korean" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
  <link rel="stylesheet" href="../css/style.css">
  <link rel="stylesheet" href="../css/read.css">
</head>
<body>
<section>
    <h1>NOTICE</h1>
    <form action="bUpdate" name="bFrm" method="post">
    <table>
      <colgroup>
        <col width="10%">
        <col width="55%">
        <col width="15%">
        <col width="20%">
      </colgroup>
      <tr>
      	<th><strong>글번호</strong></th>
        <th colspan="3">${bdto.bno}</th>
      </tr>
      <tr>
     	<td><strong>글제목</strong></td>
        <td colspan="3">${bdto.btitle}</td>
      </tr>
      <tr>
        <td><strong>작성자</strong></td>
        <td>${bdto.id}</td>
        <td><strong>조회수</strong></td>
        <td>${bdto.bhit}</td>
      </tr>
      <tr>
        <td colspan="4" class="article">${bdto.bcontent}</td>
      </tr>
      <tr>
      	<td><strong>파일이름</strong></td>
        <td colspan="3">${bdto.bfile}</td>
      </tr>
      <tr>
      	<td><strong>이미지</strong></td>
        <td colspan="3"><img src="/upload/${bdto.bfile}"></td>
      </tr>
      <tr>
      	<td><strong>다음글</strong></td>
        <td colspan="4"> [키즈잼] 2월 프로그램 안내</td>
      </tr>
      <tr>
        <td><strong>이전글</strong></td>
        <td colspan="4"> [키즈잼] 2020년 1분기 정기 휴관일 안내</td>
      </tr>
    </table>
    <script>
    	$(function(){
    		$("#bupdateBtn").click(function(){
    			alert("수정페이지로 이동합니다.");
    			bFrm.submit();
    		})
    	})
    </script>
    <a href=""><div class="list">목록</div></a>
    <a href=""><div class="list">삭제</div></a>
    <div class="list" id="bupdateBtn">수정</div>
    <a href=""><div class="list">답변달기</div></a>
  </form>
  </section>
</body>
</html>