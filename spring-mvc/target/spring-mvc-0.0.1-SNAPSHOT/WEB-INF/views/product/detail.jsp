<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<!doctype html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<title>샘플 애플리케이션</title>
</head>
<body>
<%@ include file="../common/nav.jsp" %>
<div class="container my-3">
	<main>
		<div class="row mb-3">
			<div class="col">
				<div class="border p-2 bg-dark text-white fw-bold">상품 상세정보</div>
			</div>
		</div>
		<div class="row mb-3">
			<div class="col-8">
				<div class="border p-2 bg-light">
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
								<td>${product.no}</td>
								<th>등록일자</th>
								<td><fmt:formatDate value="${product.createdDate}" pattern="yyyy년 M월 d일" /></td>
							</tr>
							<tr>
								<th>카테고리</th>
								<td>${product.category}</td>
								<th>리뷰갯수</th>
								<td><fmt:formatNumber value="${product.reviewCnt}" /> 개</td>
							</tr>
							<tr>
								<th>상품명</th>
								<td colspan="3">${product.name}</td>
							</tr>
							<tr>
								<th>제조사</th>
								<td colspan="3">${product.maker}</td>
							</tr>
							<tr>
								<th>가격</th>
								<td><fmt:formatNumber value="${product.price }" /> 원</td>
								<th>할인가격</th>
								<td><strong class="text-danger"><fmt:formatNumber value="${product.discountPrice }" /></strong> 원</td>
							</tr>
							<tr>
								<th>재고량</th>
								<td>${product.stock} 개</td>
								<th>판매여부</th>
								<td>
									<c:choose>
										<c:when test="${'Y' eq product.soldOut}">
											<strong class="text-danger">품절</strong>
										</c:when>
										<c:otherwise>
											<strong class="text-success">판매중</strong>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="col">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">${product.name}</h5>
						<div class="border-top py-2 mb-3">
							<table class="table">
								<tbody>
									<tr>
										<th>할인 가격</th>
										<td><strong class="text-danger"><fmt:formatNumber value="${product.discountPrice }" /></strong> 원</td>
									</tr>
									<tr>
										<th>적립포인트</th>
										<td><fmt:formatNumber value="${(product.discountPrice) * 0.01}" pattern="##,###" /> 점</td>
									</tr>
									<tr>
										<th>배송비</th>
										<td>무료</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="text-end">
							<a href="addCart?no=${product.no}" class="btn btn-outline-primary ${empty LOGINED_USER ? 'disabled' : ''}">장바구니 담기</a>
							<a href="order?no=${product.no}" class="btn btn-success ${empty LOGINED_USER ? 'disabled' : ''}">바로구매</a>
						</div>
					</div>
				</div>
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