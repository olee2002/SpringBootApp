package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("fakeDao")
public class FakePersonDataAccess implements PersonDao {

    private static List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getUsername(), person.getPassword()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
//        System.out.println(Arrays.to);
        return DB;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB
                .stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<Person> selectPersonByUsername(String username, String password) {
        return DB
                .stream()
                .filter(person -> person.getUsername().equals(username))
                .findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> personMaybe = selectPersonById(id);
        if (personMaybe.isPresent()) {
            DB.remove(personMaybe.get());
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        return selectPersonById(id)
                .map(p -> {
                    int indexOfPersonToUpdate = DB.indexOf(p);
                    System.out.print(indexOfPersonToUpdate);
                    if (indexOfPersonToUpdate >= 0) {
                        DB.set(indexOfPersonToUpdate, new Person(id, person.getUsername(), person.getPassword()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }

    @Override
    public int logInPersonByUsername(String username, String password, Person person) {
        DB.forEach(System.out::println);
        return selectPersonByUsername(username, password)
                .map(p -> {
                    int indexOfPersonToLogIn = DB.indexOf(p);
                    if (indexOfPersonToLogIn >= 0) {
                        if (p.getPassword().equals(person.getPassword())) {
                            System.out.println(p.getPassword());
                            System.out.println("Password Correct!");
                            return 1;
                        } else {
                            System.out.println("Password Incorrect!");
                            return 0;
                        }
                    }
                    return 0;
                }).orElse(0);
    }
}

