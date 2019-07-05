package it.univaq.disim.bioinformatics.melanoq.controller;

import it.univaq.disim.bioinformatics.melanoq.model.User;
import it.univaq.disim.bioinformatics.melanoq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/user")
    public Response createUser(@RequestBody User user, HttpServletRequest request) throws Exception {
        User u = userService.save(user);
        Response<User> response = new Response<>(HttpStatus.CREATED, request);
        response.setData(u);
        return response;
    }

    @GetMapping(value = "/user/{username}")
    public Response getUser(@PathVariable String username, HttpServletRequest request) throws Exception {
        User u = userService.findOneByUsername(username);
        Response<User> response = new Response<>(HttpStatus.OK, request);
        response.setData(u);
        return response;
    }

}
