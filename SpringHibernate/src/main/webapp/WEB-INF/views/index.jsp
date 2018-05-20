<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.04 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.12.4.min.js"></script>
<title>First JSP</title>
</head>
<body>
	<h2>Hello World!</h2>
	<h2>${user}</h2>
	
<%-- 	<c:forEach var="users" items="${user}" varStatus="u">
		<h4>${users.userName}</h4>
	</c:forEach> --%>
</body>

<!-- <script type="text/javascript">
	$(function(){
		$.ajax({
			type:"GET",
			url:"${pageContext.request.contextPath}/testJson",
			dataType:"json",
			success:function(data){
				$.each(data , function(commentIndex,comment){
					alert(comment['userName']);
				});
			}
		});		
	});
</script> -->
</html>


