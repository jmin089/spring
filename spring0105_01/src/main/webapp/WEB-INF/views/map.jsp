<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>map</title>
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=cb804fd736173d010451314bd3453633"></script>
		<script src="/js/daum_api.js"></script>
		<style>
			*{margin: 0; padding: 0;}
			#map{border: 3px solid black;}
			#header{width: 100%; height: 80px; text-align: center; margin-top: 50px;}
			.overlaybox {position:relative;width:360px;height:350px;background:url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/box_movie.png') no-repeat;padding:15px 10px;}
			.overlaybox div, ul {overflow:hidden;margin:0;padding:0;}
			.overlaybox li {list-style: none;}
			.overlaybox .boxtitle {color:#fff;font-size:16px;font-weight:bold;background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png') no-repeat right 120px center;margin-bottom:8px;}
			.overlaybox .first {position:relative;width:247px;height:136px;background: url('https://search.pstatic.net/common/?src=http%3A%2F%2Fimgnews.naver.net%2Fimage%2F5910%2F2023%2F09%2F08%2F0000001635_001_20230908105003233.jpg&type=sc960_832') no-repeat;margin-bottom:8px;}
			.first .text {color:#fff;font-weight:bold;}
			.first .triangle {position:absolute;width:48px;height:48px;top:0;left:0;background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/triangle.png') no-repeat; padding:6px;font-size:18px;}
			.first .movietitle {position:absolute;width:100%;bottom:0;background:rgba(0,0,0,0.4);padding:7px 15px;font-size:14px;}
			.overlaybox ul {width:247px;}
			.overlaybox li {position:relative;margin-bottom:2px;background:#2b2d36;padding:5px 10px;color:#aaabaf;line-height: 1;}
			.overlaybox li span {display:inline-block;}
			.overlaybox li .number {font-size:16px;font-weight:bold;}
			.overlaybox li .title {font-size:13px;}
			.overlaybox ul .arrow {position:absolute;margin-top:8px;right:25px;width:5px;height:3px;background:url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/updown.png') no-repeat;} 
			.overlaybox li .up {background-position:0 -40px;}
			.overlaybox li .down {background-position:0 -60px;}
			.overlaybox li .count {position:absolute;margin-top:5px;right:15px;font-size:10px;}
			.overlaybox li:hover {color:#fff;background:#d24545;}
			.overlaybox li:hover .up {background-position:0 0px;}
			.overlaybox li:hover .down {background-position:0 -20px;}  
		</style>
		<script>
			
		</script>
	</head>
	<body>
		<div id="header"><h1>다음지도 api</h1>
		<button id="daumMapBtn">영화 검색</button></div>
		<div id="map" style="width:100%;height:600px;"></div>
	</body>
</html>