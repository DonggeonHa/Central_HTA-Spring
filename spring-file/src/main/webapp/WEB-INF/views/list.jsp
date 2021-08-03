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
				<div class="bordered bg-light fw-bold p-2">첨부파일 목록</div>
			</div>
		</div>
		<div class="row mb-3">
			<div class="col">
				<table class="table">
					<colgroup>
						<col width="5%">
						<col width="*">
						<col width="10%">
						<col width="10%">
						<col width="15%">
						<col width="15%">
					</colgroup>
					<thead>
						<tr>
							<th>순번</th>
							<th>제목</th>
							<th>작성자</th>
							<th>파일갯수</th>
							<th>등록일</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${empty files }">
								<tr>
									<td colspan="6" class="text-center">첨부파일이 존재하지 않습니다.</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach var="file" varStatus="loop" items="${files }">
									<tr class="align-middle">
										<td>${loop.count }</td>
										<td>${file.title }</td>
										<td>${file.writer }</td>
										<td>${file.amount } 개</td>
										<td><fmt:formatDate value="${file.createdDate }" pattern="yyyy년 M월 d일"/> </td>
										<td><a class="btn btn-outline-primary btn-sm" href="detail?no=${file.no }">첨부파일 상세정보</a></td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
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