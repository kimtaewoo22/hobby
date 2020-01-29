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
	Ŭ���� ��ȣ : ${hobbyClass.hobbyClassNo}
	<br/>
	Ŭ���� ���� : ${hobbyClass.hobbyClassName}
	<br/>
	ũ�������� �����ʻ��� : ${hobbyClass.user.profileImage}
	<br/>
	ũ�������� �г��� : ${hobbyClass.user.nickName}
	<br/>
	ũ�������� ����ó(���̵�) : ${hobbyClass.user.userId}
	<br/>
	Ŭ���� ī�װ� : ${hobbyClass.category} 
	<br/> 
	Ŭ���� �ؽ��±� : 
	<c:forEach items="${hobbyClass.hashtag}" var="item" varStatus="i">
		<c:out value="${item }" />
		<br/>
	</c:forEach>
	���� ������ �� : ${hobbyClass.hobbyClassPersonnel}
	<br/>
	Ŭ���� ���� : ${hobbyClass.hobbyClassPrice}
	<br/>
	Ŭ���� ���� ���� : ${hobbyClass.lessonTotalCount} 
	<br/>
	ũ�������� �Ұ� �̹��� + ũ�������� �Ұ� �� = Ŭ���� �Ұ�(summerNote) : ${hobbyClass.hobbyClassIntro}
	<br/>
	������ : ${hobbyClass.startDate}
	<br/>
	������ : ${hobbyClass.endDate}
	<br/><br/>

	<button type="button" value="10000">Ŭ���� �غ� �Ұ���</button>
</body>
</html>