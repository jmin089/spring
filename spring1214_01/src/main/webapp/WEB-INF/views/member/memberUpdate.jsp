<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원정보수정</title>
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<style>
		   *{margin:0; padding:0;}
		   div{width:600px; margin:30px auto; text-align: center;}
		   h1{margin-bottom:30px; }
		   table,th,td{border:1px solid black; border-collapse: collapse;
		   font-size: 16px; }
		   th{width:200px; height:40px; }
		   td{width:400px; }
		   button{width:200px; height:60px; margin-top:30px; }
		   td * { vertical-align: middle; }
		   #idCheck{ width:100px; height:20px; margin-top:0; }
		</style>
		<script>
		   $(function(){
			  $("#fbtn").click(function(){
				 if($("#id").val().length<2){
					 alert("아이디를 입력하셔야 합니다.");
					 $("#id").focus();
					 return false;
				 }
				  
				 alert("회원정보를 저장합니다.");  
				 m_frm.submit();
			  });
		   });
		</script>
	</head>
	<body>
	  <div>
	   <h1>회원정보수정</h1>
	   <form name="m_frm" method="post" action="doMInsert">
		   <table>
		     <tr>
		       <th>아이디</th>
		       <td>
				   <input type="text" name="id" id="id" value="${memberDto.id}">
		           <button type="button" id="idCheck">아이디 확인</button>
		       </td>
		     </tr>
		     <tr>
		       <th>패스워드</th>
		       <td><input type="password" name="pw" id="pw" value="${memberDto.pw}"></td>
		     </tr>
		     <tr>
		       <th>이름</th>
		       <td><input type="text" name="name" id="name" value="${memberDto.name}"></td>
		     </tr>
		     <tr>
		       <th>전화번호</th>
		       <td><input type="text" name="phone" id="phone" value="${memberDto.phone}"></td>
		     </tr>
		     <tr>
		       <th>성별</th>
		       <td>
		         <input type="radio" name="gender" id="Male" value="male"
		         <c:if test="${fn:contains(memberDto.gender,'male')}">checked</c:if>
		         >
		         <label for="Male">남자</label>
		         <input type="radio" name="gender" id="female" value="female"
		         <c:if test="${fn:contains(memberDto.gender,'female')}">checked</c:if>
		         >
		         <label for="Female">여자</label>
		       </td>
		     </tr>
		     <tr>
		       <th>취미</th>
		       <td>
		         <input type="checkbox" name="hobby" id="game" value="game"
		         <c:if test="${fn:contains(memberDto.hobby,'game')}">checked</c:if>
		         >
		         <label for="game">게임</label>
		         <input type="checkbox" name="hobby" id="golf" value="golf"
		         <c:if test="${fn:contains(memberDto.hobby,'golf')}">checked</c:if>
		         >
		         <label for="golf">골프</label>
		         <input type="checkbox" name="hobby" id="run" value="run"
		         <c:if test="${fn:contains(memberDto.hobby,'run')}">checked</c:if>
		         >
		         <label for="run">조깅</label>
		         <input type="checkbox" name="hobby" id="cook" value="cook"
		         <c:if test="${fn:contains(memberDto.hobby,'cook')}">checked</c:if>
		         >
		         <label for="cook">요리</label>
		         <input type="checkbox" name="hobby" id="book" value="book"
		         <c:if test="${fn:contains(memberDto.hobby,'book')}">checked</c:if>
		         >
		         <label for="book">독서</label>
		       </td>
		     </tr>
		   </table>
		   <button type="button" id="fbtn">회원정보수정</button>
		   <button type="button" onclick="javascript:location.href='/'">취소</button>
	   </form>
	  </div>
	
	</body>
</html>