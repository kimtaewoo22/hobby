<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	
	<script type="text/javascript">
		var currentPage = 1;
		var maxPage = "${resultPage.maxPage}"
		$(function(){
			$('button').on('click', function(){
				var check = $(this).val();
				$.ajax(
						{
							url : "/searchHobbyClass/json/getPopularHobbyClassList", 
							method : "post", 
							dataType : "json", 
							headers : {
								"Accept" : "application/json", 
								"Content-Type" : "application/json"
							}, 
							data : JSON.stringify({
								currentPage : currentPage, 
								maxPage : maxPage,
								check : check 
							}),
							success : function(JSONData, status) {
								currentPage = JSONData.currentPage; 
								$('div').text('현재 ' + currentPage + ' 페이지');
								$('span').empty();
								
								var display = '';
								for(var i = 0; i < JSONData.list.length; i++) {
									display += JSONData.list[i].hobbyClassNo + ' / ' + JSONData.list[i].hobbyClassName + ' / ' + JSONData.list[i].steamCount + ' / ' + JSONData.list[i].steamCheck 
									display += '<br/><br/><br/>';
								}
								
								$('span').append(display);
							}
						}
				)
			})
		})
	</script>
	
</head>
<body>
	총 ${resultPage.totalCount } 개  
	<br/>
	최대 ${resultPage.maxPage } 페이지  
	<div>현재 1 페이지 </div>
	<br/><br/><br/>
	<span>
		<c:forEach var="list" items="${list }">
			${list.hobbyClassNo } / ${list.hobbyClassName } / ${list.steamCount }
			<br/><br/><br/>
		</c:forEach>
	</span>
	
	
	<button type="button" name="beforePage" value="0">이전페이지버튼</button>
	<button type="button" name="afterPage" value="1">다음페이지버튼</button>
</body>
</html>