package com.udemy.in28minutes.springboot.course.myfirstapp.myfirstwebapp.todo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes("username")
public class TodoController {

    // Bu service sınıfının içine @Service yazdığımız için @Autowired olmuş oldu
    // Yani, TodoController constructorunun içindeki parametre olan service
    // Java Spring tarafından otomatik olarak yaratılıp atanıyor.
    private TodoService service;
    public TodoController(TodoService service) {
        super();
        this.service = service;
    }

    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap model){
        List<Todo> todoList = service.getTodoList();
        model.put("todos", todoList);

        return "listTodos";
    }

    @RequestMapping(value="add-todo", method = RequestMethod.GET)
    public String showNewTodoPage(ModelMap model){
        String username = (String) model.get("name");
        Todo todo = new Todo(0, username, "", LocalDate.now(), false);
        model.put("todo", todo);
        return "addTodo";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String addNewTodo(
            ModelMap model, Todo todo){

        service.addTodo(
                (String)model.get("username"),
                todo.getDescription(),
                todo.getTargetDate(),
                false);

        return "redirect:list-todos";
    }


}
