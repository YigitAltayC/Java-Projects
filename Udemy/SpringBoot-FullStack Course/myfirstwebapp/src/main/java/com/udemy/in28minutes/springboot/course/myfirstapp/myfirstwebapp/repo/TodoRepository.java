package com.udemy.in28minutes.springboot.course.myfirstapp.myfirstwebapp.repo;

import com.udemy.in28minutes.springboot.course.myfirstapp.myfirstwebapp.todo.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * This is the equivalent of a class that contains any operations related to Todo table in H2
 * Beware, this is not a persist data. So after you close up the server, all of the added data etc. is completely gone.
 */
public interface TodoRepository extends JpaRepository<Todo, Integer> {

    public List<Todo> findByUsername(String username);
}
