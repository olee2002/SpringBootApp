package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins="*")
@RequestMapping("api/v1/users")
@RestController
public class PersonController {
    private final PersonService personService;
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    @PostMapping
    public List<Person> addPerson(@RequestBody Person person){
        personService.insertPerson(person);
        return personService.getAllPeople();
    }
    @PostMapping(path = "/login")
    public Optional<Person> getPersonByUsername(@RequestBody Person personToLogin){
        System.out.printf(personToLogin.getUsername());
        return personService.getPersonByUsername(personToLogin.getUsername());
    }
    @GetMapping(path = "{id}")
    public Person getPersonById(@PathVariable("id") UUID id){
        return personService.getPersonById(id)
                .orElse(null);
    }
    @GetMapping
    public List<Person> getAllPeople(){
        System.out.println("Welcome to the backend api of O'lee's spring app!");
        return personService.getAllPeople();
    }
    @DeleteMapping(path = "{id}")
    public List<Person> deletePersonById(@PathVariable("id") UUID id){
        personService.deletePerson(id);
        return personService.getAllPeople();
    }
    @PutMapping(path="{id}")
    public List<Person> updatePerson(@PathVariable("id") UUID id, @RequestBody Person personToUpdate){
        personService.updatePerson(id, personToUpdate);
        return personService.getAllPeople();
    }

  }
