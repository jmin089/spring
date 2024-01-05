
$(function(){
	screen_data();
});//jquery


//ajax으로 영화정보 가져오기 함수
function screen_data(){
	alert("영화정보와 다음지도를 가지고 옵니다.");
	$.ajax({
		url:"../screenInfo",
		type:"post",
		data:{"movie":"1"},
		dataType:"json",
		success:function(data){
			let m_data = data.boxOfficeResult.dailyBoxOfficeList;
			console.log(""+m_data[0].movieNm);
			
			//---------------------------------------------------------다음주소 api
			
			var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
			    mapOption = { 
			        center: new kakao.maps.LatLng(37.48522815220549, 126.89829875799366), // 지도의 중심좌표
			        level: 4 // 지도의 확대 레벨
			    };
			
			var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
			
			// 커스텀 오버레이에 표시할 내용입니다     
			// HTML 문자열 또는 Dom Element 입니다 
			var content = '<div class="overlaybox">' +
			    '    <div class="boxtitle">금주 영화순위</div>' +
			    '    <div class="first">' +
			    '        <div class="triangle text">1</div>' +
			    '        <div class="movietitle text">'+m_data[0].movieNm+'</div>' +
			    '    </div>' +
			    '    <ul>' +
			    '        <li class="up">' +
			    '            <span class="number">2</span>' +
			    '            <span class="title">'+m_data[1].movieNm+'</span>' +
			    '            <span class="arrow up"></span>' +
			    '            <span class="count">'+Math.abs(m_data[1].rankInten)+'</span>' +
			    '        </li>' +
			    '        <li>' +
			    '            <span class="number">3</span>' +
			    '            <span class="title">'+m_data[2].movieNm+'</span>' +
			    '            <span class="arrow "></span>' +
			    '            <span class="count">'+Math.abs(m_data[2].rankInten)+'</span>' +
			    '        </li>' +
			    '        <li>' +
			    '            <span class="number">4</span>' +
			    '            <span class="title">'+m_data[3].movieNm+'</span>' +
			    '            <span class="arrow up"></span>' +
			    '            <span class="count">'+Math.abs(m_data[3].rankInten)+'</span>' +
			    '        </li>' +
			    '        <li>' +
			    '            <span class="number">5</span>' +
			    '            <span class="title">'+m_data[4].movieNm+'</span>' +
			    '            <span class="arrow down"></span>' +
			    '            <span class="count">'+Math.abs(m_data[4].rankInten)+'</span>' +
			    '        </li>' +
			    '    </ul>' +
			    '</div>';
			
			// 커스텀 오버레이가 표시될 위치입니다 
			var position = new kakao.maps.LatLng(37.48522815220549, 126.89829875799366);  
			
			// 커스텀 오버레이를 생성합니다
			var customOverlay = new kakao.maps.CustomOverlay({
			    position: position,
			    content: content,
			    xAnchor: 0.3,
			    yAnchor: 0.91
			});
			
			// 커스텀 오버레이를 지도에 표시합니다
			customOverlay.setMap(map);
			
			//---------------------------------------------------------다음주소 api
			
		},
		error:function(){
			alert("실패");
		}
	});//ajax 끝.
}