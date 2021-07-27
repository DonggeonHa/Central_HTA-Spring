package com.sample.dao;

import java.util.List;

import com.sample.vo.Todo;

public interface TodoDao {

	void insertTodo(Todo todo);
	void updateTodo(Todo todo);
	Todo getTodoByNo(int todoNo);
	List<Todo> getAllTodos();
}
