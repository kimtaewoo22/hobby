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
	Ŭ���� ��ȣ : ${hobbyClass.hobbyClassNo}
	<br/>
	�غ� �̹��� : ${hobbyClass.kitImage} 
	<br/>
	�غ� �̸� : ${hobbyClass.kitName}
	<br/>
	�غ� ���� : ${hobbyClass.kitPrice}
	<br/>
	�غ� �Ұ� : ${hobbyClass.kitIntro}
	
	<button type="button" value="10000">Ŭ���� ������ �����</button>
</body>
</html>