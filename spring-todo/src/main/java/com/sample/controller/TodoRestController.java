package com.sample.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.dao.TodoDao;
import com.sample.vo.Todo;

@RestController
@RequestMapping("/rest")
public class TodoRestController {

	@Autowired TodoDao todoDao;
	
	// 모든 일정 조회
	@GetMapping("/todos/")
	public ResponseEntity<List<Todo>> getTodos() {
		List<Todo> savedTodos = todoDao.getAllTodos();
		
		return new ResponseEntity<>(savedTodos, HttpStatus.OK);
	}
	
	// 새 일정 등록
	@PostMapping("/todos/")
	public ResponseEntity<Todo> addTodo(@RequestBody Todo todo) {
		todoDao.insertTodo(todo);
		Todo savedTodo = todoDao.getTodoByNo(todo.getNo());
		return new ResponseEntity<>(savedTodo, HttpStatus.OK);
	}

	// 일정번호로 일정 상세정보 조회
	@GetMapping("/todos/{no}")
	public ResponseEntity<Todo> getTodo(@PathVariable("no") int todoNo) {
		Todo savedTodo = todoDao.getTodoByNo(todoNo);
		if (savedTodo == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(savedTodo, HttpStatus.OK);
	}
		
	// 일정번호로 일정 정보 삭제
	@DeleteMapping("/todos/{no}")
	public ResponseEntity<Void> deleteTodo(@PathVariable("no") int todoNo) {
		Todo savedTodo = todoDao.getTodoByNo(todoNo);
		if (savedTodo == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		savedTodo.setStatus("삭제");
		savedTodo.setUpdatedDate(new Date());
		savedTodo.setDeletedDate(new Date());
		todoDao.updateTodo(savedTodo);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	// 번호와 일정정보를 전달받아서 일정정보 수정
	@PutMapping("/todos/{no}")
	public ResponseEntity<Todo> updateTodo(@PathVariable("no") int todoNo, @RequestBody Todo todo) {
		Todo savedTodo = todoDao.getTodoByNo(todoNo);
		if (savedTodo == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		savedTodo.setCategory(todo.getCategory());
		savedTodo.setTitle(todo.getTitle());
		savedTodo.setWriter(todo.getWriter());
		savedTodo.setDueDate(todo.getDueDate());
		savedTodo.setContent(todo.getContent());
		savedTodo.setStatus(todo.getStatus());
		savedTodo.setUpdatedDate(new Date());
		savedTodo.setDeletedDate(new Date());
		
		todoDao.updateTodo(savedTodo);
		
		return new ResponseEntity<>(savedTodo, HttpStatus.OK);
	}
	
	
}
