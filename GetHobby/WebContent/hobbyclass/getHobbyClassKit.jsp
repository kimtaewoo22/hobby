<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<title>Insert title here</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	
	<script type="text/javascript">
		$(function(){
			$('button').on('click', function(){
				self.location = '/searchHobbyClass/getHobbyClassAssessContent?hobbyClassNo=' + $(this).val();
			})
		})
	</script>
	
</head>
<body>
	클래스 번호 : ${hobbyClass.hobbyClassNo}
	<br/>
	준비물 이미지 : ${hobbyClass.kitImage} 
	<br/>
	준비물 이름 : ${hobbyClass.kitName}
	<br/>
	준비물 가격 : ${hobbyClass.kitPrice}
	<br/>
	준비물 소개 : ${hobbyClass.kitIntro}
	
	<button type="button" value="10000">클래스 한줄평 보기로</button>
</body>
</html>