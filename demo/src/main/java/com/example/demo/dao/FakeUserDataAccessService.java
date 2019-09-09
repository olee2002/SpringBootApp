package com.example.demo.dao;

import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakeUserDataAccessService implements UserDao {

    private static List<User> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, User user) {
        DB.add(new User(id, user.getUsername(), user.getPassword()));
        return 1;
    }
    @Override
    public List<User> selectAllPeople(){
        return DB;
    }

    @Override
    public Optional<User> selectPersonById(UUID id) {
        return DB.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<User> personMaybe = selectPersonById(id);
        if(personMaybe.isEmpty()){
            return 0;
        }
        DB.remove(personMaybe.get());
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, User user) {
        return selectPersonById(id)
                .map(p->{
                    int indexOfPersonToDelete = DB.indexOf(user);
                    if(indexOfPersonToDelete >= 0) {
                        DB.set(indexOfPersonToDelete, user);
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }
}