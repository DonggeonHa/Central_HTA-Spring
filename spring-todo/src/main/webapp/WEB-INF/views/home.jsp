<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!doctype html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<title>샘플 애플리케이션</title>
</head>
<body>
<c:set var="menu" value="home"></c:set>
<%@ include file="common/nav.jsp" %>
<div class="container my-3">
	<div class="p-5 mb-4  bg-light rounded-3">
		<div class="container-fluid py-3">
			<h1 class="display-5 fw-bold">샘플 애플리케이션</h1>
			<p class="col-10 fs-4">일정관리 샘플 프로그램입니다.</p>
			<a class="btn btn-primary btn-lg" href="/spring-todo/todo/list">일정보기</a>
		</div>
	</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>

</script>
</body>
</html>