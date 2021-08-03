package com.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sample.dao.TodoDao;
import com.sample.vo.Todo;

@Controller
public class HomeController {
	
	@Autowired TodoDao todoDao;

	@GetMapping({"/", "/home", "/main"})
	public String home() {
		return "home";
	}
	
	@RequestMapping({"/ajax"})
	public String ajaxTodos(Model model) {
		List<Todo> savedTodos = todoDao.getAllTodos();
		model.addAttribute("todos", savedTodos);
		
		return "todo/ajax";
	}
	@RequestMapping({"/rest"})
	public String restTodos() {
		return "todo/rest";
	}
}
