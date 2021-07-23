<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!doctype html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" >
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<title>샘플 애플리케이션</title>
</head>
<body>
<c:set var="menu" value="my" />
<%@ include file="../common/nav.jsp" %>
<div class="container my-3">
	<main>
		<div class="row mb-3">
			<div class="col">
				<div class="border p-2 bg-dark text-white fw-bold">사용자 상세정보</div>
			</div>
		</div>
		<div class="row mb-3">
			<div class="col">
				<div class="p-3 mb-4 bg-light rounded-3 text-dark">
					<p class="col-8"><span class="fs-1">${LOGINED_USER.name }</span>님의 현재 적립포인트는
						<strong class="text-danger fs-2"><fmt:formatNumber value="${user.point}" /></strong>점 입니다.</p>
				</div>
			</div>
		</div>
		<div class="row mb-3">
			<div class="col">
				<div class="border p-2 bg-secondary text-white fw-bold">장바구니</div>
			</div>
		</div>
		<div class="row mb-3">
			<div class="col">
				<div class="border p-2 bg-secondary text-white fw-bold">최근 주문내역</div>
			</div>
		</div>
		<div class="row mb-3">
			<div class="col">
				<div class="border p-2 bg-secondary text-white fw-bold">내가 작성한 리뷰</div>
			</div>
		</div>
		<div class="row mb-3">
			<div class="col">
				<div class="border p-2 bg-secondary text-white fw-bold">포인트 변경 내역</div>
			</div>
		</div>
	</main>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>

</script>
</body>
</html>