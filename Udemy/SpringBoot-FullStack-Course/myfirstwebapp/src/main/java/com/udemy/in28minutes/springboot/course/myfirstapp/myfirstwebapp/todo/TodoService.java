package com.udemy.in28minutes.springboot.course.myfirstapp.myfirstwebapp.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class TodoService {

    private static int todosCount = 0;
    private static List<Todo> todoList = new ArrayList<>();

    static {
        todoList.add(new Todo(++todosCount, "localhost", "Study Spring",
                LocalDate.now().plusDays(10), false));
        todoList.add(new Todo(++todosCount, "localhost", "Complete .NET Test",
                LocalDate.now().plusDays(3), false));
        todoList.add(new Todo(++todosCount, "localhost", "Ask to school X for Post-Graduate Program",
                LocalDate.now().plusDays(5), false));
    }

    public List<Todo> findByUsername(String username) {
        Predicate<? super Todo> predicate
                = todo -> todo.getUsername().equalsIgnoreCase(username);

        return todoList.stream().filter(predicate).toList();
    }

    public List<Todo> getTodoList(){
        return todoList;
    }

    public List<Todo> getTodoListWith(String username)
    {
        return todoList.stream()
                .filter(todo -> todo.getUsername().equals(username))
                .collect(Collectors.toList());
    }

    public Todo getTodoWith(int id)
    {
        Predicate<? super Todo> predicate
                = todo -> todo.getId() == id;
        return todoList.stream().filter(predicate).findFirst().get();
    }

    public void addTodo(String username, String description, LocalDate targetDate, boolean isDone)
    {
        Todo todo = new Todo(
                ++todosCount,
                username,
                description,
                targetDate,
                isDone
        );

        todoList.add(todo);
    }

    public void updateTodo(@Valid Todo todo)
    {
        deleteById(todo.getId());
        todoList.add(todo);
    }

    public void deleteById(int id) {

        Predicate<? super Todo> predicate
                = todo -> todo.getId() == id;
        todoList.removeIf(predicate);
    }
}
