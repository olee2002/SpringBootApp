package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao){
        this.userDao = userDao;
    }

    public int addPerson(User user){
        return userDao.insertPerson(user);
    }

    public Boolean getPersonByUsername(User user){
        return userDao.selectPersonByUsername(user);
    }
}