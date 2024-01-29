package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

/**
 * 2. Bean Definition
 * Here’s a definition of beans in the Spring Framework
 * documentation:
 *
 * In Spring, the objects that form the backbone of your
 * application and that are managed by the Spring IoC container
 * are called beans. A bean is an object that is instantiated,
 * assembled, and otherwise managed by a Spring IoC container.
 *
 * This definition is concise and
 * gets to the point but fails to elaborate
 * on an important element: the Spring IoC container.
 *
 * Let’s take a closer look to see what it is
 * and the benefits it brings in.
 *
 * Rest is on:
 * https://www.baeldung.com/spring-bean#:~:text=Bean%20Definition&text=In%20Spring%2C%20the%20objects%20that,by%20a%20Spring%20IoC%20container.
 */
public class HelloWorldBean {

    private String message;

    public HelloWorldBean(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "HelloWorldBean [message=" + message + "]";
    }
}
