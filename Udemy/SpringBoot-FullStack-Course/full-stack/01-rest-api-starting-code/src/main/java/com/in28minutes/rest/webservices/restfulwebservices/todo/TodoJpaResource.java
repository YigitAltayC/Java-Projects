package com.in28minutes.rest.webservices.restfulwebservices.todo;

import com.in28minutes.rest.webservices.restfulwebservices.todo.repository.TodoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoJpaResource {

    private TodoRepository repository;

    public TodoJpaResource(TodoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/users/{username}/todos")
    public List<Todo> retrieveTodos(@PathVariable String username)
    {
        return repository.findByUsername(username);
    }

    @GetMapping("/users/{username}/todos/{id}")
    public Todo retrieveTodo(@PathVariable String username, @PathVariable int id)
    {
        return repository.findById(id).get();
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(
            @PathVariable String username,
            @PathVariable int id)
    {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{username}/todos/{id}")
    public Todo updateTodo(
            @PathVariable String username,
            @PathVariable int id,
            @RequestBody Todo todo)
    {
        repository.save(todo);
        return todo;
    }

    @PostMapping("/users/{username}/todos")
    public Todo createTodo(
            @PathVariable String username,
            @RequestBody Todo todo)
    {
        todo.setUsername(username);
        todo.setId(null);
        /**Todo createdTodo = todoService.addTodo(
                username,
                todo.getDescription(),
                todo.getTargetDate(),
                todo.isDone()
        );*/
        return repository.save(todo);
    }


}
