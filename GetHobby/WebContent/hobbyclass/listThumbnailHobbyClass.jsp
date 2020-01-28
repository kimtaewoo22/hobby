<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	
	<title>Insert title here</title>
	
	<script type="text/javascript">
		$(function(){
			$('.collapse').collapse({
				toggle : false 
			});
			
			$('button[name="toggleSwitch"]').on("click", function(){
				$('.collapse').collapse('hide');
			})
			
			$('.collapse').on('hidden.bs.collapse', function(){
				$('button[name="category"]').text('됩니까?');
			})
		})
	</script>
</head>
<body>
	<br/><br/><br/>
	

	<button type="button" class="btn btn-secondary" name="category" data-toggle="collapse" data-target="#categoryCollapse" aria-expanded="false" aria-controls="categoryCollapse">카테고리</button>
	<button type="button" class="btn btn-secondary" name="category" data-toggle="collapse" data-target="#hashtagCollapse" aria-expanded="false" aria-controls="hashtagCollapse">해쉬태그</button>
	
	<div class="row">
			<div class="collapse" id="categoryCollapse">
		  		<div class="card card-body">
		    		Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident.
		  		</div>
			</div>	
			<div class="collapse" id="hashtagCollapse">
		  		<div class="card card-body">
		    		Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident.
		  		</div>
			</div>	
	</div>
	<button type="button" class="btn btn-secondary" name="toggleSwitch">collapse</button>
</body>
</html>