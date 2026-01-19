package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {

    private final TodoRepository repository;

    public TodoController(TodoRepository repository) {
        this.repository = repository;
    }

    // HOME PAGE (HTML)
    @GetMapping("/")
    public String home() {
        List<Todo> todos = repository.findAll();

        String html = "<html><body>"
                + "<h2>Todo List</h2>"

                + "<form method='post' action='/add'>"
                + "<input type='text' name='todo'/>"
                + "<button type='submit'>Add</button>"
                + "</form>"

                + "<ul>";

        for (Todo todo : todos) {
            html += "<li>" + todo.getText()
                 + " <a href='/delete/" + todo.getId() + "'>Delete</a></li>";
        }

        html += "</ul></body></html>";
        return html;
    }

    // ADD TODO
    @PostMapping("/add")
    public String addTodo(@RequestParam String todo) {
        repository.save(new Todo(todo));
        return "<script>window.location='/'</script>";
    }

    // DELETE TODO
    @GetMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Long id) {
        repository.deleteById(id);
        return "<script>window.location='/'</script>";
    }
}
