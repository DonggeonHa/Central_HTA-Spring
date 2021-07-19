<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<h1>Home</h1>
		<%--
			Controller에서 Model객체에 저장한 값은 El, Jstl을 사용해서 표현할 수 있다.
		--%>
		<p>${greeting}</p>
	</body>
</html>