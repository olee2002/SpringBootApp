package com.example.demo.api;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/users")
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public void addPerson(@RequestBody User user){
        userService.addPerson(user);
    }

    @GetMapping
    public List<User> getAllPeople(){
        return userService.getAllPeople();
    }
    @GetMapping(path = "{id}")
    public User getPersonById(@PathVariable("id") UUID id){
        return userService.getPersonById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deletePersonById(@PathVariable("id") UUID id){
        userService.deletePerson(id);
    }

    @PutMapping(path = "{id}")
    public void updatePerson(@PathVariable("id") UUID id, @RequestBody User userToUpdate){
        userService.updatePerson(id, userToUpdate);
    }
}
