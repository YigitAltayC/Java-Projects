package com.udemy.in28minutes.springboot.course.myfirstapp.myfirstwebapp.todo;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;

// @Controller
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

        String username = getLoggedinUsername();

        List<Todo> todoList = service.getTodoList();
        model.put("todos", todoList);

        return "listTodos";
    }



    @RequestMapping(value="add-todo", method = RequestMethod.GET)
    public String showNewTodoPage(ModelMap model){
        String username = getLoggedinUsername();
        Todo todo = new Todo(0, username, "", LocalDate.now(), false);
        model.put("todo", todo);
        return "addTodo";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String addNewTodo(
            ModelMap model, @Valid Todo todo, BindingResult result){

        if(result.hasErrors())
            return "addTodo";

        service.addTodo(
                getLoggedinUsername(),
                todo.getDescription(),
                todo.getTargetDate(),
                false);

        return "redirect:list-todos";
    }

    @RequestMapping(value="update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model){
        Todo todo = service.getTodoWith(id);
        model.put("todo", todo);
        return "updateTodo";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.POST)
    public String updateTodo(
            ModelMap model, @Valid Todo todo, BindingResult result){

        if(result.hasErrors())
            return "updateTodo";

        todo.setUsername(
                getLoggedinUsername()
        );

        service.updateTodo(todo);
        return "redirect:list-todos";
    }

    /**
     * RequestParam demek, URL'de yazacak demek
     * delete-todo?id=0 gibi
     *
     * @param id
     * @return
     */
    @RequestMapping("delete-todo")
    public String deleteTodo(@RequestParam int id){

        service.deleteById(id);
        return "redirect:list-todos";
    }

    /**
     * Gets current loggedInUser from the Spring's authentication system via
     * spring-boot-starter-validation
     * @return
     */
    private String getLoggedinUsername(){
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        return authentication.getName();
    }

}
