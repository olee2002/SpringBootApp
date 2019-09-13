package com.example.demo.api;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="*")
@RequestMapping("api/v1/users")
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public String hello(){
        return "Welcome to the backend api of O'lee's spring app!";
    }

    @PostMapping
    public void addPerson(@RequestBody User user){
        userService.addPerson(user);
    }

    @PutMapping
    public Boolean getPersonByUsername(@RequestBody User user){
        return userService.getPersonByUsername(user);
    }
}
