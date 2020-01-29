<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<title>Insert title here</title>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	
	<script type="text/javascript">
		var currentPage = 2;
		var maxPage = " ${resultPage.maxPage}";
		$(function(){
			$('button').on('click', function(){
				var hobbyClassNo = $('button').val();
				
				if ( maxPage >= currentPage ) {
					$.ajax(
							{
								url : "/searchHobbyClass/json/getHobbyClassAssessContent",
								method : "post", 
								dataType : "json", 
								headers : {
									"Accept" : "application/json",
									"Content-Type" : "application/json"
								}, 
								data : JSON.stringify({
									hobbyClassNo : hobbyClassNo, 
									currentPage : currentPage 
								}), 
								success : function(JSONData, status) {
									currentPage += 1;
									
									var display = "";
									for (var i = 0; i < JSONData.listAssessContent.length; i++){
										display += JSONData.listAssessContent[i].assessNo + " / " + JSONData.listAssessContent[i].hobbyClass.hobbyClassNo + " / " + JSONData.listAssessContent[i].user.userId + " / " + JSONData.listAssessContent[i].assessContent + " / " + JSONData.listAssessContent[i].assessStar + " / " + JSONData.listAssessContent[i].regDate + "<br/><br/><br/>";
									}
									
									display += "<br/><br/><span></span>";
									$("span:last").append(display);
								}
							}
					)
				}
			})
			
			
		})
	</script>
</head>
<body>
	총 ${resultPage.totalCount} / 페이지 ${resultPage.maxPage}
	<br/><br/><br/>
	<c:forEach var="classAssess" items="${listAssessContent}">
		${classAssess.assessNo} / ${classAssess.hobbyClass.hobbyClassNo} / ${classAssess.user.userId} / ${classAssess.assessContent} / ${classAssess.assessStar} / ${classAssess.regDate} 
		<br/><br/><br/>
	</c:forEach>
	
	<span></span>
	
	<button type="button" value="10000">다음 페이지 클래스 한줄평 보기</button>
</body>
</html>