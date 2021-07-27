<%@ page pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="container">
		<a class="navbar-brand" href="/spring-todo/home">샘플 애플리케이션</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item">
					<a class="nav-link ${menu eq 'home' ? 'active fw-bold' : '' }"  href="/spring-todo/home">홈</a>
				</li>
				<li class="nav-item">
					<a class="nav-link ${menu eq 'todo' ? 'active fw-bold' : '' }" href="/spring-todo/todo/list">일정보기</a>
				</li>
			</ul>
		</div>
	</div>
</nav>