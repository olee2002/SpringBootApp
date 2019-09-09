package com.example.demo.dao;

import com.example.demo.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDao {
    int insertPerson(UUID id, User user);
    default int insertPerson(User user){
        UUID id = UUID.randomUUID();
        return insertPerson(id, user);
    }
    List<User> selectAllPeople();

    Optional<User> selectPersonById(UUID id);

    int deletePersonById(UUID id);

    int updatePersonById(UUID id, User user);
}