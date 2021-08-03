package com.sample.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sample.dao.TodoDao;
import com.sample.vo.Todo;

@Controller
public class TodoAjaxController {

	@Autowired TodoDao todoDao;
	
	@RequestMapping("/todos/add")
	public @ResponseBody ResponseEntity<Todo> add(Todo todo) {
		todoDao.insertTodo(todo);
		Todo savedTodo = todoDao.getTodoByNo(todo.getNo());
		return new ResponseEntity<Todo>(savedTodo, HttpStatus.OK);
	}
	
	@RequestMapping("/todos/detail")
	public @ResponseBody ResponseEntity<Todo> detail(@RequestParam("no") int todoNo) {
		Todo savedTodo = todoDao.getTodoByNo(todoNo);
		if (savedTodo == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(savedTodo, HttpStatus.OK);
	}
	
	@RequestMapping("/todos/delete")
	public @ResponseBody ResponseEntity<Void> delete(@RequestParam("no") int todoNo) {
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
	
	@RequestMapping("/todos/modify")
	public @ResponseBody ResponseEntity<Todo> modify(Todo todo) {
		Todo savedTodo = todoDao.getTodoByNo(todo.getNo());
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
