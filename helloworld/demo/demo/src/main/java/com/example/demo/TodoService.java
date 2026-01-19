package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public Todo addTodo(String text) {
        return repository.save(new Todo(text));
    }

    public void deleteTodo(Long id) {
        repository.deleteById(id);
    }

    public List<Todo> getAllTodos() {
        return repository.findAll();
    }
}
