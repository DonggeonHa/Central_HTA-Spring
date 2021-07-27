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
<c:set var="menu" value="todo"/>
<%@ include file="../common/nav.jsp" %>
<div class="container my-3">
	<main>
		<div class="row mb-3">
			<div class="col">
				<div class="border p-2 bg-light d-flex justify-content-between">
					<span>일정 목록</span>
					<button class="btn btn-primary btn-sm" id="btn-open-todo-modal">새 일정 등록</button>
				</div>
			</div>
		</div>
		<div class="row mb-3">
			<div class="col">
				<div class="border p-2 bg-light">
					<table class="table" id="table-todos">
						<colgroup>
							<col width="10%">
							<col width="10%">
							<col width="*">
							<col width="10%">
							<col width="10%">
							<col width="15%">
						</colgroup>
						<thead>
							<tr>
								<th>번호</th>
								<th>카테고리</th>
								<th>제목</th>
								<th>예정일</th>
								<th>상태</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${empty todos }">
									<tr>
										<td class="text-center" colspan="6">등록된 일정이 없습니다.</td>
									</tr>
								</c:when>
								<c:otherwise>
									<c:forEach var="todo" items="${todos }">
										<tr>
											<td>${todo.no }</td>
											<td>${todo.category }</td>
											<td><button class="btn btn-link btn-sm" data-todo-no="${todo.no }">${todo.title }</button></td>
											<td><fmt:formatDate value="${todo.dueDate }" /></td>
											<td>
												<c:choose>
													<c:when test="${todo.status eq '등록' }">
														<span class="badge bg-info">${todo.status }</span>
													</c:when>
													<c:when test="${todo.status eq '완료' }">
														<span class="badge bg-primary">${todo.status }</span>
													</c:when>
																										
												</c:choose>
											</td>
											<td>
												<button class="btn btn-outline-danger btn-sm" data-todo-no="${todo.no }">삭제하기</button>
												<button class="btn btn-outline-primary btn-sm" data-todo-no="${todo.no }">완료하기</button>
											</td>
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</main>
	<div class="modal fade" id="form-todo-modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<form id="form-todo">
				<input type="hidden" name="no" id="todo-no">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">새 일정쓰기</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<div class="row px-2 mb-2">
							<select class="form-control" id="todo-category" name="category">
								<option value="" selected="selected" disabled="disabled"> 카테고리를 선택하세요</option>
								<option value="회의"> 회의</option>
								<option value="미팅"> 미팅</option>
								<option value="외근"> 외근</option>
								<option value="출장"> 출장</option>
								<option value="휴가"> 휴가</option>
							</select>
						</div>
						<div class="row px-2 mb-2">
							<input type="text" class="form-control" id="todo-title" name="title" placeholder="제목을 입력하세요">
						</div>
						<div class="row px-2 mb-2">
							<input type="text" class="form-control" id="todo-writer" name="writer" placeholder="작성자를 입력하세요">
						</div>
						<div class="row px-2 mb-2">
							<input type="date" class="form-control" id="todo-due-date" name="dueDate">
						</div>
						<div class="row px-2">
							<textarea rows="5" class="form-control" id="todo-content" name="content" placeholder="내용을 입력하세요"></textarea>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
						<button type="button" class="btn btn-primary" id="btn-post-todo">등록</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
$(function() {
	var todoModal = new bootstrap.Modal(document.getElementById("form-todo-modal"), {
		keyboard: false
	})
	
	$("#btn-open-todo-modal").click(function() {
		$("#todo-no").val("");
		todoModal.show();
	});
	
	$("#btn-post-todo").click(function() {
		$("#todo-no").prop("disabled", true);
		
		$.ajax({
			type: "POST",
			url: "add",
			data: $("#form-todo").serialize(),
			dataType: 'json',
			success: function(todo) {
				var $tr = $("<tr></tr>");
				$tr.append("<td>"+todo.no+"</td>")
				$tr.append("<td>"+todo.category+"</td>")
				$tr.append("<td><button class='btn btn-link' data-todo-no='"+todo.title+"'>"+todo.title+"</td>")
				$tr.append("<td>"+todo.dueDate+"</td>")
				$tr.append("<td><span class='badge bg-info'>"+todo.status+"</span></td>")
				
				var $completeButton = $("<button></button>").addClass("btn btn-outline-primary btn-sm").text("완료하기").attr("data-todo-no", todo.no);
				var $deleteButton = $("<button></button>").addClass("btn btn-outline-danger btn-sm").text("삭제하기").attr("data-todo-no", todo.no);
				var $buttonTd = $("<td></td>")
				$buttonTd.append($deleteButton).append($completeButton);
				$tr.append($buttonTd);
				
				$("#table-todos tbody").prepend($tr);
			},
			complete: function() {
				todoModal.hide();
			}
		});
	})
	
	$("#table-todos tbody").on('click', '.btn-outline-danger', function() {
		var $tr = $(this).closest("tr");
		$.ajax({
			type: "GET",
			url: "delete",
			data: {no: $(this).data("todo-no")},
			success: function() {
				$tr.remove();
			}
		});
	});
	
	$("#table-todos tbody").on('click', '.btn-outline-primary', function() {
		var $tr = $(this).closest("tr");
		$.ajax({
			type: "GET",
			url: "complete",
			data: {no: $(this).data("todo-no")},
			success: function() {
				$tr.find("span").attr("class", "").addClass("badge bg-primary").text('완료')
			}
		})
	})
	
})
</script>
</body>
</html>