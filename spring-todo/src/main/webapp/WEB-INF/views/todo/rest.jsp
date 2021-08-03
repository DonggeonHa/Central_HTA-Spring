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
<c:set var="menu" value="rest"/>
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
						<tbody></tbody>
					</table>
				</div>
			</div>
		</div>
	</main>
	<!-- 새 일정 등록, 일정 수정할 때 사용되는 모달창 -->
	<div class="modal fade" id="form-todo-modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">일정 등록 및 수정</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<form id="form-todo">
							<!-- 일정수정인 경우 일정번호가 입력된다. 등록인 경우 필드를 비활성화시킨다. -->
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
								<!--
									일정의 상태를 설정하는 부분
									일정 등록인 경우 무조건 등록이 체크되어 있고, 완료/보류는 비활성화 한다.
									일정 수정인 경우 완료/보뷰를 비활성화 하지 않는다.
								 -->
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
						<!-- 
							등록인 경우 버튼의 텍스트를 "등록"으로 수정인 경우 "수정"으로 표시되도록 함.
						 -->
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
	// 요청 방식을 저장하는 변수
	// 등록인 경우 "POST", 수정인 경우 "PUT"으로 설정되게 한다.
	// "새 일정 등록 버튼"을 클릭하면 "POST"로 설정하고, 제목을 클릭해서 수정용 모달을 열 때 "PUT"으로 설정한다.
	var requestMethod = "POST";
	
	// 모달창을 스크립트로 제어하기 위해서 모달창객체 생성한다.
	var todoModal = new bootstrap.Modal(document.getElementById("form-todo-modal"), {
		keyboard: false
	});
	
	// "새 일정 등록"을 클릭했을 실행되는 메소드다.
	// requestMethod를 POST로 설정한다. 
	// 입력폼의 모든 값을 지우고, 카테고리는 "회의", 상태는 "등록"체크되게 한다.
	// 상태 라이오버튼은 0번째를 제외한 나머지들은 비활성화 시킨다.
	$("#btn-open-todo-modal").click(function() {
		requestMethod = "POST";
		
		$("#todo-no").val("");
		$(":radio[name=category]").eq(0).prop("checked", true);
		$("#todo-title").val("");
		$("#todo-writer").val("");
		$("#todo-due-date").val("");
		$("#todo-content").val("");
		$(":radio[name=status]").eq(0).prop("checked", true)					// 0번째 상태 라이오버튼을 체크되게 한다.
								.end()											// .end()는 eq(0) 실행이전 상태로 jQuery조회 결과를 되돌린다.
								.filter(":gt(0)").prop("disabled", true)		// 0번째 다음에 있는 라이오버튼을 비활성화 한다.
		$("#btn-post-todo").text("등록");										// 모달창의 버튼 텍스트를 "등록"으로 변경한다.
		todoModal.show();
	});
	
	// 일정제목을 클릭했을 때, 실행된다.
	// 비활성화 되어 있는 모든 입력요소를 활성화 시킨다.
	// 모달창의 버튼 텍스트를 '수정'으로 변경한다.
	// requestMethod를 'PUT'으로 변경한다.
	// 서버랑 통신해서 내가 클릭한 일정의 상세정보를 조회한다.
	$("#table-todos tbody").on('click', '.btn-link', function(event) {
		$(":input:disabled").prop("disabled", false);
		$("#btn-post-todo").text("수정");
		requestMethod = "PUT";
		
		event.preventDefault();
		$.getJSON("rest/todos/" + $(this).data("todo-no"))										// 서버로 rest/tods/일정번호 요청을 보낸다.
			.done(function(todo) {																// 조회된 일정정보를 화면에 표시한다.
				$("#todo-no").val(todo.no);														// 히든 필드에 일정번호 입력
				$(":radio[name=category][value="+todo.category+"]").prop("checked", true);		// 사용자가 체크한 라이오 버튼이 체크되게 한다.
				$("#todo-title").val(todo.title);												// 입력필드에 일정정보 표시
				$("#todo-writer").val(todo.writer);												// 입력필드에 일정정보 표시
				$("#todo-due-date").val(todo.dueDate);											// 입력필드에 일정정보 표시
				$("#todo-content").val(todo.content);											// 입력필드에 일정정보 표시
				$(":radio[name=status][value="+todo.status+"]").prop("checked", true);			// 일정의 현재상태에 맞는 라디오 버틈을 체크한다.
				
				todoModal.show();																// 모달창을 표시한다.
			})
	});
	
	// 등록/수정버튼을 클릭했을 때 실행된다.
	// 입력필드의 모든 값을 읽어와서 javascript 객체를 생성한다.
	$("#btn-post-todo").click(function() {
		var todo = {															// 입력필드의 모든 값을 읽어서 자바스크립트 객체 생성
			category: $(":radio[name=category]:checked").val(),
			title: $("#todo-title").val(),
			writer: $("#todo-writer").val(),
			dueDate: $("#todo-due-date").val(),
			content: $("#todo-content").val(),
			status: $(":radio[name=status]:checked").val()
		};
		
		var requestURI = "rest/todos/"											// 요청URL을 설정한다.
		if (requestMethod == "PUT") {											// 등록인 경우에는 요청URI를 "rest/todo""로 설정한다.
			requestURI += $("#todo-no").val(); 									// 수정인 경우에만 요청URI을 "rest/todos/번호"로 설정한다.
		}
		
		$.ajax({
			type: requestMethod,												// POST 혹은 PUT 이다.
			url: requestURI,													// rest/todos/ 혹은 rest/todos/번호 다
			data: JSON.stringify(todo),											// REST에서는 주고받은 모든 데이터를 JSON 형식으로 주고 받는다. '{"category":"외근", "title":"노동부방문", ...}' 데이터가 서버로 전달된다.
			contentType: 'application/json; charset=utf-8',						// 서버에게 전송하는 데이터의 형식이 json 형식임을 지정한다.
			dataType: 'json',													// 서버로부터 내려오는 응답컨텐츠의 타입을 json 형식으로 지정한다.
			success: function(todo) {
				if (requestMethod == "POST") {									// 등록인 경우
					$("#table-todos tbody").prepend(makeRow(todo));				// 새로운 행을 만들어서 tbody에 맨 앞쪽에 행을 추가한다.
				} else if (requestMethod == "PUT") {							// 수정인 경우
					var $tds = $("#todo-" + todo.no).find("td");				// 대상행을 찾아서 대상행의 각 칸의 값을 변경한다.
					$tds.eq(1).text(todo.category);
					$tds.eq(2).find("button").text(todo.title);
					$tds.eq(3).text(todo.dueDate);
					$tds.eq(4).find("span").text(todo.status)
						.removeClass(["bg-primary", "bg-success", "bg-secondary"]).addClass(bgColor(todo.status));
				}
			},
			complete: function() {
				todoModal.hide();												// 모둔 작업이 완료되면 모달창을 닫는다.
			}
		});
	});
	
	// 삭제버튼을 클릭했을 때 실행된다.
	// 서버로 "DELETE"와"rest/todos/일정번호"를 전송해서 일정을 삭제하게 한다. 
	$("#table-todos tbody").on('click', '.btn-outline-danger', function() {
		var $tr = $(this).closest("tr");
		$.ajax({
			type: "DELETE",
			url: "rest/todos/" + $(this).data("todo-no"),
			success: function() {
				$tr.remove();													// 서버에서 응답이 도착하면 해당 행을 삭제한다.
			}
		});
	});
	
	// 즉시 실행함수를 정의해서, 페이지가 로딩될 때 자동실행되게 함
	// 서버 "GET"와 "rest/todos/"요청을 전송해서 모든 일정정보를 응답으로 받는다.
	(function() {
		$.getJSON("rest/todos/", function(todos) {								// 요청이 성공적으로 완료되면 일정정보들이 응답으로 내려옴
			$.each(todos, function(index, todo) {								// $.each(배열, 콜백함수)를 사용해서 배열의 각요소를 순회한다.
				$("#table-todos tbody").append(makeRow(todo));					// 배열의 각 요소로 테이블에 추가한다.
			})
		});
	})();
	
	// 일정정보를 받아서 <tr>, <td>로 구성된 행을 만들어서 반환한다.
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
	
	// 일정상태를 전달받아서 상태뱃지의 배경으로 사용할 컬러를 반환한다.
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