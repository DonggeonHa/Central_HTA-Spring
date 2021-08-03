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
<c:set var="menu" value="ajax"/>
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
							<col width="10%">
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
										<tr id="todo-${todo.no }">
											<td>${todo.no }</td>
											<td>${todo.category }</td>
											<td><button class="btn btn-link btn-sm" data-todo-no="${todo.no }">${todo.title }</button></td>
											<td><fmt:formatDate value="${todo.dueDate }" pattern="yyyy-MM-dd"/></td>
											<td>
												<c:choose>
													<c:when test="${todo.status eq '등록' }">
														<span class="badge bg-primary">${todo.status }</span>
													</c:when>
													<c:when test="${todo.status eq '완료' }">
														<span class="badge bg-success">${todo.status }</span>
													</c:when>
													<c:when test="${todo.status eq '보류' }">
														<span class="badge bg-secondary">${todo.status }</span>
													</c:when>
												</c:choose>
											</td>
											<td><button class="btn btn-outline-danger btn-sm rm-2" data-todo-no="${todo.no }">삭제하기</button></td>
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
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">새 일정쓰기</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<form id="form-todo">
							<input type="hidden" name="no" id="todo-no">
							<div class="row px-2 mb-2">
								<div class="form-check">
									<div class="form-check form-check-inline">
										<input class="form-check-input" type="radio" name="category" value="회의" checked="checked">
										<label class="form-check-label">회의</label>
									</div>
									<div class="form-check form-check-inline">
										<input class="form-check-input" type="radio" name="category" value="외근">
										<label class="form-check-label">외근</label>
									</div>
									<div class="form-check form-check-inline">
										<input class="form-check-input" type="radio" name="category" value="출장">
										<label class="form-check-label">출장</label>
									</div>
									<div class="form-check form-check-inline">
										<input class="form-check-input" type="radio" name="category" value="휴가">
										<label class="form-check-label">휴가</label>
									</div>
								</div>
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
							<div class="row px-2 mb-2">
								<div class="form-check">
									<div class="form-check form-check-inline">
										<input class="form-check-input" type="radio" name="status" value="등록" checked="checked">
										<label class="form-check-label">등록</label>
									</div>
									<div class="form-check form-check-inline">
										<input class="form-check-input" type="radio" name="status" value="완료" disabled="disabled">
										<label class="form-check-label">완료</label>
									</div>
									<div class="form-check form-check-inline">
										<input class="form-check-input" type="radio" name="status" value="보류" disabled="disabled">
										<label class="form-check-label">보류</label>
									</div>
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
						<button type="button" class="btn btn-primary" id="btn-post-todo">등록</button>
					</div>
				</div>
		</div>
	</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
$(function() {
	var request = "등록"
	var requestURI = "todos/add";
	
	var todoModal = new bootstrap.Modal(document.getElementById("form-todo-modal"), {
		keyboard: false
	})
	
	// 새 일정 등록 버튼을 클릭했을 때 실행된다.
	$("#btn-open-todo-modal").click(function() {
		requestURI = "todos/add";
		request = "등록"
		
		$("#todo-no").val("").prop("disabled", true);
		$(":radio[name=category]").eq(0).prop("checked", true);
		$("#todo-title").val("");
		$("#todo-writer").val("");
		$("#todo-due-date").val("");
		$("#todo-content").val("");
		$(":radio[name=status]").eq(0).prop("checked", true).end().filter(":gt(0)").prop("disabled", true)
		$("#btn-post-todo").text("등록");
		
		todoModal.show();
	});
	
	// 모달창에서 등록/수정 버튼을 클릭했을 때 실행된다.
	$("#btn-post-todo").click(function() {
		
		$.ajax({
			type: "POST",
			url: requestURI,
			data: $("#form-todo").serialize(),																						// category=외근&title=노동부방문&writer=홍길동 쿼리스트링형태로 서버로 전달된다.
			dataType: 'json',
			success: function(todo) {
				if (request == "등록") {
					$("#table-todos tbody").prepend(makeRow(todo));
				} else if (request == "수정") {
					var $tds = $("#todo-" + todo.no).find("td");
					$tds.eq(1).text(todo.category);
					$tds.eq(2).find("button").text(todo.title);
					$tds.eq(3).text(todo.dueDate);
					$tds.eq(4).find("span").text(todo.status)
						.removeClass(["bg-primary", "bg-success", "bg-secondary"]).addClass(bgColor(todo.status));
				}
			},
			complete: function() {
				todoModal.hide();
			}
		});
	})
	
	// 삭제버튼을 클릭했을 때
	$("#table-todos tbody").on('click', '.btn-outline-danger', function() {
		var $tr = $(this).closest("tr");
		$.ajax({
			type: "GET",
			url: "todos/delete",
			data: {no: $(this).data("todo-no")},
			success: function() {
				$tr.remove();
			}
		});
	});
	
	// 일정제목을 클릭했을 때
	$("#table-todos tbody").on('click', '.btn-link', function(event) {
		request = "수정";
		requestURI = "todos/modify";
		$("#btn-post-todo").text("수정");
		$(":input:disabled").prop("disabled", false);
		
		event.preventDefault();
		$.getJSON("todos/detail?no=" + $(this).data("todo-no"))
			.done(function(todo) {
				$("#todo-no").val(todo.no);
				$(":radio[name=category][value="+todo.category+"]").prop("checked", true);
				$("#todo-title").val(todo.title);
				$("#todo-writer").val(todo.writer);
				$("#todo-due-date").val(todo.dueDate);
				$("#todo-content").val(todo.content);
				$(":radio[name=status][value="+todo.status+"]").prop("checked", true);
				
				todoModal.show();
			})
	});
	
	function makeRow(todo) {
		var row = "<tr  class='align-middle' id='todo-"+todo.no+"'>"
		row += "<td>"+todo.no+"</td>";
		row += "<td>"+todo.category+"</td>";
		row += "<td><button class='btn btn-link' data-todo-no='"+todo.no+"'>"+todo.title+"</td>";
		row += "<td>"+todo.dueDate+"</td>";
		row += "<td><span class='badge p-2 px-3 "+bgColor(todo.status)+"'>"+todo.status+"</span></td>";
		row += "<td><button class='btn btn-outline-danger btn-sm' data-todo-no='"+todo.no+"'>삭제하기</button></td>";
		row += "</tr>";
		return row;
	}	
	
	function bgColor(status) {
		if (status == '등록') {
			return "bg-primary";
		}
		if (status == '완료') {
			return "bg-success";
		}
		if (status == '보류') {
			return "bg-secondary";
		}
	}
	
})
</script>
</body>
</html>