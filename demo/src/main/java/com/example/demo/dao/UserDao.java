package com.example.demo.dao;

import com.example.demo.model.User;

public interface UserDao {
    int insertPerson(Integer id, User user);
    default int insertPerson(User user){
        return insertPerson((int) (Math.random() * 101), user);
    }

    Boolean selectPersonByUsername(User user);

}