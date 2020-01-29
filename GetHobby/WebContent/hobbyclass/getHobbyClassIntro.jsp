<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<title>Insert title here</title>

	<script type="text/javascript">
		$(function(){
			$('button').on('click', function(){
				self.location = '/searchHobbyClass/getSearchHobbyClassKit?hobbyClassNo=' + $(this).val();
			})
		})
	</script>
	
</head>
<body>
	클래스 번호 : ${hobbyClass.hobbyClassNo}
	<br/>
	클래스 제목 : ${hobbyClass.hobbyClassName}
	<br/>
	크리에이터 프로필사진 : ${hobbyClass.user.profileImage}
	<br/>
	크리에이터 닉네임 : ${hobbyClass.user.nickName}
	<br/>
	크리에이터 연락처(아이디) : ${hobbyClass.user.userId}
	<br/>
	클래스 카테고리 : ${hobbyClass.category} 
	<br/> 
	클래스 해쉬태그 : 
	<c:forEach items="${hobbyClass.hashtag}" var="item" varStatus="i">
		<c:out value="${item }" />
		<br/>
	</c:forEach>
	현재 구매자 수 : ${hobbyClass.hobbyClassPersonnel}
	<br/>
	클래스 가격 : ${hobbyClass.hobbyClassPrice}
	<br/>
	클래스 강의 개수 : ${hobbyClass.lessonTotalCount} 
	<br/>
	크리에이터 소개 이미지 + 크리에이터 소개 글 = 클래스 소개(summerNote) : ${hobbyClass.hobbyClassIntro}
	<br/>
	시작일 : ${hobbyClass.startDate}
	<br/>
	종료일 : ${hobbyClass.endDate}
	<br/><br/>

	<button type="button" value="10000">클래스 준비물 소개로</button>
</body>
</html>