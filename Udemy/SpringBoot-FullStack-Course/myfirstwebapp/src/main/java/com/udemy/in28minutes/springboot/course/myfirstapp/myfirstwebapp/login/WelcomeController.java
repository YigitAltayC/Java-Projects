package com.udemy.in28minutes.springboot.course.myfirstapp.myfirstwebapp.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("username")
public class WelcomeController {

    //private TemporaryAuthenticationService authenticator = new TemporaryAuthenticationService();
    // RequestParam sayesinde urlsinde parametre kullanabiliriz
    // http://localhost:8080/login?name=Ad
    // ModelMap kullanımı sayesinde, bunu login.jsp'de de kullanabiliriz.
    // Oraya gidip ${name} yapmamız lazım.
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String goToWelcomePage(ModelMap model){
        model.put("username", getLoggedinUsername());
        return "welcome";
    }

    private String getLoggedinUsername(){
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        return authentication.getName();
    }

    /*
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String goToWelcomePage(@RequestParam String username, @RequestParam String password, ModelMap model){

        if(!authenticator.authenticate(username, password)){
            model.put("errorMessage", "Invalid username or password. Try again.");
            return "login";
        }

        model.put("username", username);
        return "welcome";

    } */
}
