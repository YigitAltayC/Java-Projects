package com.in28minutes.rest.webservices.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Post stands for User's media posts or like tweets etc.
 */

@Entity
public class Post {

    @Id
    @GeneratedValue
    private Integer id;
    @Size(min = 10)
    private String description;

    /**
     * If we want to get Post details with User details, we have to use EAGER Fetch
     * But I don't want to get user details, so I'll use lazy this time.
     * Also, in data.sql part, we'll use user_id for User's instead.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
