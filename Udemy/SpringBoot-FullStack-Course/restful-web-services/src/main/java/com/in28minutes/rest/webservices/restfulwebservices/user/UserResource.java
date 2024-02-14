package com.in28minutes.rest.webservices.restfulwebservices.user;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    private UserDaoService service;

    public UserResource(UserDaoService service) {
        this.service = service;
    }

    // GET /users
    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    // GET /users
    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id){

        User user = service.findOne(id);

        if(user == null)
            throw new UserNotFoundException("id:");

        return user;
    }

    // DELETE /users/{id}
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        service.deleteById(id);
    }

    // POST /users
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user)
    {
        /**
         * This post method returns response 200, which stands for - Success
         * In order to make a better functionality that returns a valid response;
         *
         * This has to return Response - 201, so that It can return a valid response
         * that can be understood by user (201 - Created)
         */
        User savedUser = service.save(user);

        // /users/4 => /users /{id}, user.getID
        URI location =
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedUser.getId())
                        .toUri();

        /**
         * The reason why we create a URI.
         * Response Status - Created object keeps these details:
         *      connection
         *      content-length
         *      date
         *      keep-alive
         *      and "location"
         * This location returns a URL that contains the location of the created object.
         * If you set the location as the URL of the inserted user;
         * Then you can easily access to that user by using the GET request
         * Such like:
         *    https://localhost:8080/users/4 (4 stands for the id of added user)
         *
         * URL process goes on like:
         * ServletUriComponentsBuilder
         *      .fromCurrentRequest() (Which is, /users
         *      .path(id) --> /users/{id}
         *      .buildAndExpand(savedUsersId, which is 4)
         *      .toUri
         */

        return ResponseEntity.created(location).build();
    }

}
