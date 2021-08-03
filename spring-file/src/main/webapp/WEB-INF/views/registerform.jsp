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
		<c:set var="menu" value="register" />
		<%@ include file="common/nav.jsp" %>
	</header>
	<main>
		<div class="row my-3">
			<div class="col">
				<div class="bordered bg-light fw-bold p-2">첨부파일 등록</div>
			</div>
		</div>
		<div class="row mb-3">
			<div class="col">
				<div class="border bg-light p-2">
					<form id="form-register" method="post" action="register" enctype="multipart/form-data">
						<div class="mb-3">
							<label class="form-label">제목</label>
							<input type="text" class="form-control" name="title" placeholder="첨부파일의 제목을 입력하세요" />
						</div>
						<div class="mb-3">
							<label class="form-label">작성자</label>
							<input type="text" class="form-control" name="writer" placeholder="첨부파일을 등록한 등록자명을 입력하세요" />
						</div>
						<div class="mb-3">
							<label class="form-label">설명</label>
							<textarea rows="3" class="form-control" name="description" placeholder="첨부파일의 제목을 입력하세요" ></textarea>
						</div>
						<div class="row">
							<div class="col-12 mb-1 d-flex justify-content-between">
								<span>첨부파일을 등록하세요. </span> 
								<span><button type="button" class="btn btn-outline-primary btn-sm">필드추가 <i class='fas fa-plus'></i></button></span>
							</div>
							<div class="col-12">
								<div class="mb-3">
									<div class="input-group">
										<input type="file" class="form-control" name="upfiles" aria-label="Upload">
										<button class="btn btn-outline-danger" type="button"><i class='fas fa-minus'></i></button>
									</div>
								</div>
								<div class="mb-3">
									<div class="input-group">
										<input type="file" class="form-control" name="upfiles" aria-label="Upload">
										<button class="btn btn-outline-danger" type="button"><i class='fas fa-minus'></i></button>
									</div>
								</div>
								<div class="mb-3">
									<div class="input-group">
										<input type="file" class="form-control" name="upfiles" aria-label="Upload">
										<button class="btn btn-outline-danger" type="button"><i class='fas fa-minus'></i></button>
									</div>
								</div>
							</div>
						</div>
						<div class="text-end">
							<button type="submit" class="btn btn-primary">등록</button>
						</div>
					</form>
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