package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(@Qualifier("fakeDao") UserDao userDao){
        this.userDao = userDao;
    }

    public int addPerson(User user){
        return userDao.insertPerson(user);
    }

    public List<User> getAllPeople(){
        return userDao.selectAllPeople();
    }

    public Optional<User> getPersonById(UUID id){
        return userDao.selectPersonById(id);
    }

    public int deletePerson(UUID id){
        return userDao.deletePersonById(id);
    }

    public int updatePerson(UUID id, User newUser){
        return userDao.updatePersonById(id, newUser);
    }
}