<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	    
		<meta charset="UTF-8">
		<title>게시판</title>
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
		<style>
		   *{margin:0; padding:0;}
		   a{text-decoration: none; color:black; }
		   div{width:1000px; margin:30px auto; text-align: center;}
		   h1{margin-bottom:10px; }
		   table,th,td{border:1px solid black; border-collapse: collapse;
		   font-size: 16px; }
		   th,td{width:200px; height:40px; }
		   button{width:200px; height:60px; margin-top:10px; }
		   #n{ width:700px; height:30px; margin:40px auto; }
		   span{ display:inline-block; line-height:30px;
		   width:30px; height:30px; border:1px solid black; text-align: center;}
		   span:hover{background:#b06482; color:white; font-weight: 700; }
		   
		   .wrapper{width:700px; height: 70px; margin-top:10px; }
		   #category{width:80px; height:40px; border:1px solid #666;
		   padding:5px; font-size:16px;  }
		   .title{width:200px; height:40px;  display: inline-block;}
		   #stitle{width:200px; height:38px; border:1px solid #666; 
		           font-size:16px; }
		   #sbtn{width:40px; height:40px; background:#666; }
		   .fas{font-weight: 900; color:white; }
		</style>
	</head>
	<body>
		<div>
		   <h1>게시판</h1>
		   <!-- 검색부분 -->
		   <div class="wrapper">
		      <form action="/search" name="search" method="post">
		        <select name="category" id="category">
		          <option value="0">전체</option>
		          <option value="title">제목</option>
		          <option value="content">내용</option>
		        </select>
		
		        <div class="title">
		          <input type="text" size="16" id="stitle">
		        </div>
		  
		        <button type="button" id="sbtn" ><i class="fas fa-search"></i></button>
		      </form>
		    </div>
		   
		   <table>
		     <colgroup>
		       <col width="12%">
		       <col width="40%">
		       <col width="18%">
		       <col width="18%">
		       <col width="12%">
		     </colgroup>
		     <tr>
		       <th>번호</th>
		       <th>제목</th>
		       <th>작성자</th>
		       <th>작성일</th>
		       <th>조회수</th>
		     </tr>
			     <tr>
			       <td></td>
			       <td><a href=""></a></td>
			       <td></td>
			       <td></td>
			       <td></td>
			     </tr>
		     
		   </table>
		    <div id="n">
		      <a href="#"><span><i class="fa fa-angle-double-left" aria-hidden="true"></i></span></a>
		      <a href="#"><span><i class="fa fa-angle-left" aria-hidden="true"></i></span></a>
		      <a href="#"><span>1</span></a>
		      <a href="#"><span>2</span></a>
		      <a href="#"><span>3</span></a>
		      <a href="#"><span>4</span></a>
		      <a href="#"><span>5</span></a>
		      <a href="#"><span>6</span></a>
		      <a href="#"><span>7</span></a>
		      <a href="#"><span>8</span></a>
		      <a href="#"><span>9</span></a>
		      <a href="#"><span>10</span></a>
		      <a href="#"><span><i class="fa fa-angle-right" aria-hidden="true"></i></span></a>
		      <a href="#"><span><i class="fa fa-angle-double-right" aria-hidden="true"></i></span></a>
		   </div>
		   <a href=""><button type="button">글쓰기</button></a>
		   <a href=""><button type="button">홈으로</button></a>
		</div>
	
	</body>
</html>