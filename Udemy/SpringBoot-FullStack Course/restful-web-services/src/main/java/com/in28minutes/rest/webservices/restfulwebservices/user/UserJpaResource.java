package com.in28minutes.rest.webservices.restfulwebservices.user;

import com.in28minutes.rest.webservices.restfulwebservices.jpa.PostRepository;
import com.in28minutes.rest.webservices.restfulwebservices.jpa.UserRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJpaResource {
    private UserRepository userRepository;
    private PostRepository postRepository;

    public UserJpaResource(UserRepository userRepository, PostRepository postRepository)
    {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    // GET /users
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers(){
        // return service.findAll();
        return userRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrievePostsForUser(@PathVariable int id){
        // return service.findAll();
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty())
            throw new UserNotFoundException("id:" + id);

        return user.get().getPosts();
    }

    // GET /users
    @GetMapping("/jpa/users/{id}")
    // EntityModel --> A simple EntityModel wrapping a domain object and adding links to it.
    public EntityModel<User> retrieveUser(@PathVariable int id){

        //User user = service.findOne(id);
        Optional<User> user = userRepository.findById(id);

        //if(user == null)
        if(user.isEmpty())
            throw new UserNotFoundException("id:" + id);

        EntityModel<User> entityModel = EntityModel.of(user.get());

        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));

        return entityModel;
    }

    // DELETE /users/{id}
    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id){
        userRepository.deleteById(id);
    }

    // POST /users
    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user)
    {
        /**
         * This post method returns response 200, which stands for - Success
         * In order to make a better functionality that returns a valid response;
         *
         * This has to return Response - 201, so that It can return a valid response
         * that can be understood by user (201 - Created)
         */
        User savedUser = userRepository.save(user);

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

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Post> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post){

        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty())
            throw new UserNotFoundException("id:" + id);

        post.setUser(user.get());
        Post savedPost = postRepository.save(post);

        URI location =
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedPost.getId())
                        .toUri();

        return ResponseEntity.created(location).build();
    }



}
