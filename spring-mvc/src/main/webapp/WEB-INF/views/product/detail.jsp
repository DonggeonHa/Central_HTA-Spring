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
<%@ include file="../common/nav.jsp" %>
<div class="container my-3">
	<main>
		<div class="row mb-3">
			<div class="col">
				<div class="border p-2 bg-dark text-white fw-bold">상품 상세정보</div>
			</div>
		</div>
		<div class="row mb-3">
			<div class="col">
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
								<td>100</td>
								<th>등록일자</th>
								<td>2021년 7월 14일</td>
							</tr>
							<tr>
								<th>상품명</th>
								<td>에이든 아쿠아텍스 패브릭 3인용 소파</td>
								<th>리뷰갯수</th>
								<td>1,000 개</td>
							</tr>
							<tr>
								<th>카테고리</th>
								<td>가구</td>
								<th>제조사</th>
								<td>중앙 가구침대 주식회사</td>
							</tr>
							<tr>
								<th>가격</th>
								<td>1,000,000 원</td>
								<th>할인가격</th>
								<td><strong class="text-danger">890,000</strong> 원</td>
							</tr>
							<tr>
								<th>재고량</th>
								<td>100 개</td>
								<th>판매여부</th>
								<td><strong class="text-success">판매중</strong></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="col">
			
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