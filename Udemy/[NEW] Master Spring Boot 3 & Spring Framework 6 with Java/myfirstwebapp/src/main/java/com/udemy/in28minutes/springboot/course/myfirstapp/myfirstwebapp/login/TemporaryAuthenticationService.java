package com.udemy.in28minutes.springboot.course.myfirstapp.myfirstwebapp.login;


import org.springframework.stereotype.Service;

@Service
public class TemporaryAuthenticationService {

    public boolean authenticate(String username, String password)
    {
        boolean isValidUsername = username.equalsIgnoreCase("localhost");
        boolean isValidPassword = password.equalsIgnoreCase("root");

        return isValidPassword && isValidUsername;
    }
}