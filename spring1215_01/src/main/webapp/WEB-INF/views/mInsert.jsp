<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원가입</title>
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
			   $("#idCheck").click(function(){
				   alert("아이디를 중복체크를 진행합니다.")
				   alert($("#id").val())
				   $.ajax({
					   url:"idCheck",
					   type:"post",
					   data:{"id":$("#id").val()},
					   dataType:"json",              //ajax에서 받는 파일형태
					   //contentType:"json",           //ajax에서 보내는 파일형태 @ResponseBody
					   success:function(data){
						   if(data.result=='fail'){
							   alert("아이디 존재 다시 입력");
							   $("#id").val("");
							   $("#id").focus();
							   $("#chkTxt").text("아이디 사용 불가능");
							   $("#chkTxt").css({"color":"red","font-weight":"900"});
							   return false;
						   }else{
							   alert("아이디 사용 가능함.");
							   $("#chkTxt").text("아이디 사용가능");
							   $("#chkTxt").css({"color":"blue","font-weight":"900"});
						   }
						   console.log("data.result : "+data.result);
					   },
					   error:function(){
						   alert("실패야~")
					   }
				   })
			   })
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
	   <h1>회원가입</h1>
	   <form name="m_frm" method="post" action="doMInsert">
		   <table>
		     <tr>
		       <th>아이디</th>
		       <td>
				   <input type="text" name="id" id="id" >
		           <button type="button" id="idCheck">아이디 확인</button>
		           <br>
		           <span id="chkTxt"></span>
		       </td>
		     </tr>
		     <tr>
		       <th>패스워드</th>
		       <td><input type="password" name="pw" id="pw"></td>
		     </tr>
		     <tr>
		       <th>이름</th>
		       <td><input type="text" name="name" id="name" ></td>
		     </tr>
		     <tr>
		       <th>전화번호</th>
		       <td><input type="text" name="phone" id="phone" ></td>
		     </tr>
		     <tr>
		       <th>성별</th>
		       <td>
		         <input type="radio" name="gender" id="Male" value="male">
		         <label for="Male">남자</label>
		         <input type="radio" name="gender" id="female" value="female">
		         <label for="Female">여자</label>
		       </td>
		     </tr>
		     <tr>
		       <th>취미</th>
		       <td>
		         <input type="checkbox" name="hobby" id="game" value="game">
		         <label for="game">게임</label>
		         <input type="checkbox" name="hobby" id="golf" value="golf">
		         <label for="golf">골프</label>
		         <input type="checkbox" name="hobby" id="run" value="run">
		         <label for="run">조깅</label>
		         <input type="checkbox" name="hobby" id="cook" value="cook">
		         <label for="cook">요리</label>
		         <input type="checkbox" name="hobby" id="book" value="book">
		         <label for="book">독서</label>
		       </td>
		     </tr>
		   </table>
		   <button type="button" id="fbtn">저장</button>
		   <button type="button" onclick="javascript:location.href='/'">취소</button>
	   </form>
	  </div>
	
	</body>
</html>