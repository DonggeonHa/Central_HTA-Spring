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
<div class="container my-3">
	<header>
		<c:set var="menu" value="home"/>
		<%@ include file="common/nav.jsp" %>
	</header>
	<main>
		<div class="row my-3">
			<div class="col">
				<div class="bordered bg-light fw-bold p-2">첨부파일 상세정보</div>
			</div>
		</div>
		<div class="row mb-3">
			<div class="col">
				<table class="table">
					<colgroup>
						<col width="15%">
						<col width="35%">
						<col width="15%">
						<col width="35%">
					</colgroup>
					<tbody>
						<tr>
							<th>번호</th>
							<td>${file.no }</td>
							<th>등록일</th>
							<td><fmt:formatDate value="${file.createdDate }" pattern="yyyy년 M월 d일"/></td>
						</tr>
						<tr>
							<th>제목</th>
							<td>${file.title }</td>
							<th>작성자</th>
							<td>${file.writer }</td>
						</tr>
						<tr>
							<th>설명</th>
							<td>${file.description }</td>
						</tr>
					</tbody>
				</table>
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