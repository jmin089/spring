<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
		<title>차트-chart</title>
		<script>
			$(function(){
				$("#cbtn").click(function(){
					alert("차트데이트를 가져옵니다.");
					alert($("#cyear").val());
					let cyear = $("#cyear").val();
					
					// Chart객체 리셋해야 다시 차트를 그릴수 있슴.
					 let chartStatus = Chart.getChart("myChart"); // <canvas> id
					 if (chartStatus != undefined) {
					 	chartStatus.destroy();
					 	console.log("mhChart리셋");
					 }
					
					let chartLabels = [];
					let chartData = [];
					
					//db연결 - 데이터를 가져옴.
					//ajax 시작.
					$.ajax({
						url:"/layout/incomeSelect",
						type:"post",
						data:{"cyear":cyear},
						dataType:"json",
						success:function(data){
							alert("성공");
							console.log(data);
							console.log("개수 : "+data.length);
							console.log("6월 : "+data[5].cmonth);
							console.log("6월 수입 : "+data[5].csale);
							
							//for문
							for(let i=0;i<data.length;i++){
								chartLabels.push(data[i].cmonth);
								chartData.push(data[i].csale);
							}
							/*	단순 for문
							for(d:data){
								chartLabels.push(d.cmonth);
								chartData.push(d.csale);
							}	*/
							
							console.log(chartLabels);
							console.log(chartData);
							
							//차트 만들기
							 const ctx = document.getElementById('myChart');
							 //const ctx = $("#myChart");
			
							  new Chart(ctx, {
							    type: 'bar',
							    data: {
							      labels: chartLabels,
							      datasets: [{
							        label: cyear+'매출액',  //select 박스의 값이 넣어옴.
							        data: chartData,
							        backgroundColor: [
							            'rgba(255, 99, 132, 0.2)',
							            'rgba(255, 159, 64, 0.2)',
							            'rgba(255, 205, 86, 0.2)',
							            'rgba(75, 192, 192, 0.2)',
							            'rgba(54, 162, 235, 0.2)',
							            'rgba(153, 102, 255, 0.2)',
							            'rgba(201, 203, 207, 0.2)'
							          ],
							          borderColor: [
							            'rgb(255, 99, 132)',
							            'rgb(255, 159, 64)',
							            'rgb(255, 205, 86)',
							            'rgb(75, 192, 192)',
							            'rgb(54, 162, 235)',
							            'rgb(153, 102, 255)',
							            'rgb(201, 203, 207)'
							          ],
							        borderWidth: 1
							      }]
							    },
							    options: {
							      scales: {
							        y: {
							          beginAtZero: true
							        }
							      }
							    }
							  }); //차트 만들기
							
						},
						error:function(){
							alert("실패")
						}
					})//ajax 끝.
					
					
					})
				})//jquery
		</script>
		<style>
			.area{width: 50%; height:400px; margin: 20px auto; border: 3px solid black;}
		</style>
	</head>
	<body>
		<select id="cyear">
			<option value="2022년">2022년</option>
			<option value="2023년">2023년</option>
		</select>
		<button id="cbtn">차트 데이터 가져오기</button>
		<br>
		<div class="area">
 			<canvas id="myChart"></canvas>
		</div>
	</body>
</html>