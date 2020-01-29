<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<!-- <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script> -->
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	<script defer src="https://use.fontawesome.com/releases/v5.0.8/js/solid.js" integrity="sha384-+Ga2s7YBbhOD6nie0DzrZpJes+b2K1xkpKxTFFcx59QmVPaSA8c7pycsNaFwUK6l" crossorigin="anonymous"></script>
	<script defer src="https://use.fontawesome.com/releases/v5.0.8/js/fontawesome.js" integrity="sha384-7ox8Q2yzO/uWircfojVuCQOZl+ZZBg2D2J5nkpLqzH1HY0C1dHlTKIbpRz/LG23c" crossorigin="anonymous"></script>
	<title>Insert title here</title>
	
	<script type="text/javascript">
	var currentPage = 1;
	var maxPage;
		$(function(){
			
			$('#categoryCollapse').children().children().on('click', function(){
				
				var buttonText = $(this).text();
				var buttonValue = $(this).val();
				
				$('input[name="categoryValue"]').val(buttonValue);
				
				$('#categoryCollapse').collapse('hide');
				$('#hashtagCollapse').collapse('hide');
				
				$('#categoryCollapse').on('hidden.bs.collapse', function(){
					
					$('button[name="category"]').text(buttonText);
					
					console.log('buttoValue ? : ' + buttonValue);
					
					if( buttonValue == 'all' ) {
						console.log('category all')
						$('button[name="hashtag"]').text(buttonText);
						$('button[name="hashtag"]').attr('disabled', true);
						
						$('input[name="hashtagValue"]').val(buttonValue);
					}
					else {
						console.log('category else')
						$('button[name="hashtag"]').text('해쉬태그');
						$('button[name="hashtag"]').attr('disabled', false);
					}
				})
			})
			
			$('button[name="hashtag"]').on('click', function(){
				console.log('해쉬태그 버튼 클릭');
				var categoryCode = $('input[name="categoryValue"]').val();
				console.log('categoryCode ? : ' + categoryCode)
				
				if( categoryCode != '' && categoryCode != 'all'  ) {
					$.ajax(
							{
								url : "/searchHobbyClass/json/getSearchHashtag",  
								method : "post", 
								dataType : "json", 
								headers : {
									"Accept" : "application/json", 
									"Content-Type" : "application/json" 
								}, 
								data : JSON.stringify({
									categoryCode : categoryCode 
								}), 
								success : function(JSONData, status) {
									for(var i = 0; i < 10; i++) {
										$("button[name='hashtagButton']").eq(i + 1).text(JSONData.hashtagNameList[i]);
										$("button[name='hashtagButton']").eq(i + 1).val(JSONData.hashtagCodeList[i]);
									}
								}
							}
					);
				}
				var categoryValue = $('input[name="categoryValue"]').val();
				
				if ( categoryValue == "" ) {
					$('#hashtagCollapse').css('display', 'none');
					$('#hashtagCollapse').collapse('hide');
					
					$('#categoryCollapse').collapse('show');
				}
				else {
					$('#hashtagCollapse').css('display', '');
					$('#hashtagCollapse').collapse('show');
				}			
			})
			
			$('#hashtagCollapse').children().children().on('click', function(){
				var hashtagText = $(this).text();
				var hashtagValue = $(this).val();
				console.log("hashtagText ? : " + hashtagText);
				$('input[name="hashtagValue"]').val(hashtagValue);
				
				console.log('hashtagValue ? : ' + $('input[name="hashtagValue"]').val());
				
				$('#hashtagCollapse').collapse('hide');
				
				$('#hashtagCollapse').on('hidden.bs.collapse', function(){
					$('button[name="hashtag"]').text(hashtagText);
				})
			})
			
			
			$('#searchConditionCollapse').children().children().on('click', function(){
				var searchCondition = $(this).val();
				var searchConditionText = $(this).text();
				
				console.log('searchConditionText ? : ' + searchConditionText);
				
				$('input[name="searchCondition"]').val(searchCondition);
				
				$('#searchConditionCollapse').collapse('hide');
				
				$('#searchConditionCollapse').on('hidden.bs.collapse', function(){
					$('button[name="searchCondition"]').text(searchConditionText);
				})
			})
			
			$('button[name="searchButton"]').on("click", function(){
				var categoryValue = $('input[name="categoryValue"]').val();
				var hashtagValue = $('input[name="hashtagValue"]').val();
				var searchCondition = $('input[name="searchCondition"]').val();
				var searchKeyword = $('input[name="searchKeyword"]').val();
				
				console.log('searchKeyword ? : ' + searchKeyword);
				
				if ( categoryValue == '' ) {
					$('#searchHelp').text('카테고리를 선택해주세요.');
					return;
				}
				
				if ( hashtagValue == '' ) {
					$('#searchHelp').text('해쉬태그를 선택해주세요.');
					return;
				}
				
				if ( searchCondition == '' ) {
					$('#searchHelp').text('검색조건을 선택해주세요.');
					return;
				}
				
				if ( searchKeyword == '' ) {
					$('#searchHelp').text('검색어를 입력해주세요.');
					return;
				}
				
				var hashtagList = [hashtagValue, 'E01', 'E02']; 
				
				var obj = new Object();
				
				obj.currentPage = currentPage;
				obj.searchCondition = searchCondition;
				obj.category = categoryValue; 
				obj.hashtag = hashtagList;
				obj.searchKeyword = searchKeyword; 
				
				var stateValue = $('input[name="stateValue"]').val();
				
				$.ajax(
						{
							url : "/searchHobbyClass/json/getHobbyClassList", 
							method :  "post", 
							dataType : "json", 
							headers : {
								"Accept" : "application/json", 
								"Content-Type" : "application/json"
							}, 
							data : JSON.stringify({
								search : obj, 
								stateValue : stateValue 
							}), 
							success : function(JSONData, status) {
								var display = "";
								for(var i = 0; i < JSONData.hobbyClassList.length; i++) {
									display += "<button type='button' class='btn btn-outline-secondary btn-sm' name='classSteam' value='" + JSONData.hobbyClassList[i].hobbyClassNo + "'>" + JSONData.hobbyClassList[i].hobbyClassName + " : " + JSONData.hobbyClassList[i].steamCount + " => " + JSONData.hobbyClassList[i].steamCheck + "<input type='hidden' name='steamCheck' value='"+ JSONData.hobbyClassList[i].steamCheck +"'><input type='hidden' name='steamCount' value='"+ JSONData.hobbyClassList[i].steamCount +"'></button>"
									display += "<br/>";
									display += "<br/>";
								}
								display += "<span></span>"
								$("span:last").append(display);
								
								maxPage = JSONData.resultPage.totalCount;
							}
						}
				)
				
			})
			
			$('.dropdown-menu > a').on('click', function(){
				$('input[name="stateValue"]').val( $(this).children('input[name="dropdownHidden"]').val() );
				$('#dropdownMenuButton').text( $(this).text() );
			})
			
			$(document).on('click', 'button[name="classSteam"]', function(){
				var button = $(this);
				var steamCheckElem = $(this).children('input[name="steamCheck"]');
				var steamCountElem = $(this).children('input[name="steamCount"]');
				
				console.log('button ? : ' + button.text());
				
				var classNo = button.val();
				var steamCheck = steamCheckElem.val();
				var steamCount = steamCountElem.val();
				
				console.log('--------steamCheck ? : ' + steamCheck);
				console.log('--------classNo ? : ' + classNo);
				console.log('--------steamCount ? : ' + steamCount);
				
				var url = '';
				
				if ( steamCheck == '0' ) {
					url = '/myHobbyClass/json/addSteamHobbyClass';
				}
				else {
					url = '/myHobbyClass/json/deleteSteamHobbyClass';
				}
				$.ajax(
						{
							url : url, 
							method : "post", 
							dataType : "json", 
							headers : {
								"Accept" : "application/json",
								"Content-Type" : "application/json"
							}, 
							data : JSON.stringify({
								hobbyClassNo : classNo,
								steamCount : steamCount 
							}), 
							success : function(JSONData, status) {
								button.html('');
								var display = JSONData.hobbyClass.hobbyClassName + " : " + JSONData.hobbyClass.steamCount + " => " + JSONData.hobbyClass.steamCheck + "<input type='hidden' name='steamCheck' value='"+ JSONData.hobbyClass.steamCheck +"'><input type='hidden' name='steamCount' value='"+ JSONData.hobbyClass.steamCount +"'>";
								button.html(display);
							}
						}
				)
			})
			
			
			$('button[name="beforePage"]').on('click', function(){
				var categoryValue = $('input[name="categoryValue"]').val();
				var hashtagValue = $('input[name="hashtagValue"]').val();
				var searchCondition = $('input[name="searchCondition"]').val();
				var searchKeyword = $('input[name="searchKeyword"]').val();
				
				var hashtagList = [hashtagValue, 'E01', 'E02']; 
				
				var obj = new Object();
				currentPage -= 1;
				
				obj.currentPage = currentPage;
				obj.searchCondition = searchCondition;
				obj.category = categoryValue; 
				obj.hashtag = hashtagList;
				obj.searchKeyword = searchKeyword; 
				
				var stateValue = $('input[name="stateValue"]').val();
				
				$.ajax(
						{
							url : "/searchHobbyClass/json/getHobbyClassList", 
							method :  "post", 
							dataType : "json", 
							headers : {
								"Accept" : "application/json", 
								"Content-Type" : "application/json"
							}, 
							data : JSON.stringify({
								search : obj, 
								stateValue : stateValue 
							}), 
							success : function(JSONData, status) {
								var display = "";
								for(var i = 0; i < JSONData.hobbyClassList.length; i++) {
									display += "<button type='button' class='btn btn-outline-secondary btn-sm' name='classSteam' value='" + JSONData.hobbyClassList[i].hobbyClassNo + "'>" + JSONData.hobbyClassList[i].hobbyClassName + " : " + JSONData.hobbyClassList[i].steamCount + " => " + JSONData.hobbyClassList[i].steamCheck + "<input type='hidden' name='steamCheck' value='"+ JSONData.hobbyClassList[i].steamCheck +"'><input type='hidden' name='steamCount' value='"+ JSONData.hobbyClassList[i].steamCount +"'></button>"
									display += "<br/>";
									display += "<br/>";
								}
								display += "<span></span>"
								$("span:last").append(display);
							}
						}
				)
			})
			
			$('button[name="afterPage"]').on('click', function(){
				var categoryValue = $('input[name="categoryValue"]').val();
				var hashtagValue = $('input[name="hashtagValue"]').val();
				var searchCondition = $('input[name="searchCondition"]').val();
				var searchKeyword = $('input[name="searchKeyword"]').val();
				
				var hashtagList = [hashtagValue, 'E01', 'E02']; 
				
				var obj = new Object();
				currentPage += 1;
				
				obj.currentPage = currentPage;
				obj.searchCondition = searchCondition;
				obj.category = categoryValue; 
				obj.hashtag = hashtagList;
				obj.searchKeyword = searchKeyword; 
				
				var stateValue = $('input[name="stateValue"]').val();
				
				if(maxPage >= currentPage) {
					$.ajax(
							{
								url : "/searchHobbyClass/json/getHobbyClassList", 
								method :  "post", 
								dataType : "json", 
								headers : {
									"Accept" : "application/json", 
									"Content-Type" : "application/json"
								}, 
								data : JSON.stringify({
									search : obj, 
									stateValue : stateValue 
								}), 
								success : function(JSONData, status) {
									var display = "";
									for(var i = 0; i < JSONData.hobbyClassList.length; i++) {
										display += "<button type='button' class='btn btn-outline-secondary btn-sm' name='classSteam' value='" + JSONData.hobbyClassList[i].hobbyClassNo + "'>" + JSONData.hobbyClassList[i].hobbyClassName + " : " + JSONData.hobbyClassList[i].steamCount + " => " + JSONData.hobbyClassList[i].steamCheck + "<input type='hidden' name='steamCheck' value='"+ JSONData.hobbyClassList[i].steamCheck +"'><input type='hidden' name='steamCount' value='"+ JSONData.hobbyClassList[i].steamCount +"'></button>"
										display += "<br/>";
										display += "<br/>";
									}
									display += "<span></span>"
									$("span:last").append(display);
								}
							}
					)
				}	
			})
			
		})
	</script>
	
	<script type="text/javascript">
		$(function(){
			$('button[name="getClass"]').on('click', function(){
				self.location = '/searchHobbyClass/getSearchHobbyClassIntro?hobbyClassNo=' + $(this).val();
			})
		})
	</script>
	
</head>
<body>
	<br/><br/><br/>
	<form>
		<div class="form-group">
			<div class="container">
				<div class="row">
					<div class="col-lg-3 mr-3 mt-3">
						<button type="button" class="btn btn-secondary btn-lg btn-block" name="category" data-toggle="collapse" data-target="#categoryCollapse" aria-expanded="false" aria-controls="categoryCollapse">카테고리</button>
						<input type="hidden" name="categoryValue" value=""/>
					</div>
		
					<div class="col-lg-3 mr-3 mt-3">
						<button type="button" class="btn btn-secondary btn-lg btn-block" name="hashtag" data-toggle="collapse" data-target="#hashtagCollapse" aria-expanded="false" aria-controls="hashtagCollapse">해쉬태그</button>
						<input type="hidden" name="hashtagValue" value=""/>
					</div>
					
					<div class="col-lg-3 mr-3 mt-3">
						<button type="button" class="btn btn-secondary btn-lg btn-block" name="searchCondition" data-toggle="collapse" data-target="#searchConditionCollapse" aria-expanded="false" aria-controls="hashtagCollapse">검색조건</button>
						<input type="hidden" name="searchCondition" value=""/>
					</div>
					
					<div class="col-lg-3 mr-3 mt-3">
						<div class="dropdown">
							<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								클래스 진행상태 
							</button>
							<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
								<a class="dropdown-item">전체보기<input type="hidden" name="dropdownHidden" value="0"></a>
								<a class="dropdown-item">수요조사 중<input type="hidden" name="dropdownHidden" value="3"></a>
								<a class="dropdown-item">개강<input type="hidden" name="dropdownHidden" value="4"></a> 
							</div>
						</div>
						<input type="hidden" name="stateValue" value="0">
					</div>
				</div>
			</div>
			
			<div class="container">
				<div class="row">
					<div class="col-lg-9 mr-3 mt-3">
						<!-- <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="searchHelp"><button type="button" class="btn btn-outline-secondary btn-sm mr-3 mt-3" name="searchButton"><i class="fas fa-user"></i></button>  -->
						<div class="input-group mb-3" aria-describedby="searchHelp">
						  	<input type="text" class="form-control" name="searchKeyword">
						  	<div class="input-group-append">
						    	<button type="button" class="btn btn-outline-secondary btn-sm" name="searchButton"><i class="fas fa-user"></i></button>
						  	</div>
						</div>
						<small id="searchHelp" class="form-text text-muted"></small>
					</div>

				</div>
			</div>
				
			<div class="container">
				<div class="row">
					<div class="col-lg-3 mr-3 mt-3">
						<div class="collapse" id="categoryCollapse">
					  		<div class="card card-body">
					  			<button type="button" class="btn btn-outline-secondary btn-sm" value="all">전체</button>
					    		<br/>
					    		<button type="button" class="btn btn-outline-secondary btn-sm" value="E">운동</button>
					    		<br/>
					    		<button type="button" class="btn btn-outline-secondary btn-sm" value="A">미술</button>
					    		<br/>
					    		<button type="button" class="btn btn-outline-secondary btn-sm" value="M">음악</button>
					    		<br/>
					    		<button type="button" class="btn btn-outline-secondary btn-sm" value="L">라이프스타일</button>
					    		<br/>
					    		<button type="button" class="btn btn-outline-secondary btn-sm" value="C">요리</button>
					    		<br/>
					    		<button type="button" class="btn btn-outline-secondary btn-sm" value="H">공예</button>
					    		<br/>
					  		</div>
						</div>
					</div>	
		
					<div class="col-lg-3 mr-3 mt-3">
						<div class="collapse" id="hashtagCollapse">
					  		<div class="card card-body">
					  			<button type="button" class="btn btn-outline-secondary btn-sm" name="hashtagButton" value="all">전체</button>
					    		<br/>
					    		
								<button type="button" class="btn btn-outline-secondary btn-sm" name="hashtagButton"></button>
					    		<br/>
					    		
					    		<button type="button" class="btn btn-outline-secondary btn-sm" name="hashtagButton"></button>
					    		<br/>
					    		
					    		<button type="button" class="btn btn-outline-secondary btn-sm" name="hashtagButton"></button>
					    		<br/>
					    		
					    		<button type="button" class="btn btn-outline-secondary btn-sm" name="hashtagButton"></button>
					    		<br/>
					    		
					    		<button type="button" class="btn btn-outline-secondary btn-sm" name="hashtagButton"></button>
					    		<br/>
					    		
					    		<button type="button" class="btn btn-outline-secondary btn-sm" name="hashtagButton"></button>
					    		<br/>
					    		
					    		<button type="button" class="btn btn-outline-secondary btn-sm" name="hashtagButton"></button>
					    		<br/>
					    		
					    		<button type="button" class="btn btn-outline-secondary btn-sm" name="hashtagButton"></button>
					    		<br/>
					    		
					    		<button type="button" class="btn btn-outline-secondary btn-sm" name="hashtagButton"></button>
					    		<br/>
					    		
					    		<button type="button" class="btn btn-outline-secondary btn-sm" name="hashtagButton"></button>
					    		<br/>
					  		</div>
						</div>
					</div>
					
					<div class="col-lg-3 mr-3 mt-3">
						<div class="collapse" id="searchConditionCollapse">
							<div class="card card-body">
								<button type="button" class="btn btn-outline-secondary btn-sm" name="searchConditionButton" value="0">전체보기</button>
					    		<br/>
					    		
					    		<button type="button" class="btn btn-outline-secondary btn-sm" name="searchConditionButton" value="2">크리에이터 닉네임</button>
					    		<br/>
					    		
					    		<button type="button" class="btn btn-outline-secondary btn-sm" name="searchConditionButton" value="1">클래스 제목</button>
					    		<br/>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	
	
	<br/><br/><br/><br/><br/>
	<div class="container">
		<button type="button" class="btn btn-outline-secondary btn-sm" name="beforePage">이전페이지버튼</button>
		<button type="button" class="btn btn-outline-secondary btn-sm" name="afterPage">다음페이지버튼</button>
		<button type="button" class="btn btn-outline-secondary btn-sm" name="getClass" value="10000">10000 클래스로</button>
		<br/>
	</div>
	
	<div class="container">
		<span></span>
	</div>

</body>
</html>